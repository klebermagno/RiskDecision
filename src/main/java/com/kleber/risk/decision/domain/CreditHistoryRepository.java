package com.kleber.risk.decision.domain;

import com.kleber.risk.decision.api.CreditTransactionV1;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * An interface to log the credit decisions.
 */

@Resource
public interface CreditHistoryRepository {

    /**
     * Lookup all of the transactions based on the customer's email.
     *
     * @param email the customer's email
     * @return the customer's history
     */
    Collection lookupTransactions(String email);

    /**
     * Lookup all of the transactions based on the customer's email and the reason.
     *
     * @param email  the customer's email
     * @param reason the credit decision reason
     * @return the customer's history
     */
    Collection lookupTransactions(String email, String reason);


    void storeTransaction(String email, CreditTransactionV1 creditTransactionV1);

}
