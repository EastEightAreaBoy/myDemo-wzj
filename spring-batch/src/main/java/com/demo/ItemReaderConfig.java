package com.demo;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

/**
 * @author HP
 */
@Configuration
public class ItemReaderConfig {

    @Value("spring-batch-input/inputData.csv")
    private Resource[] inputResources;

    @Bean
    public MultiResourceItemReader<Employee> multiResourceItemReader(FlatFileItemReader<Employee> reader) {
        MultiResourceItemReader<Employee> resourceItemReader = new MultiResourceItemReader<>();
        resourceItemReader.setResources(inputResources);
        resourceItemReader.setDelegate(reader);
        return resourceItemReader;
    }

    @Bean
    public FlatFileItemReader<Employee> reader() {
        FlatFileItemReader<Employee> reader = new FlatFileItemReader<>();
        //跳过csv文件第一行，为表头
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        //字段名
                        setNames("id", "firstName", "lastName");
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Employee>() {
                    {
                        //转换化后的目标类
                        setTargetType(Employee.class);
                    }
                });
            }
        });
        return reader;
    }

    @Bean
    public ItemProcessor<Employee, Employee> itemProcessor() {
        return employee -> {
            employee.setLastName(employee.getLastName().toUpperCase());
            return employee;
        };
    }
}
