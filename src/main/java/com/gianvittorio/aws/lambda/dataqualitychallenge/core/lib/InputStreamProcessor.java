package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.Result;

import java.io.InputStreamReader;

public interface InputStreamProcessor extends PayloadProcessor<InputStreamReader> {

    Result process(final InputStreamReader inputStreamReader);
}
