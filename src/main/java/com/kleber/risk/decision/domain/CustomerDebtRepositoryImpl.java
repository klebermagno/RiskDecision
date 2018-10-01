package com.kleber.risk.decision.domain;

import com.google.common.collect.Maps;

import javax.annotation.Resource;
import java.util.Map;

/**
 * The implementation of the {@link CustomerDebtRepository} interface.
 */
@Resource
public class CustomerDebtRepositoryImpl implements CustomerDebtRepository {

    private Map<String, CustomerDebt> customerDebtStorage = Maps.newConcurrentMap();

    @Override
    public CustomerDebt fetchCustomerDebtForEmail(String email) {
        CustomerDebt customerDebit;
        if( customerDebtStorage.containsKey(email)){
            customerDebit =  customerDebtStorage.get(email);
        }else{
            customerDebit= new CustomerDebt(email, 0,0);
        }

        return  customerDebit;
    }

    @Override
    public void persistCustomerDebt(CustomerDebt customerDebt) {
        customerDebtStorage.put(customerDebt.getCustomerEmail(), customerDebt);
    }

}
