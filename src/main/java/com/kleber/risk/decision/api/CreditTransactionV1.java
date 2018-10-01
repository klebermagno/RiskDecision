/**
 * 13/08/2018
 *
 * @author Kleber Vieira e-mail: Klebermagno@gmail.com
 * github klebermagno
 */
package com.kleber.risk.decision.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreditTransactionV1 {

    @JsonProperty(value = "credit_request_decision")
    private CreditRequestDecisionV1 creditRequestDecisionV1;

    @JsonProperty(value = "amount")
    private int amount;



    public CreditRequestDecisionV1 getCreditRequestDecisionV1() {
        return creditRequestDecisionV1;
    }

    public void setCreditRequestDecisionV1(CreditRequestDecisionV1 creditRequestDecisionV1) {
        this.creditRequestDecisionV1 = creditRequestDecisionV1;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
