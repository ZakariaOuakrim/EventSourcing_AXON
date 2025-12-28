package ma.enset.cqrsaxon.query.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.cqrsaxon.commenApi.enums.AccountStatus;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.Instant;
import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor @Data @Builder
public class Account {
    @Id
    private String id;
    private double balance;
    private Instant createdAt;
    private AccountStatus status;
    private String currency;
    @OneToMany(mappedBy = "account")
    private List<AccountTransaction> accountTransactions;

}
