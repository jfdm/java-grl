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
import uk.ac.gla.socs.grl.utility.*;
import uk.ac.gla.socs.grl.evaluation.*;
/**
 * Some sample tests that take examples from the URN standard and reproduce them here.
 *
 * @author Jan de Muijnck-Hughes
 */
public class QualitativeEvaluationTest {
    @Test
    public void figure119a() {

        GRLSpec<Importance, Satisfaction, Contribution> myspec = new GRLSpec<>("Figure 15", Importance.NONE);

        Goal<Importance, Satisfaction> connection = myspec.mkDefaultGoal("Connection");

        Task<Importance, Satisfaction> internet = myspec.mkDefaultTask("Internet");
        Task<Importance, Satisfaction> wireless = myspec.mkDefaultTask("Wireless");
        Task<Importance, Satisfaction> lan = myspec.mkDefaultTask("LAN");

        connection.setDecomposition(DecompositionType.IOR);

        internet.setSatisfaction(Satisfaction.WEAKDENIED);
        wireless.setSatisfaction(Satisfaction.WEAKSATISFACTION);
        lan.setSatisfaction(Satisfaction.NONE);

        myspec.addElement(connection);

        myspec.addElement(internet);
        myspec.addElement(wireless);
        myspec.addElement(lan);

        List<ComplexElement<Importance,Satisfaction>> temp = new LinkedList<>();
        temp.add(internet);
        temp.add(wireless);
        temp.add(lan);

        myspec.addDecomposition(connection,temp);

        List<GRLElementEvalResult<Importance,Satisfaction>> results = myspec.evaluate(new QualitativeEvaluation());
        assertTrue(results.size() > 0);
        for (GRLElementEvalResult<Importance,Satisfaction> result : results) {
            this.testSatisfaction(result, "Connection", Satisfaction.WEAKSATISFACTION);
        }
    }

    @Test
    public void figure119b() {

        GRLSpec<Importance, Satisfaction, Contribution> myspec = new GRLSpec<>("Figure 15", Importance.NONE);

        Goal<Importance, Satisfaction> connection = myspec.mkDefaultGoal("Connection");

        Task<Importance, Satisfaction> internet = myspec.mkDefaultTask("Internet");
        Task<Importance, Satisfaction> wireless = myspec.mkDefaultTask("Wireless");
        Task<Importance, Satisfaction> lan = myspec.mkDefaultTask("LAN");

        connection.setDecomposition(DecompositionType.IOR);

        internet.setSatisfaction(Satisfaction.WEAKDENIED);
        wireless.setSatisfaction(Satisfaction.WEAKDENIED);
        lan.setSatisfaction(Satisfaction.WEAKDENIED);

        myspec.addElement(connection);

        myspec.addElement(internet);
        myspec.addElement(wireless);
        myspec.addElement(lan);

        List<ComplexElement<Importance,Satisfaction>> temp = new LinkedList<>();
        temp.add(internet);
        temp.add(wireless);
        temp.add(lan);

        myspec.addDecomposition(connection,temp);

        List<GRLElementEvalResult<Importance,Satisfaction>> results = myspec.evaluate(new QualitativeEvaluation());
        assertTrue(results.size() > 0);
        for (GRLElementEvalResult<Importance,Satisfaction> result : results) {
            this.testSatisfaction(result, "Connection", Satisfaction.WEAKDENIED);
        }


    }

        @Test
    public void figure119c() {

        GRLSpec<Importance, Satisfaction, Contribution> myspec = new GRLSpec<>("Figure 15", Importance.NONE);

        Goal<Importance, Satisfaction> connection = myspec.mkDefaultGoal("Connection");

        Task<Importance, Satisfaction> internet = myspec.mkDefaultTask("Internet");
        Task<Importance, Satisfaction> wireless = myspec.mkDefaultTask("Wireless");
        Task<Importance, Satisfaction> lan = myspec.mkDefaultTask("LAN");

        connection.setDecomposition(DecompositionType.IOR);

        internet.setSatisfaction(Satisfaction.WEAKDENIED);
        wireless.setSatisfaction(Satisfaction.WEAKSATISFACTION);
        lan.setSatisfaction(Satisfaction.CONFLICT);

        myspec.addElement(connection);

        myspec.addElement(internet);
        myspec.addElement(wireless);
        myspec.addElement(lan);

        List<ComplexElement<Importance,Satisfaction>> temp = new LinkedList<>();
        temp.add(internet);
        temp.add(wireless);
        temp.add(lan);

        myspec.addDecomposition(connection,temp);

        List<GRLElementEvalResult<Importance,Satisfaction>> results = myspec.evaluate(new QualitativeEvaluation());
        assertTrue(results.size() > 0);
        for (GRLElementEvalResult<Importance,Satisfaction> result : results) {
            this.testSatisfaction(result, "Connection", Satisfaction.UNKNOWN);
        }

    }

        @Test
    public void figure119d() {

        GRLSpec<Importance, Satisfaction, Contribution> myspec = new GRLSpec<>("Figure 15", Importance.NONE);

        Goal<Importance, Satisfaction> connection = myspec.mkDefaultGoal("Connection");

        Task<Importance, Satisfaction> internet = myspec.mkDefaultTask("Internet");
        Task<Importance, Satisfaction> wireless = myspec.mkDefaultTask("Wireless");
        Task<Importance, Satisfaction> lan = myspec.mkDefaultTask("LAN");

        connection.setDecomposition(DecompositionType.IOR);

        internet.setSatisfaction(Satisfaction.WEAKDENIED);
        wireless.setSatisfaction(Satisfaction.SATISFACTION);
        lan.setSatisfaction(Satisfaction.CONFLICT);

        myspec.addElement(connection);

        myspec.addElement(internet);
        myspec.addElement(wireless);
        myspec.addElement(lan);

        List<ComplexElement<Importance,Satisfaction>> temp = new LinkedList<>();
        temp.add(internet);
        temp.add(wireless);
        temp.add(lan);

        myspec.addDecomposition(connection,temp);
        List<GRLElementEvalResult<Importance,Satisfaction>> results = myspec.evaluate(new QualitativeEvaluation());
        assertTrue(results.size() > 0);
        for (GRLElementEvalResult<Importance,Satisfaction> result : results) {
            this.testSatisfaction(result, "Connection", Satisfaction.SATISFACTION);
        }

    }


    @Test
    public void figure118a() {

        GRLSpec<Importance, Satisfaction, Contribution> myspec = new GRLSpec<>("Figure 15", Importance.NONE);

        Goal<Importance, Satisfaction> connection = myspec.mkDefaultGoal("Connection");

        Task<Importance, Satisfaction> internet = myspec.mkDefaultTask("Internet");
        Task<Importance, Satisfaction> wireless = myspec.mkDefaultTask("Wireless");
        Task<Importance, Satisfaction> lan = myspec.mkDefaultTask("LAN");

        connection.setDecomposition(DecompositionType.AND);

        internet.setSatisfaction(Satisfaction.WEAKDENIED);
        wireless.setSatisfaction(Satisfaction.WEAKSATISFACTION);
        lan.setSatisfaction(Satisfaction.NONE);

        myspec.addElement(connection);

        myspec.addElement(internet);
        myspec.addElement(wireless);
        myspec.addElement(lan);

        List<ComplexElement<Importance,Satisfaction>> temp = new LinkedList<>();
        temp.add(internet);
        temp.add(wireless);
        temp.add(lan);

        myspec.addDecomposition(connection,temp);

        List<GRLElementEvalResult<Importance,Satisfaction>> results = myspec.evaluate(new QualitativeEvaluation());
        assertTrue(results.size() > 0);
        for (GRLElementEvalResult<Importance,Satisfaction> result : results) {
            this.testSatisfaction(result, "Connection", Satisfaction.WEAKDENIED);
        }


    }

    @Test
    public void figure118b() {

        GRLSpec<Importance, Satisfaction, Contribution> myspec = new GRLSpec<>("Figure 15", Importance.NONE);

        Goal<Importance, Satisfaction> connection = myspec.mkDefaultGoal("Connection");

        Task<Importance, Satisfaction> internet = myspec.mkDefaultTask("Internet");
        Task<Importance, Satisfaction> wireless = myspec.mkDefaultTask("Wireless");
        Task<Importance, Satisfaction> lan = myspec.mkDefaultTask("LAN");

        connection.setDecomposition(DecompositionType.AND);

        internet.setSatisfaction(Satisfaction.SATISFACTION);
        wireless.setSatisfaction(Satisfaction.SATISFACTION);
        lan.setSatisfaction(Satisfaction.SATISFACTION);

        myspec.addElement(connection);

        myspec.addElement(internet);
        myspec.addElement(wireless);
        myspec.addElement(lan);

        List<ComplexElement<Importance,Satisfaction>> temp = new LinkedList<>();
        temp.add(internet);
        temp.add(wireless);
        temp.add(lan);

        myspec.addDecomposition(connection,temp);

        List<GRLElementEvalResult<Importance,Satisfaction>> results = myspec.evaluate(new QualitativeEvaluation());
        assertTrue(results.size() > 0);
        for (GRLElementEvalResult<Importance,Satisfaction> result : results) {
            this.testSatisfaction(result, "Connection", Satisfaction.SATISFACTION);
        }


    }

        @Test
    public void figure118c() {

        GRLSpec<Importance, Satisfaction, Contribution> myspec = new GRLSpec<>("Figure 15", Importance.NONE);

        Goal<Importance, Satisfaction> connection = myspec.mkDefaultGoal("Connection");

        Task<Importance, Satisfaction> internet = myspec.mkDefaultTask("Internet");
        Task<Importance, Satisfaction> wireless = myspec.mkDefaultTask("Wireless");
        Task<Importance, Satisfaction> lan = myspec.mkDefaultTask("LAN");

        connection.setDecomposition(DecompositionType.AND);

        internet.setSatisfaction(Satisfaction.WEAKDENIED);
        wireless.setSatisfaction(Satisfaction.WEAKSATISFACTION);
        lan.setSatisfaction(Satisfaction.CONFLICT);

        myspec.addElement(connection);

        myspec.addElement(internet);
        myspec.addElement(wireless);
        myspec.addElement(lan);

        List<ComplexElement<Importance,Satisfaction>> temp = new LinkedList<>();
        temp.add(internet);
        temp.add(wireless);
        temp.add(lan);

        myspec.addDecomposition(connection,temp);

        List<GRLElementEvalResult<Importance,Satisfaction>> results = myspec.evaluate(new QualitativeEvaluation());
        assertTrue(results.size() > 0);
        for (GRLElementEvalResult<Importance,Satisfaction> result : results) {
            this.testSatisfaction(result, "Connection", Satisfaction.UNKNOWN);
        }

    }

            @Test
    public void figure118d() {

        GRLSpec<Importance, Satisfaction, Contribution> myspec = new GRLSpec<>("Figure 15", Importance.NONE);

        Goal<Importance, Satisfaction> connection = myspec.mkDefaultGoal("Connection");

        Task<Importance, Satisfaction> internet = myspec.mkDefaultTask("Internet");
        Task<Importance, Satisfaction> wireless = myspec.mkDefaultTask("Wireless");
        Task<Importance, Satisfaction> lan = myspec.mkDefaultTask("LAN");

        connection.setDecomposition(DecompositionType.AND);

        internet.setSatisfaction(Satisfaction.DENIED);
        wireless.setSatisfaction(Satisfaction.WEAKSATISFACTION);
        lan.setSatisfaction(Satisfaction.CONFLICT);

        myspec.addElement(connection);

        myspec.addElement(internet);
        myspec.addElement(wireless);
        myspec.addElement(lan);

        List<ComplexElement<Importance,Satisfaction>> temp = new LinkedList<>();
        temp.add(internet);
        temp.add(wireless);
        temp.add(lan);

        myspec.addDecomposition(connection,temp);

        List<GRLElementEvalResult<Importance,Satisfaction>> results = myspec.evaluate(new QualitativeEvaluation());
        assertTrue(results.size() > 0);
        for (GRLElementEvalResult<Importance,Satisfaction> result : results) {
            this.testSatisfaction(result, "Connection", Satisfaction.DENIED);
        }


    }


            @Test
    public void figure1112a() {

        GRLSpec<Importance, Satisfaction, Contribution> myspec = new GRLSpec<>("Figure 15", Importance.NONE);

        SoftGoal<Importance, Satisfaction> connection = myspec.mkDefaultSoftGoal("Increase Mobility");

        Task<Importance, Satisfaction> internet = myspec.mkDefaultTask("Internet");
        Task<Importance, Satisfaction> wireless = myspec.mkDefaultTask("Wireless");
        Task<Importance, Satisfaction> lan = myspec.mkDefaultTask("LAN");

        internet.setSatisfaction(Satisfaction.DENIED);
        wireless.setSatisfaction(Satisfaction.WEAKSATISFACTION);
        lan.setSatisfaction(Satisfaction.NONE);

        myspec.addElement(connection);

        myspec.addElement(internet);
        myspec.addElement(wireless);
        myspec.addElement(lan);

        myspec.addContribution(internet,Contribution.SOMEPOS,connection);
        myspec.addContribution(wireless,Contribution.MAKES,connection);
        myspec.addContribution(lan,Contribution.SOMENEG,connection);

        List<GRLElementEvalResult<Importance,Satisfaction>> results = myspec.evaluate(new QualitativeEvaluation());
        assertTrue(results.size() > 0);
        for (GRLElementEvalResult<Importance,Satisfaction> result : results) {
            this.testSatisfaction(result, "Connection", Satisfaction.NONE);
        }


    }

    @Test
    public void figure1112b() {

        GRLSpec<Importance, Satisfaction, Contribution> myspec = new GRLSpec<>("Figure 15", Importance.NONE);

        SoftGoal<Importance, Satisfaction> connection = myspec.mkDefaultSoftGoal("Increase Mobility");

        Task<Importance, Satisfaction> internet = myspec.mkDefaultTask("Internet");
        Task<Importance, Satisfaction> wireless = myspec.mkDefaultTask("Wireless");
        Task<Importance, Satisfaction> lan = myspec.mkDefaultTask("LAN");

        internet.setSatisfaction(Satisfaction.WEAKSATISFACTION);
        wireless.setSatisfaction(Satisfaction.WEAKSATISFACTION);
        lan.setSatisfaction(Satisfaction.NONE);

        myspec.addElement(connection);

        myspec.addElement(internet);
        myspec.addElement(wireless);
        myspec.addElement(lan);

        myspec.addContribution(internet,Contribution.SOMEPOS,connection);
        myspec.addContribution(wireless,Contribution.MAKES,connection);
        myspec.addContribution(lan,Contribution.SOMENEG,connection);

        List<GRLElementEvalResult<Importance,Satisfaction>> results = myspec.evaluate(new QualitativeEvaluation());
        assertTrue(results.size() > 0);

        for (GRLElementEvalResult<Importance,Satisfaction> result : results) {
            this.testSatisfaction(result, "Connection", Satisfaction.WEAKSATISFACTION);
        }


    }

@Test
    public void figure1113a() {

        GRLSpec<Importance, Satisfaction, Contribution> myspec = new GRLSpec<>("Figure 15", Importance.NONE);


        Actor<Importance,Satisfaction> telco = myspec.mkDefaultActor("Telcom");

        Task<Importance, Satisfaction> account = myspec.mkDefaultTask("Create Account");
        account.setSatisfaction(Satisfaction.WEAKDENIED);

        Task<Importance, Satisfaction> fees = myspec.mkDefaultTask("Charge Fees");
        fees.setSatisfaction(Satisfaction.WEAKSATISFACTION);

        telco.addElement(account);
        telco.addElement(fees);

        Resource<Importance, Satisfaction> connection = myspec.mkDefaultResource("Internet Connection");
        //        connection.setSatisfaction(Satisfaction.NONE);
        SoftGoal<Importance, Satisfaction> lowcosts = myspec.mkDefaultSoftGoal("Low Costs");
        //        lowcosts.setSatisfaction(Satisfaction.NONE);

        Actor<Importance,Satisfaction> store = myspec.mkDefaultActor("Store");

        SoftGoal<Importance, Satisfaction> incvisibility = myspec.mkDefaultSoftGoal("Increase Visibility");
        //        incvisibility.setSatisfaction(Satisfaction.NONE);
        store.addElement(incvisibility);

        myspec.addElement(telco);
        myspec.addElement(store);
        myspec.addElement(connection);
        myspec.addElement(lowcosts);

        myspec.addDependency(incvisibility,connection);
        myspec.addDependency(incvisibility,lowcosts);

        myspec.addDependency(connection,account);
        myspec.addDependency(lowcosts,fees);

        assertTrue(myspec.getElements().size() == 7);
        List<GRLElementEvalResult<Importance,Satisfaction>> results = myspec.evaluate(new QualitativeEvaluation());

        assertTrue(results.size() > 0);

        for (GRLElementEvalResult<Importance,Satisfaction> result : results) {
            this.testSatisfaction(result, "Internet Connection", Satisfaction.WEAKDENIED);
            this.testSatisfaction(result, "Low Costs", Satisfaction.WEAKSATISFACTION);
            this.testSatisfaction(result, "Increase Visibility", Satisfaction.WEAKDENIED);
        }


    }


    private void testSatisfaction(GRLElementEvalResult<Importance,Satisfaction> result,
                                  String title,
                                  Satisfaction expected)
    {
        if (result.getElement().getTitle() == title) {
            assertTrue(result.getOriginal() instanceof Nothing);
            assertTrue(result.getResult() instanceof Just);

            if (result.getResult() instanceof Just) {
                Satisfaction s = ((Just<Satisfaction>) result.getResult()).getValue();
                assertEquals(expected,s);
            }
        }
    }
}
