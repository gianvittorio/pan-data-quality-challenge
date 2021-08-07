package com.gianvittorio.aws.lambda.dataqualitychallenge.function;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.gianvittorio.aws.lambda.dataqualitychallenge.service.S3EventProcessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Function;

import static com.gianvittorio.aws.lambda.dataqualitychallenge.core.util.Constants.SUCCESSFUL;
import static com.gianvittorio.aws.lambda.dataqualitychallenge.core.util.Constants.UNSUCCESSFUL;

@Slf4j
@Component("awsLambdaS3Function")
@RequiredArgsConstructor
public class AwsLambdaS3Function implements Function<S3Event, String> {

    private final S3EventProcessorService s3EventProcessorService;

    @Override
    public String apply(S3Event event) {

        log.info("Received event: {}", event);

        String result = SUCCESSFUL;
        try {
            s3EventProcessorService.process(event);
        } catch (Exception e) {
            log.error(e.getMessage());

            result = UNSUCCESSFUL;
        }

        return result;
    }
}
