/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matkansuunnittelija.filemanagement;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.matkansuunnittelija.TravelPlan;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author Samuli
 */
public class FileManager {
    private final Gson jsonParser = new Gson();
    private Writer fileWriter;
    private Reader fileReader;
    
    public List<TravelPlan> getTravelPlans() throws IOException
    {
        try(Reader reader = new InputStreamReader(FileManager.class.getResourceAsStream("/plans.json"), "UTF-8"))
        {
            List<TravelPlan> travelPlans = jsonParser.fromJson(reader, new TypeToken<List<TravelPlan>>(){}.getType());
            return travelPlans;
        }
    }
    
    public void addDayPlanToTravelPlan() throws IOException, URISyntaxException
    {
        File jsonFile = new File(FileManager.class.getResource("/plans.json").getFile());
        
        List<TravelPlan> travelPlans = getTravelPlans();
        travelPlans.get(0).addNewDayPlan();
        Files.write(jsonParser.toJson(travelPlans), jsonFile, Charsets.UTF_8);
    }
    
}
