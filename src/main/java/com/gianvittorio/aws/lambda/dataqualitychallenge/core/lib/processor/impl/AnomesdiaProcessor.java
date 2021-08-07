package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.impl;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.Result;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.RecordProcessorComposite;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.util.RecordIterator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;

public class AnomesdiaProcessor extends RecordProcessorComposite {

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public Result process(final RecordIterator recordIterator) {

        Result result = null;
        if (recordIterator == null) {
            return result;
        }


        final String field = recordIterator.next();
        result = new Result();
        result.setValid(assertDate(field));

        final Result nextResult = super.process(recordIterator);
        if (nextResult != null && !nextResult.isValid()) {
            result = new Result();
            result.setValid(false);
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
