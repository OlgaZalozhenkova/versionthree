package olga.zalozhenkova.versionthree.controllers;

import olga.zalozhenkova.versionthree.util.PersonErrorResponse;
import olga.zalozhenkova.versionthree.util.PersonNotCreatedException;
import olga.zalozhenkova.versionthree.util.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import olga.zalozhenkova.versionthree.models.Person;
import olga.zalozhenkova.versionthree.services.PeopleService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController // @Controller + @ResponseBody над каждым методом
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping()
    public List<Person> getPeople() {
        return peopleService.findAll(); // Jackson конвертирует эти объекты в JSON
    }
//прав
    @GetMapping("/{id}")
    public Person getPerson(@PathVariable("id") int id) {
        return peopleService.findOne(id); // Jackson конвертирует в JSON
    }
// пок-т null
//    @GetMapping("/{id}")
//    public Optional<Person> getPerson(@PathVariable("id") int id) {
//        return peopleService.findOne(id); // Jackson конвертирует в JSON
//    }

//    @PostMapping()
//    public void create(@RequestBody @Valid Person person, BindingResult bindingResult) {
//        peopleService.save(person);
//    }

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Person person,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors
            ) {
                errorMsg.append(error.getField())
                        .append("-").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new PersonNotCreatedException(errorMsg.toString());
        }

        peopleService.save(person);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e) {
        PersonErrorResponse response = new PersonErrorResponse
                ("Person with this id wasn't found!",
                        System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);//404-статус
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotCreatedException e) {
        PersonErrorResponse response = new PersonErrorResponse
                (e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}




