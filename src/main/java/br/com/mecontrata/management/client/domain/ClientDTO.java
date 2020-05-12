package br.com.mecontrata.management.client.domain;

import br.com.mecontrata.management.client.controller.validation.annotation.TelefoneFields;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class ClientDTO {

    @NotEmpty(message = "{first.name.notnull}")
    @JsonProperty("primeiro_nome")
    private String firstName;

    @NotEmpty(message = "{last.name.notnull}")
    @JsonProperty("ultimo_nome")
    private String lastName;

    @NotEmpty(message = "{address.notnull}")
    @JsonProperty("endereco")
    private String address;

    @JsonProperty("complemento_endereco")
    private String addressComplement;

    @NotNull(message = "{list.phone.notnull}")
    @JsonProperty("telefones")
    @TelefoneFields
    private List<PhoneDTO> phones;

    @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", message = "{email.invalid}")
    @JsonProperty("email")
    private String email;

    @NotNull(message = "{password.notnull}")
    @JsonProperty("senha")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;

    @JsonIgnore
    private Boolean emailConfirmed;

}
