package br.com.lab.impacta.account.domain.exception;

public class AccountWithoutBalanceException extends RuntimeException {

    private String description;

    public String getDescription() {
        return this.description;
    }

    public AccountWithoutBalanceException() {
        super();
    }

    public AccountWithoutBalanceException(String description, String message) {
        super(message);

        this.description = description;
    }
}
