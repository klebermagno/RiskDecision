package com.kleber.risk.decision.domain;

/**
 * An interface of the credit decision algorithm.
 */
public interface CreditDecisionMaker {

    /**
     * Obtain a credit decision for given credit request amount and current customer debt amount.
     *
     * @param purchaseAmount the requested credit amount
     * @param currentCustomerDebt   the customer's current dept
     * @param customerDebtLimit the customer'' debit limit
     * @return the credit decision
     */
     CreditDecision makeCreditDecision(int purchaseAmount, int currentCustomerDebt, int customerDebtLimit);

}