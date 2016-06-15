package com.matkansuunnittelija.generators;

/**
 * Luokka joka kuvaa css-tyylin objectia, esim "border: 1px solid black;".
 *
 * @author Samuli
 */
public class CSSStyleObject {

    private final String key;
    private final String value;

    /**
     * Konstruktori jolla luodaan CSSStyleObject-olio
     * 
     * @param key Tyylin avain, esim "border"
     * @param value Tyylin arvo, esim "2px"
     */
    CSSStyleObject(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Palauttaa CSSStyleObjektin avaimen.
     * 
     * @return CSSStyleObjektin avain
     */
    public String getkey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
