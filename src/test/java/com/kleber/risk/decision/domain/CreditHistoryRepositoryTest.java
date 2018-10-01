/**
 * 13/08/2018
 *
 * @author Kleber Vieira e-mail: Klebermagno@gmail.com
 * github klebermagno
 */
package com.kleber.risk.decision.domain;

import com.kleber.risk.decision.api.CreditRequestDecisionV1;
import com.kleber.risk.decision.api.CreditTransactionV1;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

public class CreditHistoryRepositoryTest
{

    CreditHistoryRepository creditHistoryRepository;

    @Before
    public void before(){
        this.creditHistoryRepository = new CreditHistoryRepositoryImpl();
        this.creditHistoryRepository.storeTransaction("john@doi.com",makeCreditAcceptedTransaction());
        makeCreditDebtTransaction("johnNotAccepted@doi.com",10);
    }




    @After
    public void ater(){
        this.creditHistoryRepository = null;
    }

    @Test
    public void retriveEmptyCustomerTransactionTest(){

        Collection<CreditTransactionV1> creditTransactionV1s =  this.creditHistoryRepository.lookupTransactions("Uncle@doi.com");
        assertThat(  creditTransactionV1s.isEmpty() , equalTo(true));

    }

    @Test
    public void retriveAllCustomerTransactionTest(){

        Collection<CreditTransactionV1> creditTransactionV1s =  this.creditHistoryRepository.lookupTransactions("john@doi.com");

        assertThat(  creditTransactionV1s.size() , equalTo(1));
       Iterator<CreditTransactionV1> i= creditTransactionV1s.iterator();
        while(i.hasNext()) {
            CreditTransactionV1 creditTransactionV1 = i.next();
            assertThat(creditTransactionV1.getAmount(),equalTo(10));
            assertThat(creditTransactionV1.getCreditRequestDecisionV1().isAccepted(),equalTo(true));
            assertThat(creditTransactionV1.getCreditRequestDecisionV1().getReason(),equalTo(CreditDecision.ACCEPTED.getReason()));
        }

    }

    @Test
    public void retriveReasonBasedCustomerTransactionTest() {
        Collection<CreditTransactionV1> creditTransactionV1s =  this.creditHistoryRepository.lookupTransactions("johnNotAccepted@doi.com",CreditDecision.DEBT.getReason());

        assertThat(  creditTransactionV1s.size() , equalTo(10));
        Iterator<CreditTransactionV1> i= creditTransactionV1s.iterator();
        while(i.hasNext()) {
            CreditTransactionV1 creditTransactionV1 = i.next();
            assertThat(creditTransactionV1.getAmount(),equalTo(10));
            assertThat(creditTransactionV1.getCreditRequestDecisionV1().isAccepted(),equalTo(false));
            assertThat(creditTransactionV1.getCreditRequestDecisionV1().getReason(),equalTo(CreditDecision.DEBT.getReason()));
        }
    }

    private CreditTransactionV1 makeCreditAcceptedTransaction() {
       CreditTransactionV1 creditTransactionV1 = new CreditTransactionV1();
       creditTransactionV1.setAmount(10);
        CreditRequestDecisionV1 creditRequestDecisionV1 = new CreditRequestDecisionV1(true,CreditDecision.ACCEPTED.getReason());
       creditTransactionV1.setCreditRequestDecisionV1(creditRequestDecisionV1);
       return creditTransactionV1;
    }

    private void makeCreditDebtTransaction(String email,int times) {
        CreditTransactionV1 creditTransactionV1;

        for(int i = times;i<10;i++){
            creditTransactionV1  = new CreditTransactionV1();
            creditTransactionV1.setAmount(10);
            CreditRequestDecisionV1 creditRequestDecisionV1 = new CreditRequestDecisionV1(true,CreditDecision.ACCEPTED.getReason());
            creditTransactionV1.setCreditRequestDecisionV1(creditRequestDecisionV1);
            this.creditHistoryRepository.storeTransaction(email,creditTransactionV1);
        }



    }
}
