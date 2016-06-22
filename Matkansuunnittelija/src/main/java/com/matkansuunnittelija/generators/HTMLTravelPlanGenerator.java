package com.matkansuunnittelija.generators;

import com.matkansuunnittelija.travelplanobjects.DayEvent;
import com.matkansuunnittelija.travelplanobjects.DayPlan;
import com.matkansuunnittelija.travelplanobjects.TravelPlan;

/**
 * Luokka joka vastaa HTML-sivun generaroinnista matkasuunnitelman pohjalta.
 *
 * @author Samuli
 */
public class HTMLTravelPlanGenerator {

    static private String generateHTMLForDay(DayPlan plan) {
        StringBuilder builder = new StringBuilder();
        builder.append(HTMLBuilder.generateHeader3(plan.getName())).append("\n");
        HTMLTableBuilder tableBuilder = new HTMLTableBuilder("Aika", "Nimi", "Kuvaus");
        for (DayEvent event : plan.getDayEventsOrderedByTime()) {
            tableBuilder.addRowToTable(event.getTime(), event.getName(), event.getDescription());
        }
        builder.append(tableBuilder.generateTable()).append("\n");
        return builder.toString();
    }

    static private String setTableStyle() {
        CSSStyleBuilder builder = new CSSStyleBuilder();
        builder.addNewValueForStyle("table, th, td", "border", "1px solid black");
        builder.addNewValueForStyle("table, th, td", "border-collapse", "collapse");
        builder.addNewValueForStyle("th, td", "padding", "5px");
        builder.addNewValueForStyle("th, td", "text-align", "left");
        return builder.generateCSS();
    }

    /**
     * Generoi HTML-sivun matkasuunnitelman pohjalta.
     * @param plan Matkasuunnitelma josta sivu generoidaan
     * @return matkasuunnitelma HTML-sivuna
     */
    static public String generateHTMLPage(TravelPlan plan) {
        StringBuilder webPageGenerator = new StringBuilder();
        webPageGenerator.append(HTMLBuilder.generateHeader1(plan.getName() + " " + plan.getStartDateAsString() + " - " + plan.getEndDateAsString())).append("\n");
        webPageGenerator.append(HTMLBuilder.generateLineBreak());
        for (DayPlan planForDay : plan.getDayPlansAsList()) {
            webPageGenerator.append(generateHTMLForDay(planForDay));
        }
        return HTMLBuilder.generateDocument(setTableStyle(), webPageGenerator.toString());
    }

}
