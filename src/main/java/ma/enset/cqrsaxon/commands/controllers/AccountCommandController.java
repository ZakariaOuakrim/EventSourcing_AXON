package ma.enset.cqrsaxon.commands.controllers;

import lombok.AllArgsConstructor;
import ma.enset.cqrsaxon.commenApi.commands.CreateAccountCommand;
import ma.enset.cqrsaxon.commenApi.commands.CreditAccountCommand;
import ma.enset.cqrsaxon.commenApi.commands.DebitAccountCommand;
import ma.enset.cqrsaxon.commenApi.dto.CreateAccountDTO;
import ma.enset.cqrsaxon.commenApi.dto.CreditAccountDTO;
import ma.enset.cqrsaxon.commenApi.dto.DebitAccountDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/commands/account")
@AllArgsConstructor
public class AccountCommandController {
    private CommandGateway commandGateway;
    private EventStore eventStore;

    @PostMapping("/create")
    public CompletableFuture<String> createAccount(@RequestBody CreateAccountDTO request){
     CompletableFuture<String> result= commandGateway.send(new CreateAccountCommand(
                UUID.randomUUID().toString(),
                request.currency(),
                request.initialBalance()
        ));
     return result;
    }
    @PostMapping("/credit")
    public CompletableFuture<String> creditAccount(@RequestBody CreditAccountDTO request){
        CompletableFuture<String> result= commandGateway.send(new CreditAccountCommand(
                request.getAccountId(),
                request.getAmount(),
                request.getCurrency()

        ));
        return result;
    }
    @PostMapping("/debit")
    public CompletableFuture<String> creditAccount(@RequestBody DebitAccountDTO request){
        CompletableFuture<String> result= commandGateway.send(new DebitAccountCommand(
                request.getAccountId(),
                request.getAmount(),
                request.getCurrency()

        ));
        return result;
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity exceptionHandler(Exception exception){
        return ResponseEntity.internalServerError().body(exception.getMessage());
    }

    @GetMapping("/eventStore/{id}")
    public Stream eventStore(String id){
        return eventStore.readEvents(id).asStream();
    }


}
