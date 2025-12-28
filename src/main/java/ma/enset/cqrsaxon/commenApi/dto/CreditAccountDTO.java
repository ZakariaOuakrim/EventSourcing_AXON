package ma.enset.cqrsaxon.commenApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor @Data
public class CreditAccountDTO {
     private String accountId;
     private double amount;
     private String currency;
}
