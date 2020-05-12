package br.com.mecontrata.management.client.controller;

import br.com.mecontrata.management.client.domain.ClientDTO;
import br.com.mecontrata.management.client.facade.ClientFacade;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/client")
@Validated
public class ClientController {

    private ClientFacade clientFacade;

    public ClientController(ClientFacade clientFacade) {
        this.clientFacade = clientFacade;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ClientDTO createClient(@RequestBody @Valid ClientDTO clientDTO) {
        return clientFacade.createClient(clientDTO);
    }

    @GetMapping("/{email}")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ClientDTO getClient(@PathVariable @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
            message = "{email.invalid}") String email) {
        return this.clientFacade.getClient(email);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void deleteClient(@RequestParam @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
            message = "{email.invalid}") String email) {

        this.clientFacade.deleteClient(email);
    }

}
