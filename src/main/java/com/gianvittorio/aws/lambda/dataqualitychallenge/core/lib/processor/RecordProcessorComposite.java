package com.gianvittorio.aws.lambda.dataqualitychallenge.core.lib.processor;

import com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain.Result;
import com.gianvittorio.aws.lambda.dataqualitychallenge.core.util.RecordIterator;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class RecordProcessorComposite implements RecordProcessor {

    private RecordProcessorComposite next = null;

    @Override
    public Result process(final RecordIterator recordIterator) {

        Result result = null;
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

        return this;
    }
}
