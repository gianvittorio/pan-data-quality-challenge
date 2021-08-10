package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RowRecord;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RecordProcessingResult;

public abstract class SimpleProcessorComposite extends RecordProcessorComposite {

    public SimpleProcessorComposite(String field) {
        super(field);
    }

    @Override
    public final RecordProcessingResult process(final RowRecord.FieldsIterator fieldsIterator) {

        RecordProcessingResult result = null;
        if (fieldsIterator == null) {
            return result;
        }

        result = processImpl(fieldsIterator);

        final RecordProcessingResult nextResult = super.process(fieldsIterator);
        if (nextResult != null) {

            result.setNumberOfProcessedFields(1 + nextResult.getNumberOfProcessedFields());
            if (!nextResult.isValid()) {
                result.setValid(false);
                result.getMissingFIeldsSet()
                        .addAll(nextResult.getMissingFIeldsSet());
            }
        }

        return result;
    }

    public abstract RecordProcessingResult processImpl(final RowRecord.FieldsIterator fieldsIterator);
}
