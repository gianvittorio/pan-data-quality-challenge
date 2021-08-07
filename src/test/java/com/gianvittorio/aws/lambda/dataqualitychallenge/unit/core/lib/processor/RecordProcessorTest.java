package com.gianvittorio.aws.lambda.dataqualitychallenge.unit.core.lib.processor;

import com.gianvittorio.aws.lambda.dataqualitychallenge.config.RecordProcessorConfiguration;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.Result;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor.RecordProcessor;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.util.RecordIterator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
        Result result = recordProcessor.process(null);

        // Then
        assertThat(result)
                .isNull();
    }

    @Test
    @DisplayName("Return valid result whenever arg recordIterator is valid")
    public void whenRecordIsValidThenReturnSameRecord() {

        // Given

        // When
        final String line = "1,YWZ,643822,643,822,,2021-03-31";
        final RecordIterator recordIterator = new RecordIterator(line);

        Result result = recordProcessor.process(recordIterator);

        // Then
        assertThat(result)
                .isNotNull();
        assertThat(result.isValid())
                .isTrue();
    }
}
