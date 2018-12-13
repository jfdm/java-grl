package uk.ac.gla.socs.grl.model.element;

import java.util.Optional;

public interface Element<I, S> {

    public Optional<DecompositionType> getDecomposition();

    public Optional<S> getSatisfaction();

    public void setSatisfaction(S s);

    public I getImportance();

    public String getTitle();

}
