package com.demo;

import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author HP
 */
@Configuration
public class JobStep {

    @Bean
    public Step csvStep(StepBuilderFactory stepBuilderFactory,
                        MultiResourceItemReader<Employee> multiResourceItemReader,
                        ItemProcessor<Employee, Employee> itemProcessor,
                        FlatFileItemWriter<Employee> writer,
                        ItemWriteListener<Employee> pkslowWriteListener) {
        return stepBuilderFactory.get("csvStep").<Employee, Employee>chunk(5)
                .reader(multiResourceItemReader)
                .processor(itemProcessor)
                .writer(writer)
                .listener(pkslowWriteListener)
                .build();
    }

    @Bean
    public Job pkslowCsvJob(JobBuilderFactory jobBuilderFactory,
                            Step csvStep) {
        return jobBuilderFactory
                .get("pkslowCsvJob")
                .incrementer(new RunIdIncrementer())
                .start(csvStep)
                .build();
    }

}
