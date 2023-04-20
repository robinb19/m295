package com.coop.helloo;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class HelloController {

    private final PersonRespository personRespository;

    public HelloController(PersonRespository personRespository){this.personRespository = personRespository;}

    @PostMapping("/savePerson")
    public String savePerson(@RequestBody Person person){
        personRespository.save(person);
        return person.getName() + "saved";
    }

    @PostMapping("/updatePerson")
    public String updatePerson(@RequestBody Person person){
        personRespository.save(person);
        return person.getName() + "updated!";
    }

    @GetMapping("/getPersonById/{id}")
    public String getPersonById(@PathVariable Long id){
        Optional<Person> p = personRespository.findById(id);
        return p.get().getName();
    }

    @GetMapping("/sayHello")
    public String sayHello() {
        Person p1 = new Person();
        p1.setName("Robin");
        personRespository.save(p1);
        return "hallo";
    }

}