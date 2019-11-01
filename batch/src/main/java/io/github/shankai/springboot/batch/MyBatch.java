package io.github.shankai.springboot.batch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.item.support.ListItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatch
 */
@EnableBatchProcessing
@Configuration
public class MyBatch {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public ItemProcessor<String, String> processor() {
        return new MyItemProcessor();
    }

    @Bean
    public ItemReader<String> reader() {
        List<String> names = new ArrayList<>();
        names.add("1");
        names.add("2");
        names.add("3");
        names.add("4");
        names.add("5");
        names.add("6");

        ListItemReader<String> reader = new ListItemReader<>(names);
        return reader;
    }

    @Bean
    public ItemWriter<String> writer() {

        ListItemWriter<String> writer = new ListItemWriter<String>() {
            @Override
            public void write(List<? extends String> items) throws Exception {
                System.setProperty("SpringBatch", items.stream().map(s -> s.toString()).collect(Collectors.joining(",")));
            }
        };
        return writer;

    }

    @Bean
    public Step myStep() {
        return this.stepBuilderFactory.get("myStep").<String, String>chunk(10).reader(reader()).processor(processor())
                .writer(writer()).build();
    }

    @Bean
    public Job myJob(MyListener listener) {
        return this.jobBuilderFactory.get("myJob").incrementer(new RunIdIncrementer()).listener(listener).flow(myStep())
                .end().build();
    }

}