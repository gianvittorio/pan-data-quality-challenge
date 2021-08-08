package com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

@Data
@AllArgsConstructor
@Builder
public class StreamProcessingResult extends AbstractResult {

    private int numberOfProcessedFields = 0;
    private StringJoiner payload;

    private Map<Integer, Set<String>> incorrectIds = new HashMap<>();

    public StreamProcessingResult() {
        this.isValid = true;
    }
}
