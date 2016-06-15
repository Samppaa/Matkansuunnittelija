package com.matkansuunnittelija.generators;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Luokka joka vastaa CSS-tyylin generoinnista.
 *
 * @author Samuli
 */
public class CSSStyleBuilder {

    private final TreeMap styleObjects = new TreeMap();

    /**
     * Lisää uuden kohdan CSS-tyyliin.
     * Esim addNewValueForStyle("table, th, td", "border", "1px solid black");
     * @param style Tyylin nimi, esim. "table"
     * @param key Muuttujan nimi esim. "border"
     * @param value  Muuttujalle annettavan arvon nimi, esim. "1px solid black"
     */
    public void addNewValueForStyle(String style, String key, String value) {
        ArrayList<CSSStyleObject> styles;
        if (!styleObjects.containsKey(style)) {
            styles = new ArrayList();
        } else {
            styles = (ArrayList) styleObjects.get(style);
        }
        styles.add(new CSSStyleObject(key, value));
        styleObjects.put(style, styles);
    }

    private String getCSSForStyle(String style) {
        StringBuilder builder = new StringBuilder();
        builder.append(style).append(" {\n");
        ArrayList<CSSStyleObject> styles = (ArrayList) styleObjects.get(style);
        for (CSSStyleObject styleObject : styles) {
            builder.append(styleObject.getkey()).append(": ").append(styleObject.getValue()).append(";\n");
        }
        builder.append("}");
        return builder.toString();
    }

    /**
     * Generoi CSS-tyylin <style>..</style> tagien sisään.
     * @return CSS-tyyli
     */
    public String generateCSS() {
        StringBuilder builder = new StringBuilder();
        builder.append("<style>\n");
        for (String key : new ArrayList<String>(styleObjects.keySet())) {
            builder.append(getCSSForStyle(key)).append("\n");
        }
        builder.append("</style>\n");
        return builder.toString();
    }
}
