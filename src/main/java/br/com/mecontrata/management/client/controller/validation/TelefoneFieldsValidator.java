package br.com.mecontrata.management.client.controller.validation;

import br.com.mecontrata.management.client.controller.validation.annotation.TelefoneFields;
import br.com.mecontrata.management.client.domain.PhoneDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TelefoneFieldsValidator implements ConstraintValidator<TelefoneFields, List<PhoneDTO>> {

    @Override
    public void initialize(TelefoneFields constraintAnnotation) {

    }

    @Override
    public boolean isValid(List<PhoneDTO> values, ConstraintValidatorContext context) {
        List<Boolean> listIsValid = new ArrayList<>();

        final String pattern = "(?:[0-9] ?){6,14}[0-9]$";
        final List<PhoneDTO> validPhones = values.stream().filter(item -> item.getPhoneNumber() != null && item.getPhoneNumber().matches(pattern)).collect(Collectors.toList());
        if(values.size() > validPhones.size()) {
            return false;
        }
        return true;
    }
}
