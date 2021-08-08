package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.impl;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RecordProcessingResult;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.RecordProcessorComposite;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.util.RecordIterator;

public class FirstProcessor extends RecordProcessorComposite {

    public FirstProcessor() {
        super("");
    }

    @Override
    public RecordProcessingResult process(final RecordIterator recordIterator) {

        RecordProcessingResult result = null;
        if (recordIterator == null) {
            return result;
        }

        result = new RecordProcessingResult();
        result.setValid(true);

        final RecordProcessingResult nextResult = super.process(recordIterator);
        if (nextResult != null) {

            result.setNumberOfProcessedFields(1 + nextResult.getNumberOfProcessedFields());
            if (!nextResult.isValid() || result.getNumberOfProcessedFields() != this.rank) {
                result.setValid(false);
                result.getIncorrectFields()
                        .addAll(nextResult.getIncorrectFields());
            }
        }

        return result;
    }
}
