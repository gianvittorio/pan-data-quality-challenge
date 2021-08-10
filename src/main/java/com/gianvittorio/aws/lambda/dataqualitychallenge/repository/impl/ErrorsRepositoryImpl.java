package com.gianvittorio.aws.lambda.dataqualitychallenge.repository.impl;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.CSVStreamProcessingResult;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.util.Constants;
import com.gianvittorio.aws.lambda.dataqualitychallenge.repository.ErrorsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
@RequiredArgsConstructor
public class ErrorsRepositoryImpl implements ErrorsRepository {

    private Table errorsTable;

    private final DynamoDB dynamoDB;

    @PostConstruct
    public void init() {
        this.errorsTable = dynamoDB.getTable(Constants.ERRORS_TABLE_NAME);
    }

    @Override
    public void putObject(final CSVStreamProcessingResult result) {

        final Item item = new Item()
                .withPrimaryKey("filepath", String.format("%s/%s", result.getBucketName(), result.getFilePath()))
                .withString("created_at", result.getCreatedAt().toString())
                .withNumberSet("missing_rows", result.getMissingRows());

        errorsTable.putItem(item);
    }
}
