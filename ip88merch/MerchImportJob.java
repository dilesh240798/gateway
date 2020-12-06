package com.scb.scroe.gateway.ip88merch;

import com.scb.scroe.gateway.constant.BatchConstant;
import com.scb.scroe.gateway.entity.MerchantLandingEntity;
import com.scb.scroe.gateway.ip88merch.constant.MerchConstant;
import com.scb.scroe.gateway.ip88merch.model.MerchRecord;
import com.scb.scroe.gateway.ip88merch.step1.MerchantWriter;
import com.scb.scroe.gateway.ip88merch.tasklet.HeaderValidation;
import com.scb.scroe.gateway.ip88merch.tasklet.TrailerValidation;
import com.scb.scroe.gateway.ip88transaction.listener.TransactionJobListener;
import com.scb.scroe.gateway.ip88transaction.step1.ItemCountListener;
import com.scb.scroe.gateway.repository.DatabaseAccessObject;
import org.hibernate.SessionFactory;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowJobBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import com.scb.scroe.gateway.config.BatchJobRegistrar;

public class MerchImportJob {

	private JobBuilder jobBuilder;
	private StepBuilder stepBuilder0;
	private StepBuilder stepBuilder1;
	private DatabaseAccessObject dao;
	private SessionFactory sessionFactory;
	private TaskExecutor taskExecutor;
	private BatchJobRegistrar jobRegistrar;
	private JobRepository jobRepository;
	private PlatformTransactionManager transactionManager;
	
	public MerchImportJob(JobRepository jobRepository,SessionFactory sessionFactory,TaskExecutor taskExecutor, BatchJobRegistrar jobRegistrar,DatabaseAccessObject dao, @Qualifier("commonTxMgr") PlatformTransactionManager transactionManager) {
		this.dao=dao;
		this.sessionFactory=sessionFactory;
		this.taskExecutor = taskExecutor;
		this.jobRegistrar = jobRegistrar;
		this.jobRepository = jobRepository;
		this.transactionManager = transactionManager;
		this.jobBuilder=new JobBuilder(MerchConstant.IP88MERCH_JOB_NAME).repository(jobRepository);
		this.stepBuilder0 = new StepBuilder(MerchConstant.IP88MERCH_STEP1_NAME).repository(jobRepository)
				.transactionManager(transactionManager);
		this.stepBuilder1 = new StepBuilder(MerchConstant.IP88MERCH_STEP2_NAME).repository(jobRepository)
				.transactionManager(transactionManager);
	}
	@Bean(name= BatchConstant.IP88MERCH_JOB)
	public FlowJobBuilder merchantJob() {
		return this.jobBuilder
				.listener(new TransactionJobListener())
				.repository(this.jobRepository)
				.start(headerValidationStep()).on("*").to(trailerValidationStep())
				.from(trailerValidationStep()).on("*").to(fileDownloadStep())
				.next(fileUploadStep())
				.end();

	}
	private Step fileUploadStep() {
		return null;
	}
	private Step fileDownloadStep() {
		return step(MerchConstant.IP88MERCH_STEP2_NAME)
                .<MerchRecord, MerchantLandingEntity>chunk(2)
//                .reader(new MerchantCsvSynchronizedReader())
//                .processor(new DStepCompositeProcessor(this.dao, this.chunkSet))
                .writer(new MerchantWriter(this.sessionFactory))
                .listener(new ItemCountListener())
                .taskExecutor(taskExecutor)
                .build();
	}
	private Step headerValidationStep() {
		return step(MerchConstant.IP88MERCH_STEP0_NAME)
                .tasklet(new HeaderValidation())
                .build();
	}
	private Step trailerValidationStep() {
		return step(MerchConstant.IP88MERCH_STEP1_NAME)
                .tasklet(new TrailerValidation())
                .build();
	}
	private StepBuilder step(String name) {
		return new StepBuilder(name)
				.repository(jobRepository)
				.transactionManager(transactionManager);
	}
}
