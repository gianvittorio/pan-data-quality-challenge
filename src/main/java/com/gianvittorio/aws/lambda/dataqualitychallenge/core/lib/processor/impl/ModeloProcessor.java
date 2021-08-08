package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.impl;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.Record;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RecordProcessingResult;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.SimpleProcessor;

public class ModeloProcessor extends SimpleProcessor {

    private static final String MODELO_MASK_PATTERN = "^[A-Z]+$";

    private static final String FIELD = "modelo";

    public ModeloProcessor() {
        super(FIELD);
    }

    @Override
    public RecordProcessingResult processImpl(final Record.RecordIterator recordIterator) {
        final String field = recordIterator.next();
        final RecordProcessingResult result = new RecordProcessingResult();

        if (!field.matches(MODELO_MASK_PATTERN)) {
            result.setValid(false);
            result.getIncorrectFields()
                    .add(field);
        }

        return result;
    }
}
