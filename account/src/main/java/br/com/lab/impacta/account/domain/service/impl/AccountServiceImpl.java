package br.com.lab.impacta.account.domain.service.impl;

import br.com.lab.impacta.account.domain.exception.AccountNotFoundException;
import br.com.lab.impacta.account.domain.model.Account;
import br.com.lab.impacta.account.domain.service.AccountService;
import br.com.lab.impacta.account.infrastructure.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    @Value("${lab.account.exceptions.account-dont-exists-message}")
    private String messageAccountNotFoundException;

    @Value("${lab.account.exceptions.account-dont-exists-description}")
    private String descriptionAccountNotFoundException;

    @Override
    public Account find(Long accountId) {
        return repository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(
                        messageAccountNotFoundException,
                        descriptionAccountNotFoundException
                ));
    }

    @Override
    public void debit(Long accountId, Double amountOfDebit) {
        Account account = this.find(accountId);

        account.debit(amountOfDebit);

        repository.save(account);
    }
}
