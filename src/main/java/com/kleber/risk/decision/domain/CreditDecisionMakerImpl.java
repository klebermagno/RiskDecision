package com.kleber.risk.decision.domain;

/**
 * The implementation of the {@link CreditDecisionMaker} interface.
 */
public class CreditDecisionMakerImpl implements CreditDecisionMaker {


    @Override
    public CreditDecision makeCreditDecision(int purchaseAmount, int currentCustomerDebt, int customerDebtLimit) {

        CreditDecision creditDecision = null;

        if (purchaseAmount < 0) {

            throw new IllegalArgumentException();

        }

        if (currentCustomerDebt == customerDebtLimit) {

            creditDecision = CreditDecision.DEBT;

        } else if (currentCustomerDebt + purchaseAmount > customerDebtLimit) {

            creditDecision = CreditDecision.MAX_AMOUNT_BREACH;

        } else {

            creditDecision = CreditDecision.ACCEPTED;

        }

        return creditDecision;

    }

}
