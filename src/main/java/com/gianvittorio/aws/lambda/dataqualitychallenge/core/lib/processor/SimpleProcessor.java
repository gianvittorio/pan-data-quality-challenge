package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.Record;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RecordProcessingResult;

public abstract class SimpleProcessor extends RecordProcessorComposite {

    public SimpleProcessor(String field) {
        super(field);
    }

    @Override
    public final RecordProcessingResult process(final Record.FieldsIterator fieldsIterator) {

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
                result.getIncorrectFields()
                        .addAll(nextResult.getIncorrectFields());
            }
        }

        return result;
    }

    public abstract RecordProcessingResult processImpl(final Record.FieldsIterator fieldsIterator);
}
