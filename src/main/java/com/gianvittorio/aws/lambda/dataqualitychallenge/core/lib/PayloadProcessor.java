package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.Result;

import java.io.IOException;
import java.io.InputStreamReader;

public interface PayloadProcessor {

    Result process(final InputStreamReader inputStreamReader) throws IOException;
}
