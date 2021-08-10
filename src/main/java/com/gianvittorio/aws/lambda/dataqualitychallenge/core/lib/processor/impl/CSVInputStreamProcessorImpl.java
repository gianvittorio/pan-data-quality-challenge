package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.impl;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RowRecord;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RecordProcessingResult;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.CSVStreamProcessingResult;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.CSVInputStreamProcessor;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.RecordProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;

@Log4j2
@RequiredArgsConstructor
public class CSVInputStreamProcessorImpl implements CSVInputStreamProcessor {

    private final RecordProcessor recordProcessor;

    @Override
    public CSVStreamProcessingResult process(final InputStreamReader inputStreamReader) {

        final CSVStreamProcessingResult csvStreamProcessingResult = new CSVStreamProcessingResult();

        BufferedReader bufferedReader = null;
        String csvOutput = null;

        final StringJoiner payload = new StringJoiner("\n");

        boolean isValid = true;
        try {
            bufferedReader = new BufferedReader(inputStreamReader);
            int lineCnt = 0;

            while (null != (csvOutput = bufferedReader.readLine())) {
                // Ignore header, for now at least...
                if (lineCnt++ == 0) {
                    payload.add(csvOutput);

                    continue;
                }

                RowRecord rowRecord = new RowRecord(csvOutput);
                RecordProcessingResult result = recordProcessor.process(rowRecord.iterator());
                if (!result.isValid()) {
                    isValid = false;

                    final Integer identifMask = Integer.parseInt(rowRecord.getFields()[0]);

                    csvStreamProcessingResult.getMissingRows()
                                    .add(identifMask);

                    log.info("INVALID: " + rowRecord.getFields()[0] + " : " + csvStreamProcessingResult.getMissingRows());

                    continue;
                }

                payload.add(csvOutput);
            }
        } catch (Exception e) {
            log.error(e.getMessage());

            isValid = false;
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        csvStreamProcessingResult.setPayload(payload);
        csvStreamProcessingResult.setValid(isValid);

        return csvStreamProcessingResult;
    }
}
