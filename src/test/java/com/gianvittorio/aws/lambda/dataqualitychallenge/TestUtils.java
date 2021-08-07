package com.gianvittorio.aws.lambda.dataqualitychallenge;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.util.RecordIterator;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public interface TestUtils {

    static Stream<? extends Arguments> argumentsWithMissingFields() {

        return Stream.of(
                Arguments.of(new RecordIterator("YWZ,643822,643,822,,2021-03-31")),
                Arguments.of(new RecordIterator("1,643822,643,822,,2021-03-31")),
                Arguments.of(new RecordIterator("1,YWZ,643,822,,2021-03-31")),
                Arguments.of(new RecordIterator("1,YWZ,643822,822,,2021-03-31")),
                Arguments.of(new RecordIterator("1,YWZ,643822,643,,2021-03-31")),
                Arguments.of(new RecordIterator("1,YWZ,643822,643,822,2021-03-31")),
                Arguments.of(new RecordIterator("1,YWZ,643822,643,822,"))
        );
    }

    static Stream<? extends Arguments> argumentsWithAdditionalFields() {

        return Stream.of(
                Arguments.of(new RecordIterator("1,YWZ,643822,643,822,,2021-03-31,999")),
                Arguments.of(new RecordIterator("1,YWZ,XXX,643822,643,822,,2021-03-31")),
                Arguments.of(new RecordIterator("999,1,YWZ,643822,643,822,,2021-03-31")),
                Arguments.of(new RecordIterator("1,YWZ,643822,643,822,,2021-03-31,2021-03-01")),
                Arguments.of(new RecordIterator(",,1,YWZ,643822,643,822,,2021-03-31,,")),
                Arguments.of(new RecordIterator("1,YWZ,643822,643,822,,2021-03-31,,")),
                Arguments.of(new RecordIterator("1,YWZ,643822,643,,822,,2021-03-31"))
        );
    }
}
