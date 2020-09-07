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
	/* withMisfireHandlingInstructionIgnoreMisfires() => Instructs the Scheduler that the Trigger will never be evaluated for a misfire situation, and that the scheduler will simply try to fire it as soon as it can,
	and then update the Trigger as if it had fired at the proper time.
	NOTE: if a trigger uses this instruction, and it has missed several of its scheduled firings, then several rapid firings may occur
	as the trigger attempt to catch back up to where it would have been. For example,
	a SimpleTrigger that fires every 15 seconds which has misfired for 5 minutes will fire 20 times once it gets the chance to fire.*/
}
