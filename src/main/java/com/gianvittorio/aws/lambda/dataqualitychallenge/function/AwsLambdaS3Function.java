package com.gianvittorio.aws.lambda.dataqualitychallenge.function;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Slf4j
@Component("awsLambdaS3Function")
public class AwsLambdaS3Function implements Function<S3Event, String> {
    @Override
    public String apply(S3Event s3Event) {

        log.info("Ok Jack");

        return "ok";
    }
}
