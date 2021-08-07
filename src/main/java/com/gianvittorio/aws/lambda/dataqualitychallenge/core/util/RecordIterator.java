package com.gianvittorio.aws.lambda.dataqualitychallenge.core.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RecordIterator implements Iterator<String> {

    private static final char FS = ',';

    private final String line;
    private final char[] chars;

    private int leftPointer = 0;
    private int rightPointer = 0;

    public RecordIterator(final String line) {
        this.line = line;
        this.chars = line.toCharArray();
    }


    @Override
    public boolean hasNext() {

        return (leftPointer < chars.length);
    }

    @Override
    public String next() {
        //final String line = "1,YWZ,643822,643,822,,2021-03-31";
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        rightPointer = leftPointer;
        while (rightPointer < chars.length && chars[rightPointer] != FS) {
            ++rightPointer;
        }

        final String next = line.substring(leftPointer, rightPointer);

        leftPointer = 1 + rightPointer;

        return next;
    }
}
