package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.impl;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.Record;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RecordProcessingResult;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.SimpleProcessor;


public class IdentifMaskProcessor extends SimpleProcessor {

    private static final String IDENTIF_MASK_PATTERN = "^\\d+$";

    private static final String FIELD = "identif_mask";

    public IdentifMaskProcessor() {
        super(FIELD);
    }

    @Override
    public RecordProcessingResult processImpl(final Record.RecordIterator recordIterator) {

        final String field = recordIterator.next();

        final RecordProcessingResult result = new RecordProcessingResult();
        if (!field.matches(IDENTIF_MASK_PATTERN)) {
            result.setValid(false);

            result.getIncorrectFields()
                    .add(field);
        }

        return result;
    }
}
