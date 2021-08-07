package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.impl;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RecordProcessingResult;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.StreamProcessingResult;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.InputStreamProcessor;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.RecordProcessor;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.util.RecordIterator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;

@Log4j2
@RequiredArgsConstructor
public class InputStreamProcessorImpl implements InputStreamProcessor {

    private final RecordProcessor recordProcessor;

    @Override
    public StreamProcessingResult process(final InputStreamReader inputStreamReader) {

        final StringJoiner output = getOutput(inputStreamReader);

        return StreamProcessingResult.builder()
                .payload(output)
                .build();
    }

    private StringJoiner getOutput(final InputStreamReader inputStreamReader) {

        BufferedReader bufferedReader = null;
        String csvOutput = null;

        final StringJoiner sj = new StringJoiner("\n");

        try {
            bufferedReader = new BufferedReader(inputStreamReader);
            int lineCnt = 0;
            while (null != (csvOutput = bufferedReader.readLine())) {

                if (lineCnt++ == 0) {
                    sj.add(csvOutput);

                    continue;
                }

                RecordProcessingResult result = recordProcessor.process(new RecordIterator(csvOutput));
                if (result.isValid()) {
                    sj.add(csvOutput);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return sj;
    }
}
