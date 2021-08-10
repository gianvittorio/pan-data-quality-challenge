package com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Iterator;

@Getter
@EqualsAndHashCode
public class RowRecord {

    final String[] fields;

    static final String FS = ",";

    public RowRecord(final String line) {

        if (line == null) {
            throw new IllegalArgumentException();
        }

        this.fields = line.split(FS);
    }

    public FieldsIterator iterator() {
        return new FieldsIterator();
    }

    public int getNumberOfFields() {
        return this.fields.length;
    }

    public class FieldsIterator implements Iterator<String> {

        private int position = 0;

        @Override
        public boolean hasNext() {
            return (this.position < RowRecord.this.getNumberOfFields());
        }

        @Override
        public String next() {

            if (!hasNext()) {
                return null;
            }

            return RowRecord.this.fields[position++];
        }
    }
}
