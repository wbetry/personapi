package one.digitalinnovation.personapi.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;
import one.digitalinnovation.personapi.enums.PhoneType;

@Data
public class PersonDTO {

    private Long id;
    @NotBlank
    @Size(min = 2, max = 100)
    private String firstName;
    @NotBlank
    @Size(min = 2, max = 100)
    private String lastName;
    @NotNull
    @CPF
    private String cpf;
    @Valid
    @NotNull
    private List<@NotNull PhoneDTO> phones;

    @Data
    public static class PhoneDTO {
        private Long id;
        @NotNull
        private PhoneType type;
        @NotBlank
        @Size(min = 10, max = 13)
        private String number;
    }

}