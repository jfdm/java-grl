package uk.ac.gla.socs.grl.model.element;


public abstract class AbstractElement<I, S> implements Element<I, S> {

    private DecompositionType decomposition;
    private S satisfaction;
    private I importance;
    private String title;

    public AbstractElement(DecompositionType decomposition, S satisfaction, I importance, String title) {
        this.decomposition = decomposition;
        this.satisfaction = satisfaction;
        this.importance = importance;
        this.title = title;
    }

    public DecompositionType getDecomposition() {
        return decomposition;
    }

    public void setDecomposition(DecompositionType decomposition) {
        this.decomposition = decomposition;
    }

    public S getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(S satisfaction) {
        this.satisfaction = satisfaction;
    }

    public I getImportance() {
        return importance;
    }

    @Override
    public String getTitle() {
        return title;
    }

}
