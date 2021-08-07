package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.Record;

public interface RecordExtractor {

    Record extract(final String line);
}
