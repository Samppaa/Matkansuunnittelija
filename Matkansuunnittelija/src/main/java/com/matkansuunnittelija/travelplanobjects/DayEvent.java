package com.matkansuunnittelija.travelplanobjects;

import java.time.LocalTime;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Tapahtuma joita voi sisältyä päiviin mielivaltainen määrä.
 * Jokaisella tapahtumalla on nimi, kellonaika ja kuvaus.
 * @author Samuli
 */
public class DayEvent implements Comparable<DayEvent> {

    private final String name;
    private final String time;
    private final String description;

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
    
    private boolean validateTimeFormat(String time) {
        String regExTime = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        Pattern regExPattern = Pattern.compile(regExTime);
        Matcher matcher = regExPattern.matcher(time);
        return matcher.matches();
    }

    @Override
    public int compareTo(DayEvent o) {
        String regExTime = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        if (validateTimeFormat(o.getTime()) && validateTimeFormat(this.getTime())) {
            LocalTime thisTime = LocalTime.parse(this.getTime());
            LocalTime thatTime = LocalTime.parse(o.getTime());
            return thisTime.compareTo(thatTime);
        }
        return -1;
    }
}
