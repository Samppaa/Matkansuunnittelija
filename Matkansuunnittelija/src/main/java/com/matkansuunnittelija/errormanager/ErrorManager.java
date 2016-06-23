package com.matkansuunnittelija.errormanager;

import com.matkansuunnittelija.StatusCode;
import java.util.HashMap;

/**
 * Luokka joka antaa virhekoodin tekstimuodossa.
 *
 * @author Samuli
 */
public class ErrorManager {

    private final HashMap<StatusCode, String> errors = new HashMap<>();
    private static ErrorManager errorManagerSingleton = null;

    protected ErrorManager() {
        initErrorCodes();
    }

    private String getStringForErrorCodePrivate(StatusCode code) {
        return errors.get(code);
    }

    /**
     * Palauttaa tekstiselityksen annetulle virhekoodille.
     *
     * @param code Virhekoodi
     * @return Tekstiesitys annetusta virhekoodista
     */
    public static String getStringForErrorCode(StatusCode code) {
        if (errorManagerSingleton == null) {
            errorManagerSingleton = new ErrorManager();
        }

        return errorManagerSingleton.getStringForErrorCodePrivate(code);
    }

    private void addErrorCode(StatusCode code, String errorText) {
        errors.put(code, errorText);
    }

    private void initErrorCodes() {
        addErrorCode(StatusCode.STATUS_TRAVEL_PLAN_CREATE_FAIL_ALREADY_EXISTS, "Matkasuunnitelma samalla nimellä on jo olemassa");
        addErrorCode(StatusCode.STATUS_TRAVEL_PLAN_CREATE_FAIL_DATE_DIFFERENCE_TOO_LONG, "Matkan pituus on liian suuri, maksimipituus on 14 päivää");
        addErrorCode(StatusCode.STATUS_TRAVEL_PLAN_CREATE_FAIL_FILE_NOT_FOUND, "Vakava virhe: Tietokanta tiedostoa ei löytynyt");
        addErrorCode(StatusCode.STATUS_TRAVEL_PLAN_CREATE_FAIL_START_DATE_AFTER_END_DATE, "Matkan päättymispäivä on ennen alkamispäivää tai samana päivänä");
        addErrorCode(StatusCode.STATUS_TRAVEL_PLAN_ADD_EVENT_TIME_FORMAT_WRONG_FORMAT, "Aika on väärässä muodossa. Oikea muoto: HH:mm(Esimerkiksi 02:20)");
        addErrorCode(StatusCode.STATUS_TRAVREL_PLAN_CREATE_FAIL_DATE_WRONG_FORMAT, "Päivämäärä on väärää muotoa. Oikea muoto on dd.mm.yyyy. Esimerkiksi 05.11.2012");
        addErrorCode(StatusCode.STATUS_TRAVEL_PLAN_ADD_EVENT_TIME_ALREADY_EXISTS, "Samaan aikaan on jo aktiviteetti. Valitse eri aika.");
        addErrorCode(StatusCode.STATUS_TRAVEL_PLAN_ADD_EVENT_ILLEGAL_CHARACTER, "Tapahtuman nimi sisältää merkin, joka ei ole sallittu");
    }
}
