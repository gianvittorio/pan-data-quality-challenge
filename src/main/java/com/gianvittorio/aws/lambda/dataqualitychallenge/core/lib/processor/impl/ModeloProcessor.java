package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.impl;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.Result;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.RecordProcessorComposite;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.util.RecordIterator;

public class ModeloProcessor extends RecordProcessorComposite {

    private static final String MODELO_MASK_PATTERN = "^[A-Z]+$";

    @Override
    public Result process(final RecordIterator recordIterator) {

        Result result = null;
        if (recordIterator == null) {
            return result;
        }

        final String field = recordIterator.next();
        result = new Result();
        result.setValid(field.matches(MODELO_MASK_PATTERN));

        final Result nextResult = super.process(recordIterator);
        if (nextResult != null && !nextResult.isValid()) {
            result.setValid(false);
        }

        return result;
    }
}
