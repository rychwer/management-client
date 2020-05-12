package br.com.mecontrata.management.client.facade;

import br.com.mecontrata.management.client.facade.exception.ClientAlreadyExistsException;
import br.com.mecontrata.management.client.facade.exception.ClientNotFoundException;
import br.com.mecontrata.management.client.facade.exception.EmailNotConfirmedException;
import br.com.mecontrata.management.client.domain.ClientDTO;
import br.com.mecontrata.management.client.service.ClientService;
import br.com.server.resource.domain.LoginDTO;
import br.com.server.resource.service.AuthorizationService;
import br.com.server.resource.service.KafkaService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class ClientFacade {

    private ClientService clientService;
    private MessageSource messageSource;
    private KafkaService emailNotificationService;
    private AuthorizationService authorizationService;

    public ClientFacade(ClientService clientService, MessageSource messageSource,
                        @Qualifier("emailNotification") KafkaService emailNotificationService,
                        AuthorizationService authorizationService) {
        this.clientService = clientService;
        this.messageSource = messageSource;
        this.emailNotificationService = emailNotificationService;
        this.authorizationService = authorizationService;
    }

    public ClientDTO createClient(ClientDTO clientDTO) {

        final ClientDTO findClient = this.clientService.hasClientEmail(clientDTO.getEmail());

        if (!ObjectUtils.isEmpty(findClient)) {
            if (!findClient.getEmailConfirmed()) {
                throw new EmailNotConfirmedException(messageSource.getMessage("error.client.email.not.confirmed", null, null));
            } else {
                throw new ClientAlreadyExistsException(messageSource.getMessage("error.client.already.exists", null, null));
            }
        }

        //TODO wrapper para criação de usuário
        authorizationService.createClientLogin(new LoginDTO(clientDTO.getEmail(), clientDTO.getPassword()));

        final ClientDTO saveClient = this.clientService.createClient(clientDTO);

        emailNotificationService.sendNotification(saveClient);

        return saveClient;
    }

    public ClientDTO getClient(String email) {
        final ClientDTO clientDTO = this.clientService.hasClientEmail(email);

        if(clientDTO == null) {
            throw new ClientNotFoundException(messageSource.getMessage("error.client.not.found", null, null));
        }

        return clientDTO;
    }

    public void deleteClient(String email) {

        //TODO Mudança de estado para deleção / Chamar API de Autenticação para desabilitar usuário

        if(this.clientService.hasClientEmail(email) == null) {
            throw new ClientNotFoundException(messageSource.getMessage("error.client.not.found", null, null));
        }

        this.clientService.deleteClient(email);
    }

}
