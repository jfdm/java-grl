package uk.ac.gla.socs.grl.value.quantitative.common;

import io.atlassian.fugue.Pair;

public abstract class AbstractValue {

    private QuantitativeValue value;
    private Pair<Integer, Integer> range;

    public AbstractValue(Pair<Integer, Integer> range, Integer v) {
        this.range = range;
        this.value = QuantitativeValue.create(range, v);
    }

    public Integer getValue() {
        return this.getValue();
    }

    public void setValue(Integer value) {
        this.value = QuantitativeValue.create(range, value);
    }

}
