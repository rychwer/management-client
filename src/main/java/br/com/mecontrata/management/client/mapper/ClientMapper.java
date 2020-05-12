package br.com.mecontrata.management.client.mapper;

import br.com.mecontrata.management.client.domain.ClientDTO;
import br.com.mecontrata.management.client.entity.ClientEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientEntity toClientEntity(ClientDTO clientDTO);

    ClientDTO toClientDTO(ClientEntity clientEntity);
}
