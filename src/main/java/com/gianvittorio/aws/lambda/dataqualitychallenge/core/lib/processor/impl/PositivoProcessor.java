package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.impl;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.Record;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RecordProcessingResult;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.SimpleProcessor;

public class PositivoProcessor extends SimpleProcessor {

    private static final String POSITIVO_MASK_PATTERN = "^\\d+$";

    private static final String FIELD = "positivo";

    public PositivoProcessor() {
        super(FIELD);
    }

    @Override
    public RecordProcessingResult processImpl(final Record.FieldsIterator fieldsIterator) {

        final String field = fieldsIterator.next();
        final RecordProcessingResult result = new RecordProcessingResult();
        if (!field.matches(POSITIVO_MASK_PATTERN)) {
            result.setValid(false);

            result.getIncorrectFields()
                    .add(field);
        }

        return result;
    }
}
