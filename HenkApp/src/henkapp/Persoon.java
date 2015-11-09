/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package henkapp;

import java.util.Objects;

/**
 *
 * @author Stijn
 */
public class Persoon implements Comparable<Persoon>{

    private String naam;
    private String plaats;
    private String telefoon;

    Persoon(String naam, String plaats, String telefoon) {
        this.naam = naam;
        this.plaats = plaats;
        this.telefoon = telefoon;
    }

    @Override
    public String toString() {
        return naam + " - " + telefoon;
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

    public String getTelefoon() {
        return telefoon;
    }

    public void setTelefoon(String telefoon) {
        this.telefoon = telefoon;
    }

    boolean contentEquals(Persoon p) {
        if (this.naam.equals(p.getNaam())) {
            if (this.plaats.equals(p.getPlaats())) {
                if (this.telefoon.equals(p.getTelefoon())) {
                    return true;
                }
            }
        }
        return false;
    }

    //Bron: http://stackoverflow.com/questions/185937/overriding-the-java-equals-method-quirk
    @Override
    public boolean equals(Object other) {
        if (other == null) { //Wanneer het andere object null is zijn de twee ongelijk
            return false;
        }
        if (other.hashCode() == this.hashCode()) {
            return true;
        }
        if (!(other instanceof Persoon)) {
            return false;
        }
        Persoon otherPersoon = (Persoon) other;
        if (this.naam.equals(otherPersoon.getNaam())) {
            if (this.plaats.equals(otherPersoon.getPlaats())) {
                if (this.telefoon.equals(otherPersoon.getTelefoon())) {
                    return true;
                }
            }
        }
        return false;
    }

    // Automatisch gegenereerde hashCode() methode
    // Geen aanpassingen omdat het me zo goed lijkt
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.naam);
        hash = 83 * hash + Objects.hashCode(this.plaats);
        hash = 83 * hash + Objects.hashCode(this.telefoon);
        return hash;
    }
    
    // Bron:http://www.tutorialspoint.com/java/java_using_comparator.htm
    // Vergelijken van de namen.
    @Override
    public int compareTo(Persoon o) {
        return (this.naam).compareTo(o.naam);
    }
}
