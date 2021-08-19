package com.picpay.springbatchdemo;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;

public class MySkipPolicy implements SkipPolicy {

    @Override
    public boolean shouldSkip(final Throwable throwable, final int i) throws SkipLimitExceededException {
        return true;
    }

}
