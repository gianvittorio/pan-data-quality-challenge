package com.gianvittorio.aws.lambda.dataqualitychallenge.unit.core.lib.processor;

import com.gianvittorio.aws.lambda.dataqualitychallenge.TestUtils;
import com.gianvittorio.aws.lambda.dataqualitychallenge.config.RecordProcessorConfiguration;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RowRecord;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RecordProcessingResult;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.RecordProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RecordProcessorConfiguration.class})
public class RowRecordProcessorTest {

    @Autowired
    RecordProcessor recordProcessor;

    @ParameterizedTest(name = "{index} => fieldsIterator={0}")
    @MethodSource("argumentsWitValidFields")
    @DisplayName("Return valid result whenever arg fieldsIterator is valid")
    public void whenRecordIsValidThenReturnValidResult(final RowRecord.FieldsIterator fieldsIterator) {

        // Given

        // When
        final RecordProcessingResult result = recordProcessor.process(fieldsIterator);

        // Then
        assertThat(result)
                .isNotNull();
        assertThat(result.isValid())
                .isTrue();
    }

    @Test
    @DisplayName("Return null result whenever arg recordIterator is null")
    public void whenRecordIsNullThenReturnNull() {

        // Given

        // When
        final RecordProcessingResult result = recordProcessor.process(null);

        // Then
        assertThat(result)
                .isNull();
    }

    @Test
    @DisplayName("Throw IllegalArgumentException whenever arg recordIterator is is initialized with null")
    public void whenRecordIsNullThenThrow() {

        // Given

        // When
        Throwable throwable = catchThrowable(() -> {
            new RowRecord(null).iterator();
        });

        // Then
        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "{index} => fieldsIterator={0}")
    @MethodSource("argumentsWitDifferentFields")
    @DisplayName("Return invalid result whenever arg fieldsIterator has any non matching field")
    public void whenRecordHasFieldWrongFormatThenReturnInvalidResult(final RowRecord.FieldsIterator fieldsIterator) {

        // Given

        // When
        final RecordProcessingResult result = recordProcessor.process(fieldsIterator);

        // Then
        assertThat(result)
                .isNotNull();
        assertThat(result.isValid())
                .isFalse();
    }


    @ParameterizedTest(name = "{index} => fieldsIterator={0}")
    @MethodSource("argumentsWithMissingFields")
    @DisplayName("Return invalid result whenever arg fieldsIterator misses any fields")
    public void whenRecordMissesFieldThenReturnInvalidResult(final RowRecord.FieldsIterator fieldsIterator) {

        // Given

        // When
        final RecordProcessingResult result = recordProcessor.process(fieldsIterator);

        // Then
        assertThat(result)
                .isNotNull();
        assertThat(result.isValid())
                .isFalse();
    }

    @ParameterizedTest(name = "{index} => fieldsIterator={0}")
    @MethodSource("argumentsWithMissingFields")
    @DisplayName("Return invalid result whenever arg fieldsIterator has any additional")
    public void whenRecordhasAdditionalFieldThenReturnInvalidResult(final RowRecord.FieldsIterator fieldsIterator) {

        // Given

        // When
        final RecordProcessingResult result = recordProcessor.process(fieldsIterator);

        // Then
        assertThat(result)
                .isNotNull();
        assertThat(result.isValid())
                .isFalse();
    }

    static Stream<? extends Arguments> argumentsWitValidFields() {

        return TestUtils.argumentsWithValidFields();
    }

    static Stream<? extends Arguments> argumentsWitDifferentFields() {

        return TestUtils.argumentsWithDifferentFields();
    }

    static Stream<? extends Arguments> argumentsWithMissingFields() {

        return TestUtils.argumentsWithMissingFields();
    }

    static Stream<? extends Arguments> argumentsWithAdditionalFields() {

        return TestUtils.argumentsWithAdditionalFields();
    }
}
