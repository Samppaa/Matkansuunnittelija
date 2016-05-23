/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matkansuunnittelija;

import com.matkansuunnittelija.travelplanobjects.DayPlan;
import com.matkansuunnittelija.filemanagement.FileManager;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author Samuli
 */
public class MatkanSuunnittelija {

    public static void main(String[] args) throws IOException, URISyntaxException {
        DayPlan p = new DayPlan("dada");
        p.addNewDayEvent("test", "asasd", "adsasdasd");
        p.deleteDayEvent("test");
    }
}
