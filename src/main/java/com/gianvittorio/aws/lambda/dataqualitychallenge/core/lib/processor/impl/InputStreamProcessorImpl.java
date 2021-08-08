package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.impl;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.Record;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RecordProcessingResult;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.StreamProcessingResult;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.InputStreamProcessor;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.RecordProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringJoiner;

@Log4j2
@RequiredArgsConstructor
public class InputStreamProcessorImpl implements InputStreamProcessor {

    private final RecordProcessor recordProcessor;

    @Override
    public StreamProcessingResult process(final InputStreamReader inputStreamReader) {

        final StreamProcessingResult streamProcessingResult = new StreamProcessingResult();

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

                Record record = new Record(csvOutput);
                RecordProcessingResult result = recordProcessor.process(record.iterator());
                if (!result.isValid()) {
                    isValid = false;

                    streamProcessingResult.getIncorrectIds()
                            .computeIfAbsent(Integer.parseInt(record.getFields()[0]), id -> new HashSet<>())
                            .addAll(result.getIncorrectFields());

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

        streamProcessingResult.setPayload(payload);
        streamProcessingResult.setValid(isValid);

        return streamProcessingResult;
    }
}
