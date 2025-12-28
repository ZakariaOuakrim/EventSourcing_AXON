package ma.enset.cqrsaxon.query.repo;

import ma.enset.cqrsaxon.query.entities.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<AccountTransaction,Long> {
}
