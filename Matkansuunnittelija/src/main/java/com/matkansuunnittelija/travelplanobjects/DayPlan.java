package com.matkansuunnittelija.travelplanobjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Päivä joka sisältyy matkasuunnitelmaan. Päivällä on nimi ja mielivaltainen
 * määrä aktiviteetteja
 *
 * @author Samuli
 */
public class DayPlan {

    private final String name;
    private DayEvent[] dayEvents;

    /**
     * Konstruktori joka luo DayPlan-tyyppisen olion.
     * 
     * @param name Luotavan päivän nimi
     */
    public DayPlan(String name) {
        this.name = name;
        dayEvents = new DayEvent[0];
    }

    public String getName() {
        return name;
    }

    /**
     * Palauttaa päivän tapahtumat lista-muodossa.
     *
     * @return lista tapahtumista
     */
    public List<DayEvent> getDayEvents() {
        List<DayEvent> list = Arrays.asList(dayEvents);
        return Arrays.asList(dayEvents);
    }

    private ArrayList<DayEvent> getDayEventsAsArrayList() {
        return new ArrayList<>(getDayEvents());
    }

    /**
     * Palauttaa tapahtuman tietyllä nimellä.
     *
     * @param name Haetun tapahtuman nimi
     * @return DayEvent tapahtuman tai null jos ei löydy
     */
    public DayEvent getDayEventWithName(String name) {
        for (DayEvent e : getDayEvents()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }

        return null;
    }

    private boolean containsDayEventWithName(String name) {
        return getDayEventWithName(name) != null;
    }

    private void saveDayEvents(List<DayEvent> dayEvents) {
        if (dayEvents.isEmpty()) {
            this.dayEvents = new DayEvent[0];
        } else {
            this.dayEvents = new DayEvent[dayEvents.size()];
            dayEvents.toArray(this.dayEvents);
        }
    }

    /**
     * Tutkii onko päivällä tapahtumaa valittuna kellonaikana.
     *
     * @param time Aika joka tutkitaan
     * @return True tai false riippuen onko tapahtuma olemassa valitulla ajalla
     */
    public boolean hasEventWithTime(String time) {
        if (time.length() > 0) {
            StringBuilder tempBuffer = new StringBuilder(time);
            tempBuffer.deleteCharAt(0);
            for (DayEvent e : dayEvents) {
                if (e.getTime().equals(time) || e.getTime().equals("0" + time) || e.getTime().equals(tempBuffer.toString())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Lisää uuden tapahtuman valituilla tiedoilla.
     *
     * @param name Tapahtuman nimi
     * @param time Tapahtuman aika
     * @param description Tapahtuman kuvaus
     */
    public void addNewDayEvent(String name, String time, String description) {
        if (dayEvents == null) {
            dayEvents = new DayEvent[0];
        }
        if (containsDayEventWithName(name)) {
            return;
        }

        List<DayEvent> newDayEvents = new ArrayList<>(Arrays.asList(dayEvents));
        DayEvent event = new DayEvent(name, time, description);
        newDayEvents.add(event);
        dayEvents = new DayEvent[newDayEvents.size()];
        newDayEvents.toArray(dayEvents);
    }

    /**
     * Poistaa tapahtuman tietyllä nimellä.
     *
     * @param name Poistettavan tapahtuman nimi
     */
    public void deleteDayEvent(String name) {
        ArrayList<DayEvent> events = getDayEventsAsArrayList();
        DayEvent e = getDayEventWithName(name);
        if (e == null) {
            return;
        }
        events.remove(e);
        saveDayEvents(events);
    }
}
