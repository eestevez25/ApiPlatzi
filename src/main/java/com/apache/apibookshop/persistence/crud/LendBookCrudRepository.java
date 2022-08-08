package com.apache.apibookshop.persistence.crud;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.apache.apibookshop.persistence.entity.LendBook;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LendBookCrudRepository extends CrudRepository<LendBook,Long> {
    Optional<List<LendBook>> findByTypeUser(Integer typeUser);

    /*@Query("Select count(id) FROM lendBook WHERE typeUser =type AND limitDate <date")
    int countByTypeUserAndLimitDate(@Param("type") Integer typeUser, @Param("date") LocalDate limitDate);*/

    LendBook findByTypeUserAndLimitDateGreaterThan(Integer typeUser, LocalDate limitDate);

    /*@Query("select p from Person p where p.fechaAlta >= :from and p.fechaAlta <= :to ")
    List<Person> findByFechaAltaBetween(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);*/


}
