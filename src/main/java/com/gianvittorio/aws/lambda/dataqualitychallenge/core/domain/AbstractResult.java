package com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.StringJoiner;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractResult {

    protected boolean isValid;

    protected StringJoiner message;
}
