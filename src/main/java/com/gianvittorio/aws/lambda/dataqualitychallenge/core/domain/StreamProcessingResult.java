package com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.*;

@Data
@AllArgsConstructor
@Builder
public class StreamProcessingResult extends AbstractResult {

    private int numberOfProcessedFields = 0;
    private StringJoiner payload;

    private Set<Number> incorrectIds = new HashSet<>();

    public StreamProcessingResult() {
        this.isValid = true;
    }
}
