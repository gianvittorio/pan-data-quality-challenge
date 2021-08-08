package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.impl;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RecordProcessingResult;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.RecordProcessorComposite;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.util.RecordIterator;

public class PositivoProcessor extends RecordProcessorComposite {

    private static final String POSITIVO_MASK_PATTERN = "^\\d+$";

    private static final String FIELD = "positivo";

    public PositivoProcessor() {
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
        if (!field.matches(POSITIVO_MASK_PATTERN)) {
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
