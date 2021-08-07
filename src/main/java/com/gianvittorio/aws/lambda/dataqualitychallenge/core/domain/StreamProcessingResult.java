package com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.StringJoiner;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StreamProcessingResult extends AbstractResult {

    private int numberOfProcessedFields = 0;
    private StringJoiner payload;
}
