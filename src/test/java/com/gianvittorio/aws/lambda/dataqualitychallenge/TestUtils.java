package com.gianvittorio.aws.lambda.dataqualitychallenge;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.Record;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public interface TestUtils {

    static Stream<? extends Arguments> argumentsWithValidFields() {

        return Stream.of(
                Arguments.of(new Record("1,YWZ,643822,643,822,,20210331").iterator()),
                Arguments.of(new Record("2,YWZ,3003,3,3,,20210331").iterator()),
                Arguments.of(new Record("3,YWZ,742601,742,601,,20210331").iterator()),
                Arguments.of(new Record("4,YWZ,3003,3,3,,20210331").iterator())
        );
    }

    static Stream<? extends Arguments> argumentsWithDifferentFields() {

        return Stream.of(
                Arguments.of(new Record("X,YWZ,643822,643,822,,20210331").iterator()),
                Arguments.of(new Record("1,5,643822,643,822,,20210331").iterator()),
                Arguments.of(new Record("1,YWZ,643F22,643,822,,20210331").iterator()),
                Arguments.of(new Record("1,YWZ,643822,A43,822,,20210331").iterator()),
                Arguments.of(new Record("1,YWZ,643822,643,B,,20210331").iterator()),
                Arguments.of(new Record("1,YWZ,643822,643,822,,31/03/2021").iterator())
        );
    }

    static Stream<? extends Arguments> argumentsWithMissingFields() {

        return Stream.of(
                Arguments.of(new Record("YWZ,643822,643,822,,2021-03-31").iterator()),
                Arguments.of(new Record("1,643822,643,822,,2021-03-31").iterator()),
                Arguments.of(new Record("1,YWZ,643,822,,2021-03-31").iterator()),
                Arguments.of(new Record("1,YWZ,643822,822,,2021-03-31").iterator()),
                Arguments.of(new Record("1,YWZ,643822,643,,2021-03-31").iterator()),
                Arguments.of(new Record("1,YWZ,643822,643,822,2021-03-31").iterator()),
                Arguments.of(new Record("1,YWZ,643822,643,822,").iterator())
        );
    }

    static Stream<? extends Arguments> argumentsWithAdditionalFields() {

        return Stream.of(
                Arguments.of(new Record("1,YWZ,643822,643,822,,2021-03-31,999").iterator()),
                Arguments.of(new Record("1,YWZ,XXX,643822,643,822,,2021-03-31").iterator()),
                Arguments.of(new Record("999,1,YWZ,643822,643,822,,2021-03-31").iterator()),
                Arguments.of(new Record("1,YWZ,643822,643,822,,2021-03-31,2021-03-01").iterator()),
                Arguments.of(new Record(",,1,YWZ,643822,643,822,,2021-03-31,,").iterator()),
                Arguments.of(new Record("1,YWZ,643822,643,822,,2021-03-31,,").iterator()),
                Arguments.of(new Record("1,YWZ,643822,643,,822,,2021-03-31").iterator())
        );
    }
}
