package com.picpay.springbatchdemo;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class PrinterWriter implements ItemWriter<String> {

    @Override
    public void write(final List<? extends String> list) throws Exception {
        System.out.println(list);
    }

}
