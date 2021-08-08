package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.impl;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RecordProcessingResult;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.SimpleProcessor;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.util.RecordIterator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;

public class AnomesdiaProcessor extends SimpleProcessor {

    private static final String DEFAULT_DATE_FORMAT = "yyyyMMdd";

    private static final String FIELD = "anomesdia";

    public AnomesdiaProcessor() {
        super(FIELD);
    }

    @Override
    public RecordProcessingResult processImpl(RecordIterator recordIterator) {


        final String field = recordIterator.next();
        final RecordProcessingResult result = new RecordProcessingResult();
        if (!assertDate(field)) {
            result.setValid(false);
            result.getIncorrectFields()
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
