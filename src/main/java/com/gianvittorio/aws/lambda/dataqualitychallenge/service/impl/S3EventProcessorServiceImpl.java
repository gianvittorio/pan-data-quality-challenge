package com.gianvittorio.aws.lambda.dataqualitychallenge.service.impl;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.event.S3EventNotification.S3EventNotificationRecord;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.StreamProcessingResult;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.InputStreamProcessor;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.util.Constants;
import com.gianvittorio.aws.lambda.dataqualitychallenge.repository.ReportsRepository;
import com.gianvittorio.aws.lambda.dataqualitychallenge.service.S3EventProcessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static com.amazonaws.services.s3.event.S3EventNotification.S3Entity;
import static com.gianvittorio.aws.lambda.dataqualitychallenge.core.util.Constants.*;

@Log4j2
@Service
@RequiredArgsConstructor
public class S3EventProcessorServiceImpl implements S3EventProcessorService {

    private final AmazonS3 s3Client;

    private final ReportsRepository reportsRepository;

    private final InputStreamProcessor payloadProcessor;

    @Override
    public void process(final S3Event event) {

        try {
            final S3EventNotificationRecord record = event.getRecords()
                    .get(0);
            final S3Entity s3Entity = record.getS3();
            final String bucketName = s3Entity.getBucket()
                    .getName();
            final String inputFilePath = s3Entity.getObject()
                    .getKey();

            log.info("Processing: inputFilePath: {}; bucketName: {}", inputFilePath, bucketName);

            final S3Object response = s3Client.getObject(new GetObjectRequest(bucketName, inputFilePath));
            final String contentType = response.getObjectMetadata()
                    .getContentType();
            if (!contentType.endsWith(FILE_FORMAT)) {
                RuntimeException invalidFormatException = new RuntimeException(Constants.DEFAULT_INVALID_FORMAT_ERROR_MSG);

                log.error(invalidFormatException.getMessage());

                throw invalidFormatException;
            }

            final StreamProcessingResult result = payloadProcessor.process(new InputStreamReader(response.getObjectContent()));

            final byte[] output = result
                    .getPayload()
                    .toString()
                    .getBytes(StandardCharsets.UTF_8);

            final ByteArrayInputStream inputStream = new ByteArrayInputStream(output);

            final String outputFilePath = getOutputFilePath(inputFilePath);

            log.info("Creating output file: {}; within {}", outputFilePath, bucketName);

            s3Client.putObject(bucketName, outputFilePath, inputStream, new ObjectMetadata());

            reportsRepository.putObject(outputFilePath, result);

            log.info("Created output file: {}; within {}", outputFilePath, bucketName);

        } catch (Exception e) {
            log.error(e.getMessage());

            throw new RuntimeException(e);
        }
    }

    private static String getOutputFilePath(final String inputFilePath) {

        return inputFilePath
                .replace(
                        DEFAULT_SEPARATOR.concat(FILE_FORMAT),
                        DEFAULT_SUFFIX.concat(DEFAULT_SEPARATOR)
                                .concat(FILE_FORMAT)
                )
                .replace(DEFAULT_INPUT_DIRECTORY, DEFAULT_OUTPUT_DIRECTORY);
    }
}
