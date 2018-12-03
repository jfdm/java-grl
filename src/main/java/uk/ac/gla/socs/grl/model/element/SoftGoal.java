package uk.ac.gla.socs.grl.model.element;

/**
 * A Softgoal is a condition or state of affairs in the world that the
element would like to achieve, but unlike in the concept of (hard) goal,
there are no clear-cut criteria for whether the condition is achieved,
and it is up to subjective judgement and interpretation of the
modeller to judge whether a particular state of affairs in fact
achieves sufficiently the stated softgoal. Softgoals are often used to
describe qualities
and non-functional aspects such as security, robustness, performance, usability, etc.
 *
 */
public class SoftGoal<I, S> extends AbstractElement<I, S> implements IntentionalElement<I,S> {

    public SoftGoal(S satisfaction, I importance, String title) {
        super(DecompositionType.AND, satisfaction, importance, title);
    }
}
