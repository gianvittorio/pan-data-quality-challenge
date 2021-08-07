package com.gianvittorio.aws.lambda.dataqualitychallenge.config;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.InputStreamProcessor;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.RecordProcessor;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RecordProcessorConfiguration {

    @Bean
    public InputStreamProcessor payloadProcessor(final RecordProcessor recordProcessor) {
        return new InputStreamProcessorImpl(recordProcessor);
    }

    @Bean
    public RecordProcessor recordProcessor() {
        final RecordProcessor recordProcessor = new FirstProcessor()
                .addNext(new IdentifMaskProcessor())
                .addNext(new ModeloProcessor())
                .addNext(new ScoreProcessor())
                .addNext(new RestritivoProcessor())
                .addNext(new PositivoProcessor())
                .addNext(new MsgProcessor())
                .addNext(new AnomesdiaProcessor());

        return recordProcessor;
    }
}
