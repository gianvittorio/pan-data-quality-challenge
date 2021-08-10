package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.impl;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RowRecord;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RecordProcessingResult;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.SimpleProcessorComposite;

public class MsgProcessorComposite extends SimpleProcessorComposite {

    private static final String FIELD = "msg";

    public MsgProcessorComposite() {
        super(FIELD);
    }

    @Override
    public RecordProcessingResult processImpl(final RowRecord.FieldsIterator fieldsIterator) {

        final String field = fieldsIterator.next();
        final RecordProcessingResult result = new RecordProcessingResult();
        result.setValid(true);

        return result;
    }
}
