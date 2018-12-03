package uk.ac.gla.socs.grl.value.quantitative;

import io.atlassian.fugue.Pair;
import uk.ac.gla.socs.grl.value.quantitative.common.AbstractValue;

/**
 * Encapsulates how satisfied a element is.
 */
public class Satisfaction extends AbstractValue {

    public Satisfaction(Integer v) {
        super(new Pair<>(-100, 100), v);
    }
}
