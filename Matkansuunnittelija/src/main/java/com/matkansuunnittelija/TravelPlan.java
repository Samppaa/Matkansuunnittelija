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
public class TravelPlan {
    private String name;
    private DayPlan[] dayPlans;
    
    public String getName()
    {
        return name;
    }
    
    public DayPlan[] getDayPlans()
    {
        return dayPlans;
    }
    
    public void addNewDayPlan()
    {
        DayPlan plan = new DayPlan("Test");
        plan.addNewDayEvent("Test1", "Test2");
        DayPlan[] newDayPlans = new DayPlan[dayPlans.length+1];
        System.arraycopy(dayPlans, 0, newDayPlans, 0, dayPlans.length);
        newDayPlans[dayPlans.length] = plan;
        dayPlans = newDayPlans;
    }
    
}
