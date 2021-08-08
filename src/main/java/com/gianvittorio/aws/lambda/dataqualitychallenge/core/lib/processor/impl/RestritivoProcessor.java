package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.impl;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.Record;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RecordProcessingResult;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.SimpleProcessor;

public class RestritivoProcessor extends SimpleProcessor {

    private static final String RESTRITIVO_MASK_PATTERN = "^\\d+$";

    private static final String FIELD = "restritivo";

    public RestritivoProcessor() {
        super(FIELD);
    }

    @Override
    public RecordProcessingResult processImpl(final Record.RecordIterator recordIterator) {

        final String field = recordIterator.next();
        final RecordProcessingResult result = new RecordProcessingResult();
        if (!field.matches(RESTRITIVO_MASK_PATTERN)) {
            result.setValid(false);

            result.getIncorrectFields()
                    .add(field);
        }

        return result;
    }
}
