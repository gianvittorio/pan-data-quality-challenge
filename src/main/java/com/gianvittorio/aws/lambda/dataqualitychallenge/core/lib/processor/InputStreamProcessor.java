package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.StreamProcessingResult;

import java.io.InputStreamReader;

public interface InputStreamProcessor extends PayloadProcessor<InputStreamReader, StreamProcessingResult> {

    StreamProcessingResult process(final InputStreamReader inputStreamReader);
}
