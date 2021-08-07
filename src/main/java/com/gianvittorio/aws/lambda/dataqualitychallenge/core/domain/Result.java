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
public class Result {

    private boolean isValid;
    private StringJoiner payload;
    private StringJoiner message;
}
