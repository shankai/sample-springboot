package io.github.shankai.springboot.batch;

import org.springframework.batch.item.ItemProcessor;

/**
 * MyItemProcessor
 */
public class MyItemProcessor implements ItemProcessor<String, String> {

    @Override
    public String process(String item) throws Exception {
        return item + "-item-processor";
    }

}