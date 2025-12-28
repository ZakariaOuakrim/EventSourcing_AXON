package ma.enset.cqrsaxon.query.repo;

import ma.enset.cqrsaxon.query.entities.Account;
import ma.enset.cqrsaxon.query.entities.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
