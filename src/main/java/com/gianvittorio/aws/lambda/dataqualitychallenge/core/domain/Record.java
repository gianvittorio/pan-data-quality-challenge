package com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Iterator;

@Getter
@EqualsAndHashCode
public class Record {

    final String[] fields;

    static final String FS = ",";

    public Record(final String line) {

        if (line == null) {
            throw new IllegalArgumentException();
        }

        this.fields = line.split(FS);
    }

    public RecordIterator iterator() {
        return new RecordIterator();
    }

    public int getNumberOfFields() {
        return this.fields.length;
    }

    public class RecordIterator implements Iterator<String> {

        private int position = 0;

        @Override
        public boolean hasNext() {
            return (this.position < Record.this.getNumberOfFields());
        }

        @Override
        public String next() {

            if (!hasNext()) {
                return null;
            }

            return Record.this.fields[position++];
        }
    }
}
