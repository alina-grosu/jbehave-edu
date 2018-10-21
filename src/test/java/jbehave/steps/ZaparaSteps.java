package jbehave.steps;

import jbehave.dto.json.Bar;
import jbehave.dto.json.Feq;
import jbehave.dto.json.Foo;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

public class ZaparaSteps {

    public ZaparaSteps() {
        System.out.println("instantiating steps");
    }

    @Given("tests cann run")
    public void givenTestsCannRun(){
        System.out.println("We are running ffs.");
    }

    @When("foo equals <foo>")
    public void whenFooEqualsFoo(@Named("foo") String foo){
        System.out.println(String.format("foo=%s", foo));
    }

    @Then("bar equals <bar>")
    public void thenBarEqualsBar(@Named("bar") String bar){
        System.out.println(String.format("foo=%s", bar));
    }

    @Then("baz equal <baz>")
    public void thenBazEqualBaz(@Named("baz") Foo baz){
        //System.out.println(String.format("baz=%s, beb=%s, foq=%s", baz.getBaz(), baz.getBeb(), baz.getFoq()));
    }

    @Then("fiq equal to <fiq>")
    public void thenFiqEqualToFiq(@Named("fiq") Feq fiq){
        //System.out.println(fiq);
        //System.out.println(fiq.equals(fiq));
        Feq newFeq = new Feq(new Bar("OMG3", 103, new BigDecimal("100.3")), new Foo("baz3", "beb3", "feq3"), Arrays.asList("foo", "bar", "baz"));
        //System.out.println("NEW FEQ: " + newFeq);
        //System.out.println("FEQ equals:" + fiq.equals(newFeq));
        assertThat(fiq, equalTo(newFeq));
    }

    @Then("bob equal <beb>")
    public void thenBobEqualBaz(@Named("beb") Bar beb){
        //System.out.println(beb);
        //System.out.println(beb.equals(beb));
        Bar omg1 = new Bar("OMG1", 100, new BigDecimal("100.2"));
        //System.out.println(omg1);
        //System.out.println(beb.equals(omg1));
        //System.out.println("" + beb.getBigdex().equals(omg1.getBigdex()) + (beb.getNumber() == omg1.getNumber()) + beb.getOmg().equals(omg1.getOmg()));
        //System.out.println(beb.getBigdex() + " " + omg1.getBigdex());
    }

    @Given("date is <date>")
    public void givenDateIsDate(@Named("date") ZonedDateTime date){
        System.out.println("**************************************************************");
        System.out.println(date);
        System.out.println("**************************************************************");
    }


    public static void main (String[] args)
    {
        System.out.println(String.format("foo=%s", "foq"));
    }

}