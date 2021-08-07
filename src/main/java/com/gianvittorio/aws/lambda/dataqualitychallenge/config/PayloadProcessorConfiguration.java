package com.gianvittorio.aws.lambda.dataqualitychallenge.config;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.InputStreamProcessor;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.impl.InputStreamProcessorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PayloadProcessorConfiguration {

    @Bean
    public InputStreamProcessor payloadProcessor() {
        return new InputStreamProcessorImpl();
    }
}
