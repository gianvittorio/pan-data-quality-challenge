package com.gianvittorio.aws.lambda.dataqualitychallenge.config;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.CSVInputStreamProcessor;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.RecordProcessor;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RecordProcessorConfiguration {

    @Bean
    public CSVInputStreamProcessor payloadProcessor(final RecordProcessor recordProcessor) {
        return new CSVInputStreamProcessorImpl(recordProcessor);
    }

    @Bean
    public RecordProcessor recordProcessor() {
        final RecordProcessor recordProcessor = new FirstProcessor()
                .addNext(new IdentifMaskProcessorComposite())
                .addNext(new ModeloProcessorComposite())
                .addNext(new ScoreProcessorComposite())
                .addNext(new RestritivoProcessorComposite())
                .addNext(new PositivoProcessorComposite())
                .addNext(new MsgProcessorComposite())
                .addNext(new AnomesdiaProcessorComposite());

        return recordProcessor;
    }
}
