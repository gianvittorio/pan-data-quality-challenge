package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RowRecord;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RecordProcessingResult;

public interface RecordProcessor extends PayloadProcessor<RowRecord.FieldsIterator, RecordProcessingResult>{
}
