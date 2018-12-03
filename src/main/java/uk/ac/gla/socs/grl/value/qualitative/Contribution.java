package uk.ac.gla.socs.grl.value.qualitative;

/**
 * The qualitative contribution of a source intentional element or
 * indicator to a destination intentional element can be one of the
 * following values based on the degree (positive or negative) and
 * sufficiency of the contribution to the satisfaction of the
 * destination intentional element.
 */
public enum Contribution {
    /**
     * The contribution is positive and sufficient.
     */
    MAKES,
    /**
     * The contribution is positive but not sufficient.
     */
    HELPS,
    /**
     * The contribution is positive, but the extent of the contribution is
     * unknown.
     */
    SOMEPOS,
    /**
     * There is some contribution, but the extent and the degree (positive or
     * negative) of the contribution is unknown.
     */
    UNKNOWN,
    /**
     * The contribution is negative, but the extent of the contribution is
     * unknown.
     */
    SOMENEG,
    /**
     * The contribution of the contributing element is negative and sufficient.
     */
    BREAKS,
    /**
     * The contribution is negative but not sufficient.
     */
    HURTS
}
