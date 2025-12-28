package ma.enset.cqrsaxon.commands.controllers;

import lombok.AllArgsConstructor;
import ma.enset.cqrsaxon.commenApi.commands.CreateAccountCommand;
import ma.enset.cqrsaxon.commenApi.dto.CreateAccountDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/commands/account")
@AllArgsConstructor
public class AccountCommandController {
    private CommandGateway commandGateway;

    @PostMapping("/create")
    public CompletableFuture<String> createAccount(@RequestBody CreateAccountDTO request){
     CompletableFuture<String> result= commandGateway.send(new CreateAccountCommand(
                UUID.randomUUID().toString(),
                request.currency(),
                request.initialBalance()
        ));
     return result;
    }
}
