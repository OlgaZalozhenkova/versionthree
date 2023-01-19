package olga.zalozhenkova.versionthree.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import olga.zalozhenkova.versionthree.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

}