package com.gianvittorio.aws.lambda.dataqualitychallenge.repository;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.CSVStreamProcessingResult;

public interface ErrorsRepository {

    void putObject( final CSVStreamProcessingResult result);
}
