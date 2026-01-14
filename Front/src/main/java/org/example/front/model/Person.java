package org.example.front.model;

import org.example.front.RSA.RsaImplementation;

public class Person {

    public static Person person = new Person("","","");

    public static Person getPerson() {
        return person;
    }
    public static void setPerson(Person p) {
        person = p;
    }

    public static void clearPerson() {
        person = new Person("","","");
    }

    private String name;
    private String password;
    private String public_key;


    private RsaImplementation rsa;

    public Person(String name, String password, String public_key) {
        this.name = name;
        this.password = password;
        this.public_key = public_key;
    }


    public RsaImplementation getRsa() {
        return rsa;
    }

    public void setRsa(RsaImplementation rsa) {
        this.rsa = rsa;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getPublic_key() { return public_key; }
    public void setPublic_key(String public_key) { this.public_key = public_key; }
}