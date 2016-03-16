package com.rest.api;

import javax.persistence.*;

@Entity
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    String firstName;
    String lastName;

    @ManyToOne()
    Show show;

    public Character() {
    }

    public Character(String firstName, String lastName, Show show) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.show = show;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Show getShow() {
        return show;
    }
}