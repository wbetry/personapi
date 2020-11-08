package one.digitalinnovation.personapi.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.entity.Phone;
import one.digitalinnovation.personapi.enums.PhoneType;

@Data
public class PersonDTO {

    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    @CPF
    private String cpf;
    @NotNull
    private List<@NotNull PhoneDTO> phones;

    public Person toPerson() {
        Person person = Person.builder()
                .firstName(firstName)
                .lastName(lastName)
                .cpf(Long.parseLong(cpf))
                .build();

        person.addPhones(phones.stream()
                .map(PersonDTO.PhoneDTO::toPhone)
                .collect(Collectors.toList())); 
    
        return person;
    }



    @Data
    public static class PhoneDTO {
        private Long id;
        @NotNull
        private PhoneType type;
        @NotBlank
        private String number;

        public Phone toPhone() {
            return Phone.builder()
                    .type(type)
                    .number(number)
                    .build();
        }
    }

    

}