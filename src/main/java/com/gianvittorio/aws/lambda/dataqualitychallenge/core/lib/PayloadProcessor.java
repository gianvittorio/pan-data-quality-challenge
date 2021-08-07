package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.Result;

public interface PayloadProcessor<T> {

    Result process(final T payload);
}
