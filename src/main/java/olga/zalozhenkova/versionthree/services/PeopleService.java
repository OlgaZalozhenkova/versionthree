package olga.zalozhenkova.versionthree.services;


import lombok.extern.slf4j.Slf4j;
import olga.zalozhenkova.versionthree.util.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import olga.zalozhenkova.versionthree.models.Person;
import olga.zalozhenkova.versionthree.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Slf4j
@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

// прав
    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElseThrow(PersonNotFoundException::new);
    }
// показывает null
//    public Optional<Person> findOne(int id) {
//        Optional<Person> foundPerson = peopleRepository.findById(id);
//        return foundPerson;
//    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }
}