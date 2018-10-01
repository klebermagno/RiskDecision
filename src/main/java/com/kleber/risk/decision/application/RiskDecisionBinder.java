package com.kleber.risk.decision.application;

import com.kleber.risk.decision.domain.CreditDecisionMaker;
import com.kleber.risk.decision.domain.CreditDecisionMakerImpl;
import com.kleber.risk.decision.domain.CustomerDebtRepository;
import com.kleber.risk.decision.domain.CustomerDebtRepositoryImpl;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 * A class containing interface-to-implementation bindings for dependency injection
 */
public class RiskDecisionBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(CustomerDebtRepositoryImpl.class).to(CustomerDebtRepository.class);
        bind(CreditDecisionMakerImpl.class).to(CreditDecisionMaker.class);
    }

}
