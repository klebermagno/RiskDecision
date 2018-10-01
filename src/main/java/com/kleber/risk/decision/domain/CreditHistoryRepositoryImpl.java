package com.kleber.risk.decision.domain;

import com.google.common.collect.Maps;
import com.kleber.risk.decision.api.CreditTransactionV1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The implementation of the {@link CreditHistoryRepository} interface.
 */
public class CreditHistoryRepositoryImpl implements CreditHistoryRepository {

    private Map<String, Collection<CreditTransactionV1>> customerHistory = Maps.newConcurrentMap();

    @Override
    public Collection lookupTransactions(String email) {
        if(customerHistory.containsKey(email)){

            return customerHistory.get(email);
        }else{
            ArrayList<CreditTransactionV1> creditTransactionV1s = new ArrayList();
            customerHistory.put(email, creditTransactionV1s);
            return creditTransactionV1s;
        }



    }

    @Override
    public Collection lookupTransactions(String email, String reason) {
        Collection<CreditTransactionV1> transactions = this.lookupTransactions(email);
        List<CreditTransactionV1> filteredTransactions =
                transactions.stream()
                        .filter(p -> p.getCreditRequestDecisionV1().equals( CreditDecision.DEBT.getReason()))
                        .collect(Collectors.toList());
        return  filteredTransactions;
    }

    @Override
    public void storeTransaction(String email, CreditTransactionV1 creditTransactionV1) {
        Collection transections = lookupTransactions(email);
        transections.add(creditTransactionV1);
    }
}
