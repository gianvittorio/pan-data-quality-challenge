package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.impl;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.Record;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RecordProcessingResult;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.SimpleProcessor;

public class ScoreProcessor extends SimpleProcessor {

    private static final String SCORE_MASK_PATTERN = "^\\d+$";

    private static final String FIELD = "score";

    public ScoreProcessor() {
        super(FIELD);
    }

    @Override
    public RecordProcessingResult processImpl(final Record.FieldsIterator fieldsIterator) {

        final String field = fieldsIterator.next();
        final RecordProcessingResult result = new RecordProcessingResult();

        if (!field.matches(SCORE_MASK_PATTERN)) {
            result.setValid(false);

            result.getIncorrectFields()
                    .add(field);
        }

        return result;
    }
}
