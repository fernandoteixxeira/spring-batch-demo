package com.picpay.springbatchdemo;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.Iterator;
import java.util.List;

public class NumberReader implements ItemReader<Integer> {

    private final Iterator<Integer> numbers = List.of(1,2,3,4,5).iterator();

    @Override
    public Integer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (!numbers.hasNext()) {
            return null;
        }
        return numbers.next();
    }

}
