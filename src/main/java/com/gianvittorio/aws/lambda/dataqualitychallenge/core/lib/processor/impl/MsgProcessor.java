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
    public RecordProcessingResult processImpl(final Record.RecordIterator recordIterator) {

        final String field = recordIterator.next();
        final RecordProcessingResult result = new RecordProcessingResult();
        result.setValid(true);

        return result;
    }
}
