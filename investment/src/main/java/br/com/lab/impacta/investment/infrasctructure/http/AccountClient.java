package br.com.lab.impacta.investment.infrasctructure.http;

import br.com.lab.impacta.investment.integration.dto.request.DebitAccountRequest;
import br.com.lab.impacta.investment.integration.valueObject.AccountBalanceVo;
import br.com.lab.impacta.investment.integration.valueObject.DebitAccountVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "${lab.investment.paths.client-account-name}",
             url = "${lab.investment.paths.client-account-base-url}")
public interface AccountClient {

    @GetMapping("${lab.investment.paths.client-account-balance-path-url}")
    AccountBalanceVo getBalance(@PathVariable("accountId") Long accountId);

    @PostMapping("${lab.investment.paths.client-account-debit-path-url}")
    DebitAccountVo debit(@PathVariable("accountId") Long accountId,
                         DebitAccountRequest debitAccountRequest);
}
