package uk.ac.gla.socs.grl.value.quantitative.common;

import io.atlassian.fugue.Pair;

/**
 * Represents quantitatively derived values within a specified range $(l,u)$.
 * <p>
 * We wrap the raw integer value in an object to ensure correct construction i.e. $v \in (l,u)$.
 */
public class QuantitativeValue {

    private Integer value;

    private QuantitativeValue(Integer value) {
        this.value = value;
    }

    /**
     * Coonstruct a new quantitative value subject to the specified range
     *
     * @param range an open range (l,u)
     * @param v
     * @return
     */
    protected static QuantitativeValue create(Pair<Integer, Integer> range, Integer v) {
        if (v <= range.left())
            return new QuantitativeValue(range.left());
        else if (v >= range.right())
            return new QuantitativeValue(range.right());
        else
            return new QuantitativeValue(v);
    }

    public Integer getValue(Integer value) {
        return this.value;
    }

}
