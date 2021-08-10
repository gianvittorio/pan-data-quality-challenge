package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.impl;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RowRecord;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RecordProcessingResult;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.SimpleProcessorComposite;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;

public class AnomesdiaProcessorComposite extends SimpleProcessorComposite {

    private static final String DEFAULT_DATE_FORMAT = "yyyyMMdd";

    private static final String FIELD = "anomesdia";

    public AnomesdiaProcessorComposite() {
        super(FIELD);
    }

    @Override
    public RecordProcessingResult processImpl(final RowRecord.FieldsIterator fieldsIterator) {


        final String field = fieldsIterator.next();
        final RecordProcessingResult result = new RecordProcessingResult();
        if (!assertDate(field)) {
            result.setValid(false);
            result.getMissingFIeldsSet()
                    .add(field);
        }

        return result;
    }

    private static boolean assertDate(final String date) {

        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

        boolean isValid = false;
        try {
            final Date parsedDate = simpleDateFormat.parse(date);
            final Date minDate = Date.from(
                    ZonedDateTime.now()
                            .minusYears(1)
                            .toInstant()
            );

            isValid = parsedDate.after(minDate);
        } catch (ParseException e) {

        }

        return isValid;
    }
}
