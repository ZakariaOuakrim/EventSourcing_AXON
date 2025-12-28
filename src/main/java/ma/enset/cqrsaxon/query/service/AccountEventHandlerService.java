package ma.enset.cqrsaxon.query.service;

import lombok.extern.slf4j.Slf4j;
import ma.enset.cqrsaxon.commenApi.events.AccountCreatedEvent;
import ma.enset.cqrsaxon.query.entities.Account;
import ma.enset.cqrsaxon.query.repo.AccountRepository;
import ma.enset.cqrsaxon.query.repo.TransactionRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class AccountEventHandlerService {
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    public AccountEventHandlerService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }
    @EventHandler
    public void on(AccountCreatedEvent event, EventMessage<AccountCreatedEvent> eventMessage){
        log.info("**********************");
        log.info("AccountCreatedEvent received");
        Account account=new Account();
        account.setId(event.getId());
        account.setBalance(event.getInitialBalance() );
        account.setStatus(event.getStatus());
        account.setCurrency(event.getCurrency());
        account.setCreatedAt(eventMessage.getTimestamp());
        accountRepository.save(account);
    }
}
