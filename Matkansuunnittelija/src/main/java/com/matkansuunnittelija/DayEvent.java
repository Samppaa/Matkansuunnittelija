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
public class DayEvent {
    private String time;
    private String description;
    
    public DayEvent(String time, String description)
    {
        this.time = time;
        this.description = description;
    }
    
    public String getTime()
    {
        return time;
    }
    
    public String getDescription()
    {
        return description;
    }
}
