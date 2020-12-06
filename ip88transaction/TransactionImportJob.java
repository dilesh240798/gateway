package com.scb.scroe.gateway.ip88transaction;




import com.scb.scroe.gateway.ip88transaction.constant.TransactionConstant;
import com.scb.scroe.gateway.ip88transaction.listener.TransactionJobListener;
import com.scb.scroe.gateway.ip88transaction.model.TransactionRecord;
import com.scb.scroe.gateway.ip88transaction.step1.ItemCountListener;
import com.scb.scroe.gateway.ip88transaction.step1.TransactionCompositeProcessor;
import com.scb.scroe.gateway.ip88transaction.step1.TransactionSynchronizedReader;
import com.scb.scroe.gateway.ip88transaction.step1.TransactionWriter;
import com.scb.scroe.gateway.ip88transaction.validationsteps.HeaderValidationTasklet;
import com.scb.scroe.gateway.ip88transaction.validationsteps.TrailerValidationTasklet;
import com.scb.scroe.gateway.repository.DatabaseAccessObject;
import org.hibernate.SessionFactory;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowJobBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import com.scb.scroe.gateway.config.SpringBatchConfig;
import com.scb.scroe.gateway.constant.BatchConstant;
import com.scb.scroe.gateway.entity.IP88TransactionLandingEntity;

@Configuration
@Import(SpringBatchConfig.class)
public class TransactionImportJob {

	private JobBuilder jobBuilder;
	private StepBuilder stepBuilder0;
	private StepBuilder stepBuilder1;
//	private StepBuilder stepBuilder2;
	private StepBuilder stepBuilder3;
	private SessionFactory sessionFactory;
	private DatabaseAccessObject dao;
	private TaskExecutor taskExecutor;
	private JobRepository jobRepository;
    private PlatformTransactionManager transactionManager;

	public TransactionImportJob(JobRepository jobRepository, SessionFactory sessionFactory, DatabaseAccessObject dao, TaskExecutor taskExecutor,
			@Qualifier("commonTxnMgr") PlatformTransactionManager transactionManager) {
		this.dao = dao;
		this.sessionFactory = sessionFactory;
		this.jobRepository = jobRepository;
	    this.transactionManager = transactionManager;
		this.jobBuilder = new JobBuilder(TransactionConstant.IP88TRANSACTION_JOB_NAME).repository(jobRepository);
		this.stepBuilder0 = new StepBuilder(TransactionConstant.IP88TRANSACTION_STEP0_NAME).repository(jobRepository)
				.transactionManager(transactionManager);
		this.stepBuilder1 = new StepBuilder(TransactionConstant.IP88TRANSACTION_STEP1_NAME).repository(jobRepository)
				.transactionManager(transactionManager);
//		this.stepBuilder2 = new StepBuilder(IETConstant.IET_STEP2_NAME).repository(jobRepository)
//				.transactionManager(transactionManager);
		this.stepBuilder3 = new StepBuilder(TransactionConstant.IP88TRANSACTION_STEP3_NAME).repository(jobRepository)
				.transactionManager(transactionManager);
		this.taskExecutor = taskExecutor;
	}
//fault tolerance, api call step,posting step, # 500 errors
	@Bean(name = BatchConstant.IP88TRANSACTION_JOB)
	public FlowJobBuilder misPaymentsJob() {
//		return jobBuilderFactory.get("job").incrementer(new RunIdIncrementer())
//				.flow(feedImportStep()).end().build();
		
		return this.jobBuilder
				.listener(new TransactionJobListener())
				.flow(checkFile()).on("END").end()
				.from(checkFile()).on("*").to(headerValidationStep())
				.from(headerValidationStep()).on(BatchConstant.EXIT_STATUS_END).end()
				.from(headerValidationStep()).on("*").to(trailerValidationsStep())
				.from(trailerValidationsStep()).on(BatchConstant.EXIT_STATUS_END).end()
                .from(trailerValidationsStep()).on("*").to(feedImportStep())
				.next(deleteFile())
				.build();
		
//		jobBuilder.start(flow)
	}
	
	public Step feedImportStep() {
//		return stepBuilderFactory.get("step1").<Customer, Customer>chunk(2)
//				.reader(Reader.reader("customer-data.csv"))
//				.processor(new Processor()).writer(new Writer(customerDao)).build();
		//System.out.println("Reached feedImportStep");
		return this.stepBuilder1.<TransactionRecord, IP88TransactionLandingEntity>chunk(1)
				.reader(new TransactionSynchronizedReader())
				.processor(new TransactionCompositeProcessor())
				.writer(new TransactionWriter(sessionFactory))
//				.faultTolerant().skipPolicy(new FileItemSkipper())	
				.listener(new ItemCountListener())
				.taskExecutor(taskExecutor)
				//.retryPolicy(new RetryStep())
//				.retryLimit(3)
//                .retry(TransactionException.class)
				//.skip(ParseException.class)
//				.listener(new JobParameterExecutionContextCopyListener()).listener(new IETJobListener())
//				.listener(new StepResultListener())
//	                .faultTolerant() 
//	                .skip(IETRecordInvalidException.class)
//	                .skipLimit(this.prop.getIet().getSkipLimit())
//	                .listener(new StepItemReadListener())
//	                .listener(new StepItemProcessListener())
//	                .listener(new StepItemWriteListener())
//	                .listener(new StepSkipListener())
//	                .listener(new ChunkItemsListener())
				.build();
	}
	
    public Step deleteFile() {
        return this.stepBuilder3.tasklet(new DeleteFileTasklet()).build();
    }
	
	private Step checkFile() {
	        return this.stepBuilder0.tasklet(new CheckFileTasklet()).build();
	}
	
	private Step headerValidationStep() {
        return step(TransactionConstant.IP88TRANSACTION_HEADER_VALIDATION)
                .tasklet(new HeaderValidationTasklet())
                .build();
    }
	
	private Step trailerValidationsStep() {
		return step(TransactionConstant.IP88TRANSACTION_TRAILER_VALIDATION)
				.tasklet(new TrailerValidationTasklet())
				.build();
	}

	private StepBuilder step(String stepName) {
		
		return new StepBuilder(stepName)
				.repository(this.jobRepository)
				.transactionManager(this.transactionManager);
	}

}
