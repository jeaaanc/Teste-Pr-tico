package com.gerenciadorfuncionarios.domain;

import java.time.LocalDate;
import java.time.Period;

public class Person {
    private String name;
    private LocalDate dateBirth;

    public Person(String name, LocalDate dateBirth) {
        this.name = name;
        this.dateBirth = dateBirth;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public int getIdade(){
        return Period.between(this.dateBirth, LocalDate.now()).getYears();
    }
}
