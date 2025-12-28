package ma.enset.cqrsaxon.commenApi.dto;

public record CreateAccountDTO(
        String currency,
        double initialBalance
) {
}
