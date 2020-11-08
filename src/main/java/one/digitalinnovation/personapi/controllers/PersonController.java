package one.digitalinnovation.personapi.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import one.digitalinnovation.personapi.dto.PersonDTO;
import one.digitalinnovation.personapi.dto.mapper.PersonMapper;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.service.PersonService;

@RestController
@RequestMapping("/api/v1/people")
@RequiredArgsConstructor
class PersonController {

    private final PersonService personService;

    private final PersonMapper personMapper;

    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAll() {
        List<PersonDTO> dto = personService.getAll().stream().map(personMapper::toDto).collect(Collectors.toList());

        if (dto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getOne(@PathVariable Long id) {
        return ResponseEntity.of(personService.getOne(id).map(personMapper::toDto));
    }

    @PostMapping
    public ResponseEntity<PersonDTO> createPerson(@RequestBody @Valid PersonDTO dto) {
        Person person = personMapper.toModel(dto);

        personService.createPerson(person);

        return ResponseEntity.created(URI.create("/api/v1/people/" + person.getId())).body(personMapper.toDto(person));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO>  updatePerson(@PathVariable Long id, @RequestBody @Valid PersonDTO dto) {
        Person dataToUpdate = personMapper.toModel(dto);

        Person updatedPerson = personService.updatePerson(id, dataToUpdate);

        return ResponseEntity.ok(personMapper.toDto(updatedPerson));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable Long id) {
        try {
            personService.deletePerson(id);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

}
