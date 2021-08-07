package com.gianvittorio.aws.lambda.dataqualitychallenge.unit.core.lib.processor;

import com.gianvittorio.aws.lambda.dataqualitychallenge.TestUtils;
import com.gianvittorio.aws.lambda.dataqualitychallenge.config.RecordProcessorConfiguration;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RecordProcessingResult;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.RecordProcessor;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.util.RecordIterator;
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
public class RecordProcessorTest {

    @Autowired
    RecordProcessor recordProcessor;

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
            new RecordIterator(null);
        });

        // Then
        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Return valid result whenever arg recordIterator is valid")
    public void whenRecordIsValidThenReturnValidResult() {

        // Given

        // When
        final String line = "1,YWZ,643822,643,822,,2021-03-31";
        final RecordIterator recordIterator = new RecordIterator(line);

        final RecordProcessingResult result = recordProcessor.process(recordIterator);

        // Then
        assertThat(result)
                .isNotNull();
        assertThat(result.isValid())
                .isTrue();
    }

    @ParameterizedTest(name = "{index} => recordIterator={0}")
    @MethodSource("argumentsWithMissingFields")
    @DisplayName("Return invalid result whenever arg recordIterator misses any fields")
    public void whenRecordMissesFieldThenReturnInvalidResult(RecordIterator recordIterator) {

        // Given

        // When
        final RecordProcessingResult result = recordProcessor.process(recordIterator);

        // Then
        assertThat(result)
                .isNotNull();
        assertThat(result.isValid())
                .isFalse();
    }

    @ParameterizedTest(name = "{index} => recordIterator={0}")
    @MethodSource("argumentsWithMissingFields")
    @DisplayName("Return invalid result whenever arg recordIterator has any additional")
    public void whenRecordhasAdditionalFieldThenReturnInvalidResult(RecordIterator recordIterator) {

        // Given

        // When
        final RecordProcessingResult result = recordProcessor.process(recordIterator);

        // Then
        assertThat(result)
                .isNotNull();
        assertThat(result.isValid())
                .isFalse();
    }

    static Stream<? extends Arguments> argumentsWithMissingFields() {

        return TestUtils.argumentsWithMissingFields();
    }

    static Stream<? extends Arguments> argumentsWithAdditionalFields() {

        return TestUtils.argumentsWithAdditionalFields();
    }
}
