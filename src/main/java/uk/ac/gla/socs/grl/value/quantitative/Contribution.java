package uk.ac.gla.socs.grl.value.quantitative;

import io.atlassian.fugue.Pair;
import uk.ac.gla.socs.grl.value.quantitative.common.AbstractValue;

public class Contribution extends AbstractValue {
    public Contribution(Integer v) {
        super(new Pair<>(-100, 100), v);
    }
}
