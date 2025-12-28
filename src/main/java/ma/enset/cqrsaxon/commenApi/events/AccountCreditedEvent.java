package ma.enset.cqrsaxon.commenApi.events;

import lombok.Getter;
import ma.enset.cqrsaxon.commenApi.enums.AccountStatus;

public class AccountCreditedEvent extends BaseEvent<String>{
    @Getter private double amount;
    @Getter private String currency;

    public AccountCreditedEvent(String id, double amount, String currency) {
        super(id);
        this.amount = amount;
        this.currency = currency;
    }
}
