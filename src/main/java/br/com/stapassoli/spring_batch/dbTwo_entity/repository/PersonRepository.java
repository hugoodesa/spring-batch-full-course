package br.com.stapassoli.spring_batch.dbTwo_entity.repository;

import br.com.stapassoli.spring_batch.dbTwo_entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
