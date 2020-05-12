package br.com.mecontrata.management.client.entity;

import br.com.mecontrata.management.client.domain.PhoneDTO;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "client")
@Data
public class ClientEntity {

    @Id
    private String email;

    private String firstName;

    private String lastName;

    private String address;

    private String addressComplement;

    private List<PhoneDTO> phones;

    @Version
    private Long version;

    private Boolean emailConfirmed = Boolean.FALSE;

}
