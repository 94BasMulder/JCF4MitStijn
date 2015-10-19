/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package henkapp;

/**
 *
 * @author Bas
 */
public class Person {

    
    private String naam;
    private String plaats;
    private String telefoonnummer;
    
    
    public Person() {
    }
    
    
    public Person(String naam, String plaats, String telefoonnummer) {
        this.naam = naam;
        this.plaats = plaats;
        this.telefoonnummer = telefoonnummer;
    }

    @Override
    public String toString() {
        return naam;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getPlaats() {
        return plaats;
    }

    public void setPlaats(String plaats) {
        this.plaats = plaats;
    }

    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    public void setTelefoonnummer(String telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }
}
