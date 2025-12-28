package ma.enset.cqrsaxon.query.service;

import lombok.AllArgsConstructor;
import ma.enset.cqrsaxon.query.entities.Account;
import ma.enset.cqrsaxon.query.queries.GetAllAccounts;
import ma.enset.cqrsaxon.query.repo.AccountRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountQueryHandler {
    private AccountRepository accountRepository;

    @QueryHandler
    public List<Account> on(GetAllAccounts query){
        return accountRepository.findAll();
    }
}
