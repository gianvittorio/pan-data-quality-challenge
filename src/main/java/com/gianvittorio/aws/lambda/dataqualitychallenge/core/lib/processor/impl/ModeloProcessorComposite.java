package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.impl;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RowRecord;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RecordProcessingResult;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.SimpleProcessorComposite;

public class ModeloProcessorComposite extends SimpleProcessorComposite {

    private static final String MODELO_MASK_PATTERN = "^[A-Z]+$";

    private static final String FIELD = "modelo";

    public ModeloProcessorComposite() {
        super(FIELD);
    }

    @Override
    public RecordProcessingResult processImpl(final RowRecord.FieldsIterator fieldsIterator) {
        final String field = fieldsIterator.next();
        final RecordProcessingResult result = new RecordProcessingResult();

        if (!field.matches(MODELO_MASK_PATTERN)) {
            result.setValid(false);
            result.getMissingFIeldsSet()
                    .add(field);
        }

        return result;
    }
}
