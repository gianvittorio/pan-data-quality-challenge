package com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CSVStreamProcessingResult {

    private boolean isValid;

    private String filePath;

    private String bucketName;

    private StringJoiner message;

    private int numberOfProcessedRows = 0;

    private StringJoiner payload;

    private Set<Number> missingRows = new TreeSet<>();

    private ZonedDateTime createdAt;
}
