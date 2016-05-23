/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matkansuunnittelija.travelplanobjects;

import java.util.Objects;

/**
 *
 * @author Samuli
 */
public class DayEvent {

    private String name;
    private String time;
    private String description;

    public DayEvent(String name, String time, String description) {
        this.name = name;
        this.time = time;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DayEvent)) {
            return false;
        }
        DayEvent otherDay = (DayEvent) o;
        return otherDay.getName().equals(otherDay.getName());
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
