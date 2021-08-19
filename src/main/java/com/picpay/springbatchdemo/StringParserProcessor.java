package com.picpay.springbatchdemo;

import org.springframework.batch.item.ItemProcessor;

public class StringParserProcessor implements ItemProcessor<Integer, String> {

    @Override
    public String process(final Integer number) throws Exception {
        if (number == 4) {
            throw new RuntimeException();
        }
        return String.valueOf(number);
    }

}
