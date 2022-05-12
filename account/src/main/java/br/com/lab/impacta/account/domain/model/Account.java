package br.com.lab.impacta.account.domain.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long number;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person costumer;

    private Double balance;

    public boolean debit(Double valueOfDebit) {
        if (this.getBalance() < valueOfDebit) {
            return false;
        }

        Double debitedAmount = this.getBalance() - valueOfDebit;

        this.setBalance(debitedAmount);

        return true;
    }
}
