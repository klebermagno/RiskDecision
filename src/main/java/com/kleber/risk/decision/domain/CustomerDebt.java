package com.kleber.risk.decision.domain;

/**
 * A class representing customer debt which is the sum of all of the purchases done by the customer.
 */
public class CustomerDebt {

    /**
     * The primary identifier of the customer.
     */
    private String customerEmail;

    /**
     * The sum of the customer's all approved puchases.
     */
    private int debtAmount;

    /**
     * The limit of debit limit.
     */
    private int debtLimit;

    public CustomerDebt() {
        // default
    }

    public CustomerDebt(String customerEmail, int debtAmount, int debtLimit) {
        this.customerEmail = customerEmail;
        this.debtAmount = debtAmount;
        this.debtLimit = debtLimit;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public int getDebtAmount() {
        return debtAmount;
    }

    public void increaseDebtAmount(int amountIncrement) {
        debtAmount += amountIncrement;
    }

    public int getDebtLimit() {
        return debtLimit;
    }

    public void setDebtLimit(int debtLimit) {
        this.debtLimit = debtLimit;
    }
}
