package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.Record;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.RecordProcessingResult;

public abstract class RecordProcessorComposite implements RecordProcessor {

    protected RecordProcessorComposite next = null;

    protected final String field;

    protected int rank = 0;

    public RecordProcessorComposite(final String field) {
        this.field = field;
    }

    @Override
    public RecordProcessingResult process(final Record.RecordIterator recordIterator) {

        RecordProcessingResult result = null;
        if (recordIterator == null) {
            return result;
        }

        if (recordIterator.hasNext() && this.next != null) {
            result = this.next.process(recordIterator);
        }

        return result;
    }

    public RecordProcessorComposite addNext(final RecordProcessorComposite next) {

        this.next = (this.next == null)
                ? (next)
                : (this.next.addNext(next));

        ++this.rank;

        return this;
    }
}
