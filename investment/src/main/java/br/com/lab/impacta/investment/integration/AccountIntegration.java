package br.com.lab.impacta.investment.integration;

import br.com.lab.impacta.investment.integration.valueObject.AccountBalanceVo;
import br.com.lab.impacta.investment.integration.valueObject.DebitAccountVo;

public interface AccountIntegration {

    AccountBalanceVo getAccountBalanceById(Long accountId);

    boolean debit(Long accountId, Double amountOfDebit);
}
