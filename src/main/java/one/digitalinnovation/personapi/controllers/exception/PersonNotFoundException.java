package one.digitalinnovation.personapi.controllers.exception;

import java.text.MessageFormat;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 145735988681696292L;

    public PersonNotFoundException(Long id) {
        super(MessageFormat.format("Person with ID {0} not found! ", id));
    }


     
}
