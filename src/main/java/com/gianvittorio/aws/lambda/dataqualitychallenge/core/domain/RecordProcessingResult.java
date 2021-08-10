package com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecordProcessingResult {

    private boolean isValid = true;

    private int numberOfProcessedFields = 0;

    private StringJoiner message;

    private Set<String> missingFIeldsSet = new HashSet<>();
}
