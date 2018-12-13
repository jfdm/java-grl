package uk.ac.gla.socs.grl.model.element;

import java.util.Optional;

public abstract class AbstractElement<I, S> implements Element<I, S> {

    private Optional<DecompositionType> decomposition;
    private Optional<S> satisfaction;
    private I importance;
    private String title;

    public AbstractElement(Optional<DecompositionType> decomposition,
                           Optional<S> satisfaction,
                           I importance,
                           String title)
    {
        this.decomposition = decomposition;
        this.satisfaction = satisfaction;
        this.importance = importance;
        this.title = title;
    }

    public Optional<DecompositionType> getDecomposition() {
        return decomposition;
    }

    public void setDecomposition(DecompositionType decomposition) {
        this.decomposition = Optional.of(decomposition);
    }

    public Optional<S> getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(S satisfaction) {
        this.satisfaction = Optional.of(satisfaction);
    }

    public I getImportance() {
        return importance;
    }

    @Override
    public String getTitle() {
        return title;
    }

}
