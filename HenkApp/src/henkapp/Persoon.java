/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package henkapp;

/**
 *
 * @author Stijn
 */
public class Persoon {

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
        if(this.naam.equals(p.getNaam()))
           if(this.plaats.equals(p.getPlaats()))
                if(this.telefoon.equals(p.getTelefoon()))
                    return true;
        return false;
    }
}
