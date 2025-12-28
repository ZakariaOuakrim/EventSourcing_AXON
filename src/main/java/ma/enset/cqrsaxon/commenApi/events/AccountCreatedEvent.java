package ma.enset.cqrsaxon.commenApi.events;

import lombok.Getter;
import ma.enset.cqrsaxon.commenApi.enums.AccountStatus;

public class AccountCreatedEvent extends BaseEvent<String>{
   @Getter private String currency;
    @Getter private double initialBalance;
    @Getter private AccountStatus status;

    public AccountCreatedEvent(String id, String currency, double initialBalance, AccountStatus status) {
        super(id);
        this.currency = currency;
        this.initialBalance = initialBalance;
        this.status = status;
    }
}
