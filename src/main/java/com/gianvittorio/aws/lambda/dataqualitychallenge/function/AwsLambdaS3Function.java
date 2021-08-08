package com.gianvittorio.aws.lambda.dataqualitychallenge.function;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.gianvittorio.aws.lambda.dataqualitychallenge.service.S3EventProcessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.function.Function;

import static com.gianvittorio.aws.lambda.dataqualitychallenge.core.util.Constants.SUCCESSFUL;
import static com.gianvittorio.aws.lambda.dataqualitychallenge.core.util.Constants.UNSUCCESSFUL;

@Log4j2
@Component("awsLambdaS3Function")
@RequiredArgsConstructor
public class AwsLambdaS3Function implements Function<S3Event, String> {

    private final S3EventProcessorService s3EventProcessorService;

    @Override
    public String apply(S3Event event) {

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
