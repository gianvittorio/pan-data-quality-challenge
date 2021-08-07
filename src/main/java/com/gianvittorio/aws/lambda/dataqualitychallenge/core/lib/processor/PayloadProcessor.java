package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor;

public interface PayloadProcessor<T, V> {

    V process(final T payload);
}
