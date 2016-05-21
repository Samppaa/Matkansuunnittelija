/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matkansuunnittelija;

import com.matkansuunnittelija.filemanagement.FileManager;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 *
 * @author Samuli
 */
public class MatkanSuunnittelija {
    public static void main(String[] args) throws IOException, URISyntaxException {
        FileManager manager = new FileManager();
        /*List<TravelPlan> plans = manager.getTravelPlans();
        plans.stream().forEach((p) -> {
            System.out.println(p.getName());
            for(DayPlan d : p.getDayPlans())
            {
                System.out.println(d.getName());
                for(DayEvent e : d.getDayEvents())
                {
                    System.out.println(e.getDescription());
                }
            }
        });*/
        manager.addDayPlanToTravelPlan();
    }
}
