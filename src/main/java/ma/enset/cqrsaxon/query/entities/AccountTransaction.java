package ma.enset.cqrsaxon.query.entities;

import lombok.*;
import ma.enset.cqrsaxon.query.enums.TransactionType;

import javax.persistence.*;
import java.time.Instant;

@Entity
@AllArgsConstructor @NoArgsConstructor @Builder @Data
public class AccountTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant instant;
    private double amount;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @ManyToOne
    private Account account;
}
