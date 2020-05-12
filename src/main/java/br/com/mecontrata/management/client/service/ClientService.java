package br.com.mecontrata.management.client.service;

import br.com.mecontrata.management.client.domain.ClientDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface ClientService {

    @Transactional
    public ClientDTO hasClientEmail(String email);

    @Transactional
    public ClientDTO createClient(ClientDTO clientDTO);

    @Transactional
    public void deleteClient(String email);


}
