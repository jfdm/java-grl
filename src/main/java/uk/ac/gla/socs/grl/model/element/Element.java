package uk.ac.gla.socs.grl.model.element;

public interface Element<I, S> {

    DecompositionType getDecomposition();

    S getSatisfaction();

    I getImportance();

    String getTitle();

}
