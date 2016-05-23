/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matkansuunnittelija.travelplanobjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Samuli
 */
public class DayPlan {

    private final String name;
    private DayEvent[] dayEvents;

    public DayPlan(String name) {
        this.name = name;
        dayEvents = new DayEvent[0];
    }

    public String getName() {
        return name;
    }

    public List<DayEvent> getDayEvents() {
        return Arrays.asList(dayEvents);
    }

    private ArrayList<DayEvent> getDayEventsAsArrayList() {
        return new ArrayList<>(getDayEvents());
    }

    private DayEvent getDayEventWithName(String name) {
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
            dayEvents.toArray(this.dayEvents);
        }
    }

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
