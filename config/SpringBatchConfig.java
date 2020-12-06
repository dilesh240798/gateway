package com.scb.scroe.gateway.config;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Import;

@EnableBatchProcessing
@Import(DBConfig.class)
public class SpringBatchConfig extends DefaultBatchConfigurer{

//	@Override
//    protected JobRepository createJobRepository() throws Exception {
//        MapJobRepositoryFactoryBean factoryBean = new MapJobRepositoryFactoryBean();
//        factoryBean.afterPropertiesSet();
//        return factoryBean.getObject();
//    }
	
//	@Bean
//	public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry) {
//	    JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
//	    jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
//	    return jobRegistryBeanPostProcessor;
//	}
}