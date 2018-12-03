package uk.ac.gla.socs.grl.value.quantitative;

import io.atlassian.fugue.Pair;
import uk.ac.gla.socs.grl.value.quantitative.common.AbstractValue;

public class Importance extends AbstractValue {
    public Importance(Integer v) {
        super(new Pair<>(0, 100), v);
    }
}
