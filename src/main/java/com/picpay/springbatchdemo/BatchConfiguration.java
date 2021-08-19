package com.picpay.springbatchdemo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfiguration {

    @Bean
    public NumberReader reader() {
        return new NumberReader();
    }

    @Bean
    public StringParserProcessor processor() {
        return new StringParserProcessor();
    }

    @Bean
    public PrinterWriter writer() {
        return new PrinterWriter();
    }

    @Bean
    public MySkipPolicy skipPolicy() {
        return new MySkipPolicy();
    }

    @Bean("step1")
    public Step step(StepBuilderFactory stepBuilderFactory, NumberReader reader, StringParserProcessor processor, PrinterWriter writer, MySkipPolicy skipPolicy) {
        return stepBuilderFactory.get("main_step_1")
            .<Integer, String>chunk(3)
            .reader(reader)
            .processor(processor)
            .writer(writer)
            .faultTolerant()
            .skipPolicy(skipPolicy)
            .build();
    }

    @Bean("step2")
    public Step step2(StepBuilderFactory stepBuilderFactory, NumberReader reader, StringParserProcessor processor, PrinterWriter writer) {
        return stepBuilderFactory.get("main_step_2")
            .<Integer, String>chunk(3)
            .reader(new NumberReader())
            .processor(processor)
            .writer(writer)
            .build();
    }

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory, @Qualifier("step1") Step step, @Qualifier("step2") Step step2) {
        return jobBuilderFactory.get("main_job")
            .start(step)
            .next(step2)
            .build();
    }
}
