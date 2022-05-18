package br.com.lab.impacta.investment.application.impl;

import br.com.lab.impacta.investment.application.InvestmentApplication;
import br.com.lab.impacta.investment.application.adapter.InvestmentAdapter;
import br.com.lab.impacta.investment.application.dto.request.InvestmentRequest;
import br.com.lab.impacta.investment.application.dto.response.InvestmentResponse;
import br.com.lab.impacta.investment.domain.model.Investment;
import br.com.lab.impacta.investment.domain.service.InvestmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InvestmentApplicationImpl implements InvestmentApplication {

    private final InvestmentService investmentService;

    @Override
    public InvestmentResponse invest(Long accountId, InvestmentRequest investmentRequest) {

        Investment investment = investmentService.invest(investmentRequest.getProductId(),
                accountId,
                investmentRequest.getAmount());

        return InvestmentAdapter.toDtoInvestment(investment);
    }
}
