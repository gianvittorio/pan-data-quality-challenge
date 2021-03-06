package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.impl;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RowRecord;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RecordProcessingResult;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.RecordProcessorComposite;

public class FirstProcessor extends RecordProcessorComposite {

    public FirstProcessor() {
        super("");
    }

    @Override
    public RecordProcessingResult process(final RowRecord.FieldsIterator fieldsIterator) {

        RecordProcessingResult result = null;
        if (fieldsIterator == null) {
            return result;
        }

        result = new RecordProcessingResult();
        result.setValid(true);

        final RecordProcessingResult nextResult = super.process(fieldsIterator);
        if (nextResult != null) {

            result.setNumberOfProcessedFields(1 + nextResult.getNumberOfProcessedFields());
            if (!nextResult.isValid() || result.getNumberOfProcessedFields() != this.rank) {
                result.setValid(false);
                result.getMissingFIeldsSet()
                        .addAll(nextResult.getMissingFIeldsSet());
            }
        }

        return result;
    }
}
