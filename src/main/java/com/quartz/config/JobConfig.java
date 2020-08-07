package com.quartz.config;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.quartz.job.JobA;
import com.quartz.job.JobB;

@Configuration
public class JobConfig {

	//Name variable of bean JobDetail 'jobDetailsA' below must same with parameter JobDetail initial variable in Trigger jobTriggerA ('jobDetailsA')
	@Bean
	public JobDetail jobDetailsA () {
		return JobBuilder
			.newJob(JobA.class)
			.withIdentity("jobA")
			.storeDurably()
			.build();
	}
	
	@Bean
	public Trigger jobTriggerA(JobDetail jobDetailsA) {
		return TriggerBuilder
				.newTrigger()
				.forJob(jobDetailsA)
				.withIdentity("triggerA")
				.withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * ? * * *"))
				.build();
	}
		
	@Bean
	public JobDetail jobDetailsB() {
		return JobBuilder
			.newJob(JobB.class)
			.withIdentity("jobB")
			.storeDurably()
			.build();
	}
		
	@Bean
	public Trigger jobTriggerB(JobDetail jobDetailsB) {
		return TriggerBuilder
			.newTrigger()
			.forJob(jobDetailsB)
			.withIdentity("triggerB")
			.withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * ? * * *").withMisfireHandlingInstructionIgnoreMisfires())
			.build();
	}
}
