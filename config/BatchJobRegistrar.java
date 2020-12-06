package com.scb.scroe.gateway.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.DuplicateJobException;
import org.springframework.batch.core.configuration.JobFactory;
import org.springframework.batch.core.configuration.JobRegistry;

public class BatchJobRegistrar {
	
	private JobRegistry registry;

    public BatchJobRegistrar(JobRegistry registry) {
        this.registry = registry;
    }

    public void registerJob(Job job) throws DuplicateJobException {
        this.registry.register(new JobFactory() {
            @Override
            public Job createJob() {
                return job;
            }

            @Override
            public String getJobName() {
                return job.getName();
            }
        });
    }
}
