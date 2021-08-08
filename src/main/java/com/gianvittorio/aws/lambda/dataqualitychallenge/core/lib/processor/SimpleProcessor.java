package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.Record;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RecordProcessingResult;

public abstract class SimpleProcessor extends RecordProcessorComposite {

    public SimpleProcessor(String field) {
        super(field);
    }

    @Override
    public final RecordProcessingResult process(final Record.RecordIterator recordIterator) {

        RecordProcessingResult result = null;
        if (recordIterator == null) {
            return result;
        }

        result = processImpl(recordIterator);

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

    public abstract RecordProcessingResult processImpl(final Record.RecordIterator recordIterator);
}
