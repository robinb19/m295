package com.coop.helloo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRespository extends JpaRepository<Person, Long>  {
    public List<Person> findByName(String name);
}
