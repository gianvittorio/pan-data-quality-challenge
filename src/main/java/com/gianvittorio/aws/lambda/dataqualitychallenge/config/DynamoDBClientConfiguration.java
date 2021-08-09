package com.gianvittorio.aws.lambda.dataqualitychallenge.config;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.util.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDBClientConfiguration {

    @Bean("reportsTable")
    public Table reportsTable(final DynamoDB dynamoDB) {
        return dynamoDB.getTable(Constants.REPORTS_TABLE_NAME);
    }

    @Bean
    public DynamoDB dynamoDB(final AmazonDynamoDB client) {
        return new DynamoDB(client);
    }

    @Bean
    public AmazonDynamoDB client() {
        return AmazonDynamoDBClientBuilder.standard()
                .build();
    }
}
