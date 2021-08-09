package com.gianvittorio.aws.lambda.dataqualitychallenge.repository.impl;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.StreamProcessingResult;
import com.gianvittorio.aws.lambda.dataqualitychallenge.repository.ReportsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import software.amazon.ion.Timestamp;

@Repository
@RequiredArgsConstructor
public class ReportsRepositoryImpl implements ReportsRepository {

    private final Table reportsTable;

    @Override
    public void putObject(final String filepath, final StreamProcessingResult result) {

        final Item item = new Item()
                .withPrimaryKey("filepath", filepath)
                .withString("created_at", Timestamp.now().toString())
                .withNumberSet("incorrect_lines", result.getIncorrectIds());

        reportsTable.putItem(item);
    }
}
