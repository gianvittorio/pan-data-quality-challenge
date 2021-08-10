package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.impl;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RowRecord;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RecordProcessingResult;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.SimpleProcessorComposite;

public class RestritivoProcessorComposite extends SimpleProcessorComposite {

    private static final String RESTRITIVO_MASK_PATTERN = "^\\d+$";

    private static final String FIELD = "restritivo";

    public RestritivoProcessorComposite() {
        super(FIELD);
    }

    @Override
    public RecordProcessingResult processImpl(final RowRecord.FieldsIterator fieldsIterator) {

        final String field = fieldsIterator.next();
        final RecordProcessingResult result = new RecordProcessingResult();
        if (!field.matches(RESTRITIVO_MASK_PATTERN)) {
            result.setValid(false);

            result.getMissingFIeldsSet()
                    .add(field);
        }

        return result;
    }
}
