package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.util.List;

/**
 * @author HP
 */
@Configuration
@Slf4j
public class ItemWriterConfig {

    private final Resource outputResource = new FileSystemResource("spring-batch-output/outputData.csv");
//    private final Resource outputResource = new ClassPathResource("output/outputData.csv");

    @Bean
    public ItemWriteListener<Employee> pkslowWriteListener() {
        return new ItemWriteListener<>() {
            @Override
            public void beforeWrite(List<? extends Employee> list) {
                log.info("=====beforeWrite: " + list);
            }

            @Override
            public void afterWrite(List<? extends Employee> list) {
                log.info("=====afterWrite: " + list);
            }

            @Override
            public void onWriteError(Exception e, List<? extends Employee> list) {
                log.info("=====onWriteError: " + list);
            }
        };
    }

    @Bean
    public FlatFileItemWriter<Employee> writer() {
        FlatFileItemWriter<Employee> writer = new FlatFileItemWriter<>();
        writer.setResource(outputResource);
        //是否为追加模式
        writer.setAppendAllowed(true);
        writer.setLineAggregator(new DelimitedLineAggregator<>() {
            {
                //设置分割符
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor<>() {
                    {
                        //设置字段
                        setNames(new String[]{"id", "firstName", "lastName"});
                    }
                });
            }
        });
        return writer;
    }

}
