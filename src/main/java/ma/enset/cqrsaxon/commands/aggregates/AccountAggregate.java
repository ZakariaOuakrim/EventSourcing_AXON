package ma.enset.cqrsaxon.commands.aggregates;

import ma.enset.cqrsaxon.commenApi.commands.CreateAccountCommand;
import ma.enset.cqrsaxon.commenApi.enums.AccountStatus;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class AccountAggregate {
    @AggregateIdentifier
    private String accountId;
    private double balance;
    private String currency;
    private AccountStatus status;

    public AccountAggregate(){

    }

    @CommandHandler
    public AccountAggregate(CreateAccountCommand command){
        if(command.getInitialBalance()<0) throw  new RuntimeException("Balance is negative");
        AggregateLifecycle.apply(new AccountCreatedEvent())
    }


}
