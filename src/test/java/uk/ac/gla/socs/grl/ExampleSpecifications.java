package uk.ac.gla.socs.grl;

import java.lang.*;

import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import io.atlassian.fugue.Pair;

import uk.ac.gla.socs.grl.model.*;
import uk.ac.gla.socs.grl.model.link.*;
import uk.ac.gla.socs.grl.model.element.*;
import uk.ac.gla.socs.grl.value.qualitative.*;
import uk.ac.gla.socs.grl.utility.Graph;
import uk.ac.gla.socs.grl.utility.Edge;
import uk.ac.gla.socs.grl.utility.GraphFactory;

/**
 * Some sample tests that take examples from the URN standard and reproduce them here.
 *
 * @author Jan de Muijnck-Hughes
 */
public class ExampleSpecifications {
    @Test
    public void figure15() {
        GRLSpec<Importance, Satisfaction, Contribution> myspec = new GRLSpec<>("Figure 15", Importance.NONE);
        SoftGoal<Importance, Satisfaction> hr = myspec.mkDefaultSoftGoal("High Reliability");
        Task<Importance, Satisfaction> wireless = myspec.mkDefaultTask("Make voice connection over wireless");
        Task<Importance, Satisfaction> wired = myspec.mkDefaultTask("Make voice connection over internet");
        Belief<Importance, Satisfaction> belief = myspec.mkDefaultBelief("Wireless is less reliable than internet.");
        SoftGoal<Importance, Satisfaction> spec_usage = myspec.mkDefaultSoftGoal("Minimise spectrum usage");

        myspec.addElement(hr);
        myspec.addElement(wireless);
        myspec.addElement(wired);
        myspec.addElement(belief);
        myspec.addElement(spec_usage);

        assertEquals(5,myspec.getElements().size());
        for (Element<Importance,Satisfaction> n : myspec.getElements()) {
            assertFalse(n.getSatisfaction().isPresent());
            assertEquals(Importance.NONE, n.getImportance());
        }

        // 'Make Voice Connection Over Wireless' has a positive and
        // sufficient contribution on 'High Reliability'
        myspec.addContribution(wireless,Contribution.MAKES, hr);

        // "Make Voice Connection Over Internet" has some positive
        // contribution on "High Reliability".
        myspec.addContribution(wired, Contribution.SOMEPOS, hr);

        // "Wireless is less reliable than Internet" has some negative
        // contribution on "High Reliability".
        myspec.addContribution(belief, Contribution.SOMENEG, hr);

        // "Make Voice Connection Over Wireless" has some negative
        // correlation (side-effect) on "Minimize Spectrum Usage".
        myspec.addCorrelation(wireless, Contribution.SOMENEG, spec_usage);

        // "Make Voice Connection Over Internet" has some positive
        // correlation (side-effect) on "Minimize Spectrum Usage".
        myspec.addCorrelation(wired, Contribution.SOMEPOS, spec_usage);

        assertEquals(5, myspec.getLinks().size());
    }

    @Test
    public void figure17(){
        GRLSpec<Importance, Satisfaction, Contribution> myspec = new GRLSpec<>("Figure 17", Importance.NONE);

        Actor<Importance, Satisfaction> store = myspec.mkDefaultActor("Store");
        Resource<Importance, Satisfaction> conn = myspec.mkDefaultResource("Internet Connection");
        Actor<Importance, Satisfaction> telco = myspec.mkDefaultActor("Telecom Provider");

        myspec.addElement(store);
        myspec.addElement(conn);
        myspec.addElement(telco);

        myspec.addDependency(store, conn);
        myspec.addDependency(conn, telco);
    }

    @Test
    public void figure18(){
        GRLSpec<Importance, Satisfaction, Contribution> myspec = new GRLSpec<>("Figure 17", Importance.NONE);

        Actor<Importance, Satisfaction> store = myspec.mkDefaultActor("Store");
        Resource<Importance, Satisfaction> conn = myspec.mkDefaultResource("Internet Connection");
        Actor<Importance, Satisfaction> telco = myspec.mkDefaultActor("Telecom Provider");
        Task<Importance, Satisfaction> acc = myspec.mkDefaultTask("Create Account");
        telco.addElement(acc);

        myspec.addElement(store);
        myspec.addElement(conn);
        myspec.addElement(telco);

        myspec.addDependency(store, conn);
        myspec.addDependency(conn, acc);
    }
}
