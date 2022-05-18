package br.com.lab.impacta.investment.domain.service.impl;

import br.com.lab.impacta.investment.domain.exception.InvestmentAccountIsNotDebited;
import br.com.lab.impacta.investment.domain.exception.InvestmentProductNotFoundException;
import br.com.lab.impacta.investment.domain.model.Investment;
import br.com.lab.impacta.investment.domain.model.Product;
import br.com.lab.impacta.investment.domain.service.InvestmentService;
import br.com.lab.impacta.investment.infrasctructure.repository.InvestmentRepository;
import br.com.lab.impacta.investment.infrasctructure.repository.ProductRepository;
import br.com.lab.impacta.investment.integration.AccountIntegration;
import br.com.lab.impacta.investment.integration.valueObject.AccountBalanceVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InvestmentServiceImpl implements InvestmentService {

    private final ProductRepository productRepository;
    private final AccountIntegration accountIntegration;
    private final InvestmentRepository investmentRepository;

    @Override
    public Investment invest(Long productId, Long accountId, Double amountOfInvestment) {

        Product product = productRepository.findById(productId)
                .orElseThrow(InvestmentProductNotFoundException::new);

        Investment investment = new Investment(productId, accountId, amountOfInvestment);

        AccountBalanceVo accountBalanceVo = accountIntegration.getAccountBalanceById(accountId);

        investment.checkSufficientBalanceForInvestment(accountBalanceVo.getBalance());
        investment.verifyProductIsPrivateOrDefaultForInvestment(accountBalanceVo.getBalance(), product);

        boolean isDebited = accountIntegration.debit(accountId, amountOfInvestment);

        if (isDebited) {
            return investmentRepository.save(investment);
        } else {
            throw new InvestmentAccountIsNotDebited();
        }
    }
}
