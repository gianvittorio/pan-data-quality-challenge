package com.gianvittorio.aws.lambda.dataqualitychallenge.repository;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.StreamProcessingResult;

public interface ReportsRepository {

    void putObject(final String filepath, final StreamProcessingResult result);
}
