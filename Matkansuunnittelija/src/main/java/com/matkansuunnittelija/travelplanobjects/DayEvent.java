package com.matkansuunnittelija.travelplanobjects;

import java.util.Objects;

/**
 * Tapahtuma joita voi sisältyä päiviin mielivaltainen määrä.
 * Jokaisella tapahtumalla on nimi, kellonaika ja kuvaus.
 * @author Samuli
 */
public class DayEvent {

    private String name;
    private String time;
    private String description;

    /**
     * Konstruktori joka luo DayEvent tyyppisen olion.
     * @param name Tapahtuman nimi
     * @param time Tapahtuman aika
     * @param description Tapahtuman kuvaus
     */
    public DayEvent(String name, String time, String description) {
        this.name = name;
        this.time = time;
        this.description = description;
    }

    /**
     * Vertailee kahta DayEvent objectia niiden nimen perusteella.
     * @param o Objekti jota verrataan tähän olioon
     * @return True tai false riippuen siitä onko nimi sama
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DayEvent)) {
            return false;
        }
        DayEvent otherDay = (DayEvent) o;
        return otherDay.getName().equals(this.getName());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.name);
        return hash;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }
}
