package com.campusdual.ejercicio5;

import java.util.ArrayList;
import java.util.List;

public class Patient {
    private String name;
    private String surname;
    private Integer weight;
    private Integer height;
    private Integer age;
    private String gender; // mirar de ponerlo como enumerado

    private List<Diet> dietList;

    public Patient() {
        dietList = new ArrayList<>();;
    }
    public Patient(String name, String surname, Integer weight, Integer height, Integer age, String gender) {
        this.name = name;
        this.surname = surname;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
        dietList = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
