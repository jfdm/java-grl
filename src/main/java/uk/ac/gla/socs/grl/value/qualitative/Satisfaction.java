package uk.ac.gla.socs.grl.value.qualitative;

public enum Satisfaction {
    /**
     * The intentional element or indicator is sufficiently dissatisfied.
     */
    DENIED,
    /**
     * The intentional element or indicator is partially dissatisfied.
     */
    WEAKDENIED,
    /**
     * The intentional element or indicator is partially satisfied.
     */
    WEAKSATISFACTION,
    /**
     * The satisfaction level of the intentional element or indicator is unknown.
     */
    UNKNOWN,
    /**
     * The intentional element or indicator is sufficiently satisfied.
     */
    SATISFACTION,
    /**
     * There are arguments strongly in favour and strongly against the
satisfaction of the intentional element or indicator.
    */
    CONFLICT,
    /**
     * The intentional element or indicator is neither satisfied nor
     * dissatisfied.
     */
    NONE
}
