package com.gianvittorio.aws.lambda.dataqualitychallenge.config;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.PayloadProcessor;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.impl.PayloadProcessorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PayloadProcessorConfiguration {

    @Bean
    public PayloadProcessor payloadProcessor() {
        return new PayloadProcessorImpl();
    }
}
