package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.impl;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.Record;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RecordProcessingResult;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.SimpleProcessor;

public class MsgProcessor extends SimpleProcessor {

    private static final String FIELD = "msg";

    public MsgProcessor() {
        super(FIELD);
    }

    @Override
    public RecordProcessingResult processImpl(final Record.FieldsIterator fieldsIterator) {

        final String field = fieldsIterator.next();
        final RecordProcessingResult result = new RecordProcessingResult();
        result.setValid(true);

        return result;
    }
}
