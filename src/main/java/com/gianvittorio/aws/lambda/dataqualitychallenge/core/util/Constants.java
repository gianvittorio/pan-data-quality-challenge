package com.gianvittorio.aws.lambda.dataqualitychallenge.core.util;

public interface Constants {

    String FILE_FORMAT = "csv";

    String DEFAULT_INPUT_DIRECTORY = "inbound";

    String DEFAULT_OUTPUT_DIRECTORY = "outbound";

    String DEFAULT_SUFFIX = "_CORRECT";

    String DEFAULT_SEPARATOR = ".";

    String DEFAULT_INVALID_FORMAT_ERROR_MSG = "ERROR: file format was incorrect";

    String SUCCESSFUL = "successful";

    String UNSUCCESSFUL = "unsuccessful";
}
