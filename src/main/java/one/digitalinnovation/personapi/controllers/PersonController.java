package one.digitalinnovation.personapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import antlr.collections.List;

@RestController
@RequestMapping("/api/v1/people")
class PersonController {
    
    @GetMapping
    public String getAll() {
        return "Ok, people";
    }

}
