/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matkansuunnittelija;


/**
 *
 * @author Samuli
 */
public class DayPlan {
    private final String name;
    private DayEvent[] dayEvents;
    
    public DayPlan(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
    
    public DayEvent[] getDayEvents()
    {
        return dayEvents;
    }
    
    public void addNewDayEvent(String time, String description)
    {
        if(dayEvents == null)
            dayEvents = new DayEvent[0];
            
        DayEvent event = new DayEvent(time, description);
        DayEvent[] newDayEvents = new DayEvent[dayEvents.length+1];
        if(dayEvents.length != 0)
            System.arraycopy(dayEvents, 0, newDayEvents, 0, dayEvents.length-1);
        newDayEvents[dayEvents.length] = event;
        dayEvents = newDayEvents;
    }
}
