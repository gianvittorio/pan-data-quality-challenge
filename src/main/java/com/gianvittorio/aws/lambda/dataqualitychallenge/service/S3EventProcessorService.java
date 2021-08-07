package com.gianvittorio.aws.lambda.dataqualitychallenge.service;

import com.amazonaws.services.lambda.runtime.events.S3Event;

public interface S3EventProcessorService {

    void process(final S3Event event);
}
