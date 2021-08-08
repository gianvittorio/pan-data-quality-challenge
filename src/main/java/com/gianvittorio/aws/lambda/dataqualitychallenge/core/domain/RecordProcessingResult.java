package com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@Builder
public class RecordProcessingResult extends AbstractResult {

    private int numberOfProcessedFields = 0;

    private Set<String> incorrectFields = new HashSet<>();

    public RecordProcessingResult() {
        this.isValid = true;
    }
}
