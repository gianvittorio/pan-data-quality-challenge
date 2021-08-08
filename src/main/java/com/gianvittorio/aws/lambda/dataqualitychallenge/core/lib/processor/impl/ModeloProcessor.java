package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.impl;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RecordProcessingResult;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.RecordProcessorComposite;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.util.RecordIterator;

public class ModeloProcessor extends RecordProcessorComposite {

    private static final String MODELO_MASK_PATTERN = "^[A-Z]+$";

    private static final String FIELD = "modelo";

    public ModeloProcessor() {
        super(FIELD);
    }

    @Override
    public RecordProcessingResult process(final RecordIterator recordIterator) {

        RecordProcessingResult result = null;
        if (recordIterator == null) {
            return result;
        }

        final String field = recordIterator.next();
        result = new RecordProcessingResult();
        if (!field.matches(MODELO_MASK_PATTERN)) {
            result.setValid(false);
            result.getIncorrectFields()
                    .add(field);
        }


        final RecordProcessingResult nextResult = super.process(recordIterator);
        if (nextResult != null) {

            result.setNumberOfProcessedFields(1 + nextResult.getNumberOfProcessedFields());
            if (!nextResult.isValid()) {
                result.setValid(false);
                result.getIncorrectFields()
                        .addAll(nextResult.getIncorrectFields());
            }
        }

        return result;
    }
}
