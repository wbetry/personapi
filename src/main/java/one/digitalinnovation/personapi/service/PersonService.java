package one.digitalinnovation.personapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import one.digitalinnovation.personapi.controllers.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.repository.PersonRepository;

@RequiredArgsConstructor
@Service
public class PersonService {

    private final PersonRepository personRepository;

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

	public List<Person> getAll() {
		return personRepository.findAll();
	}

	public Optional<Person> getOne(Long id) {
		return personRepository.findById(id);
	}

	public Person updatePerson(Long id, Person personToUpdate) {
		Person person = verifyIfExists(id);

		personToUpdate.setId(person.getId());
		personToUpdate.setGenId(person.getGenId());

		personRepository.save(personToUpdate);

		return personToUpdate;
	}

	public void deletePerson(Long id) {
		personRepository.deleteById(id);
	}

	private Person verifyIfExists(Long id) {
		return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
	}

}
