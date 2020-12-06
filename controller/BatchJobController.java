package com.scb.scroe.gateway.controller;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.ThreadContext;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.DuplicateJobException;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.ReferenceJobFactory;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.job.builder.FlowJobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.scb.scroe.gateway.constant.BatchConstant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Controller
public class BatchJobController {

	private JobLauncher jobLauncher;

	private FlowJobBuilder ip88transactionjob;

	private FlowJobBuilder ip88MerchantJob;

	private JobRepository jobRepository;

	@Autowired
	private JobRegistry jobRegistry;

	@Autowired
	private JobExplorer jobExplorer;

	@Autowired
	private JobOperator jobOperator;

	Logger logger = LogManager.getLogger(BatchJobController.class);

	BatchJobController(JobLauncher jobLauncher,
			@Qualifier(BatchConstant.IP88TRANSACTION_JOB) FlowJobBuilder ip88transactionjob, JobExplorer jobExplorer,
			JobRepository jobRepository, JobRegistry jobRegistry, JobOperator jobOperator) {

		this.jobLauncher = jobLauncher;
		this.ip88transactionjob = ip88transactionjob;
		this.jobExplorer = jobExplorer;
		this.jobRepository = jobRepository;

	}

	public void transactionJob() throws NoSuchJobExecutionException, NoSuchJobException {

		try {

			ReferenceJobFactory referenceJobFactory = new ReferenceJobFactory(ip88transactionjob.build());
			jobRegistry.register(referenceJobFactory);

//		    	"C:\\Users\\1626207\\CASH\\bitbucket\\scroe\\scroe-connect-gateway\\src\\main\\resources\\readfilename.csv"
			String fileName = "C:\\Users\\1626207\\CASH\\bitbucket\\scroe\\scroe-connect-gateway\\src\\main\\resources\\readfilenamedebug.csv";
//			String fileName = "C:\\Users\\1626239\\Documents\\scroe-connect-gateway\\src\\main\\resources\\readfilename.csv";
			JobParameters params = new JobParametersBuilder()
					.addString(BatchConstant.JOB_ID, String.valueOf(System.currentTimeMillis()))
					.addString(BatchConstant.IP88TRANSACTION_SRC_FILEPATH, fileName).toJobParameters();

			// logger(params);
			JobExecution execution = this.jobLauncher.run(ip88transactionjob.build(), params);

		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException | DuplicateJobException e) {
			System.out.println("Exception");
			
		}
	}

	public void transactionJob2() throws NoSuchJobExecutionException, NoSuchJobException {

		try {

			jobRegistry.register(new ReferenceJobFactory(ip88transactionjob.build()));
			List<String> jobs = jobExplorer.getJobNames();
			for (String job : jobs) {

				Set<JobExecution> jobExecutions = jobExplorer.findRunningJobExecutions(job);

				for (JobExecution jobExecution : jobExecutions) {

					Collection<StepExecution> stepExecutions = jobExecution.getStepExecutions();
					for (StepExecution stepExecution : stepExecutions) {
						BatchStatus status = stepExecution.getStatus();
						if (status.isRunning() || status == BatchStatus.STOPPING || status == BatchStatus.STARTED) {
							stepExecution.setStatus(BatchStatus.STOPPED);
							stepExecution.setEndTime(new Date());
							jobRepository.update(stepExecution);
						}
					}

					jobExecution.setStatus(BatchStatus.STOPPED);
					jobExecution.setEndTime(new Date());
					jobRepository.update(jobExecution);

					Long jobExecutionId = jobExecution.getId();
					jobOperator.restart(jobExecutionId);
				}
			}
		}

		catch (DuplicateJobException | JobParametersInvalidException | JobRestartException
				| JobInstanceAlreadyCompleteException e) {
			System.out.println("Exception: " + e);
		}
	}

	public void merchantJob() {
		try {
			String fileName = "C:\\Users\\1626207\\CASH\\bitbucket\\scroe\\scroe-connect-gateway\\src\\main\\resources\\readfilename.csv";
			JobParameters params = new JobParametersBuilder()
					.addString(BatchConstant.JOB_ID, String.valueOf(System.currentTimeMillis()))
					.addString(BatchConstant.IP88TRANSACTION_SRC_FILEPATH, fileName).toJobParameters();
			this.jobLauncher.run(ip88MerchantJob.build(), params);
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			System.out.println("Exception");
		}
	}
}
