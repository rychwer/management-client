package br.com.mecontrata.management.client.service.impl;

import br.com.mecontrata.management.client.domain.ClientDTO;
import br.com.mecontrata.management.client.entity.ClientEntity;
import br.com.mecontrata.management.client.repository.ClientRepository;
import br.com.mecontrata.management.client.service.ClientService;
import br.com.mecontrata.management.client.mapper.ClientMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    private ClientMapper clientMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Transactional
    @Override
    public ClientDTO hasClientEmail(String email) {
        AtomicReference<ClientDTO> clientDTO = new AtomicReference<>(null);
        final Optional<ClientEntity> optionalClientEntity = this.clientRepository.findById(email);
        optionalClientEntity.ifPresent(item -> clientDTO.set(clientMapper.toClientDTO(item)));
        return clientDTO.get();
    }

    @Transactional
    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        final ClientEntity clientEntitySaved = this.clientRepository.save(clientMapper.toClientEntity(clientDTO));
        return clientMapper.toClientDTO(clientEntitySaved);
    }

    @Transactional
    @Override
    public void deleteClient(String email) {
        this.clientRepository.deleteById(email);
    }


}
