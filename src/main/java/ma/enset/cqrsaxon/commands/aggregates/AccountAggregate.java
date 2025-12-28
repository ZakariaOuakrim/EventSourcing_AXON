package ma.enset.cqrsaxon.commands.aggregates;

import ma.enset.cqrsaxon.commenApi.commands.CreateAccountCommand;
import ma.enset.cqrsaxon.commenApi.commands.CreditAccountCommand;
import ma.enset.cqrsaxon.commenApi.commands.DebitAccountCommand;
import ma.enset.cqrsaxon.commenApi.enums.AccountStatus;
import ma.enset.cqrsaxon.commenApi.events.AccountCreatedEvent;
import ma.enset.cqrsaxon.commenApi.events.AccountCreditedEvent;
import ma.enset.cqrsaxon.commenApi.events.AccountDebitedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
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
        AggregateLifecycle.apply(new AccountCreatedEvent(
                command.getId(),
                command.getCurrency(),
                command.getInitialBalance(),
                AccountStatus.CREATED
                ));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event){
        this.accountId=event.getId();
        this.balance=event.getInitialBalance();
        this.currency=event.getCurrency();
        this.status=event.getStatus();
    }

    @CommandHandler
    public void handle(CreditAccountCommand command){
        if(command.getAmount()<0) throw  new RuntimeException("Amount must be positive");
        AggregateLifecycle.apply(new AccountCreditedEvent(
                command.getId(),
                command.getAmount(),
                command.getCurrency()
                ));
    }

    @EventSourcingHandler
    public void on(AccountCreditedEvent event){
        this.balance=this.balance+event.getAmount();
    }


    @CommandHandler
    public void handle(DebitAccountCommand command){
        if(command.getAmount()>this.balance) throw  new RuntimeException("not enough balance");
        AggregateLifecycle.apply(new AccountDebitedEvent(
                command.getId(),
                command.getAmount(),
                command.getCurrency()
        ));
    }

    @EventSourcingHandler
    public void on(AccountDebitedEvent event){
        this.balance=this.balance-event.getAmount();
    }

}
