package one.digitalinnovation.personapi.controllers;

import java.net.URI;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import one.digitalinnovation.personapi.dto.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.repository.PersonRepository;

@RestController
@RequestMapping("/api/v1/people")
@RequiredArgsConstructor
class PersonController {
    
    private final PersonRepository personRepository;

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody @Valid PersonDTO dto) {
        Person person = Person.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .cpf(Long.parseLong(dto.getCpf()))
                .build();
        person.addPhones(dto.getPhones()
                .stream()
                .map(PersonDTO.PhoneDTO::toPhone)
                .collect(Collectors.toList()));

        personRepository.save(person);

        return ResponseEntity.created(URI.create("//api/v1/people/" + person.getId())).body(person);
    }

}
