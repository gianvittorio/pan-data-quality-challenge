package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.impl;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.Result;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.PayloadProcessor;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;

@Log4j2
public class PayloadProcessorImpl implements PayloadProcessor {

    @Override
    public Result process(final InputStreamReader inputStreamReader) {

        final StringJoiner output = getOutput(inputStreamReader);

        return Result.builder()
                .payload(output)
                .build();
    }

    private StringJoiner getOutput(final InputStreamReader inputStreamReader) {

        BufferedReader bufferedReader = null;
        String csvOutput = null;

        final StringJoiner sj = new StringJoiner("\n");

        try {
            bufferedReader = new BufferedReader(inputStreamReader);

            while (null != (csvOutput = bufferedReader.readLine())) {
                sj.add(csvOutput);
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
