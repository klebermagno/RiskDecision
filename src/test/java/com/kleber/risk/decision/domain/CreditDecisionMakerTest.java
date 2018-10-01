package com.kleber.risk.decision.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CreditDecisionMakerTest {

    private CreditDecisionMaker creditDecisionMaker;

    @Before
    public void before() {
        creditDecisionMaker = new CreditDecisionMakerImpl();
    }

    @After
    public void after() {
        creditDecisionMaker = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionOnNegativePurchaseAmount() {
        creditDecisionMaker.makeCreditDecision(-42, 0, 10);
    }

    @Test
    public void shouldAcceptCreditRequestsUpToPurchaseAmountLimit() {
        CreditDecision creditDecision = creditDecisionMaker.makeCreditDecision(10, 0,10);
        assertThat(creditDecision.isAccepted(), is(true));
        assertThat(creditDecision.getReason(), is("ok"));
    }


    @Test
    public void shouldAcceptCreditRequestsUpToCustomerDebtLimit() {
        CreditDecision creditDecision = creditDecisionMaker.makeCreditDecision(10, 90,100);
        assertThat(creditDecision.isAccepted(), is(true));
        assertThat(creditDecision.getReason(), is("ok"));
    }

    @Test
    public void shouldNotAcceptPurchasesThatWouldResultWithExceedingCustomerDebtLimit() {
        CreditDecision creditDecision = creditDecisionMaker.makeCreditDecision(1, 100,100);
        assertThat(creditDecision.isAccepted(), is(false));
        assertThat(creditDecision.getReason(), is("debt")); // no make sense becouse exced the limit
    }

}
