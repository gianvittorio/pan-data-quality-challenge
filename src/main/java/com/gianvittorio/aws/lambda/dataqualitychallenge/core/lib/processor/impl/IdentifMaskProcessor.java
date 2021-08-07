package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.impl;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.Result;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.RecordProcessorComposite;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.util.RecordIterator;


public class IdentifMaskProcessor extends RecordProcessorComposite {

    private static final String IDENTIF_MASK_PATTERN = "^\\d+$";

    @Override
    public Result process(final RecordIterator recordIterator) {

        Result result = null;
        if (recordIterator == null) {
            return result;
        }

        final String field = recordIterator.next();
        result = new Result();
        result.setValid(field.matches(IDENTIF_MASK_PATTERN));

        final Result nextResult = super.process(recordIterator);
        if (nextResult != null && !nextResult.isValid()) {
            result.setValid(false);
        }

        return result;
    }
}
