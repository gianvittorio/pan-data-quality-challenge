package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.CSVStreamProcessingResult;

import java.io.InputStreamReader;

public interface CSVInputStreamProcessor extends PayloadProcessor<InputStreamReader, CSVStreamProcessingResult> {

    CSVStreamProcessingResult process(final InputStreamReader inputStreamReader);
}
