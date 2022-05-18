package br.com.lab.impacta.investment.domain.model;

import br.com.lab.impacta.investment.domain.exception.InvestmentAccountWithoutBalanceException;
import br.com.lab.impacta.investment.domain.exception.InvestmentAccountWithoutBalanceForPrivateInvestment;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Long accountId;
    private Double amount;

    @CreationTimestamp
    private LocalDateTime creationDate;
    private boolean privateInvestment;

    public Investment(Long productId, Long accountId, Double amount) {
        this.productId = productId;
        this.accountId = accountId;
        this.amount = amount;
    }

    public void checkSufficientBalanceForInvestment(Double accountBalance) {
        if (this.amount > accountBalance) {
            throw new InvestmentAccountWithoutBalanceException();
        }
    }

    public void verifyProductIsPrivateOrDefaultForInvestment(Double accountBalance, Product product) {
        if (!product.isPrivateInvestment()) {
            this.privateInvestment = false;
        } else if (product.isPrivateInvestment() && (accountBalance >= product.getMinimumAmountForInvestment())) {
            this.privateInvestment = true;
        } else {
            throw new InvestmentAccountWithoutBalanceForPrivateInvestment();
        }
    }
}
