package br.com.mecontrata.management.client.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class PhoneDTO {

    @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$", message = "${phone.invalid}")
    @NotNull(message = "${phone.invalid}")
    @NotEmpty(message = "${phone.invalid}")
    @JsonProperty("telefone")
    private String phoneNumber;

}
