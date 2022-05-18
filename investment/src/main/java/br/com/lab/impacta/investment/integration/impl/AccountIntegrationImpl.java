package br.com.lab.impacta.investment.integration.impl;

import br.com.lab.impacta.investment.infrasctructure.http.AccountClient;
import br.com.lab.impacta.investment.integration.AccountIntegration;
import br.com.lab.impacta.investment.integration.dto.request.DebitAccountRequest;
import br.com.lab.impacta.investment.integration.valueObject.AccountBalanceVo;
import br.com.lab.impacta.investment.integration.valueObject.DebitAccountVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountIntegrationImpl implements AccountIntegration {

    private final AccountClient accountClient;

    @Override
    public AccountBalanceVo getAccountBalanceById(Long accountId) {

        return accountClient.getBalance(accountId);
    }

    @Override
    public boolean debit(Long accountId, Double amountOfDebit) {

        DebitAccountVo debit = accountClient.debit(accountId, new DebitAccountRequest(amountOfDebit));

        return debit.isDebited();
    }
}
