## Rakennekuvaus

Matkansuunnittelija-ohjelma koostuu neljästä pääalueesta: 
- HTMLDocumentin generointiin vaadittavista luokista
    - HTMLBuilder.java - Tarjoaa HTML-sivun luomiseen tarvittavat metodit
    - CSSStyleBuilder.java - Mahdollistaa CSS-tyylin luomisen
    - CSSStyleObject.java - CSS-tyyli objekti
    - HTMLTravelPlanGenerator.java - Vastaa matkasuunnitelman muuntamisesta HTML-muotoon
- Tiedostonkäsittelyyn käytettävästä FileManager.java luokasta. FileManager hoitaa siis matkasuunnitelmien lukemisen ja kirjoittaisen tiedostoon. FileManager hyödyntää googlen GSON:ia json-tiedoston käsittelyyn.
- Kontrollerista TravelPlanController.java, joka toimii solmukohtana käyttöliittymän ja muun ohjelman logiikan välillä. Käyttöliittymäluokat(kaikki luokat jotka päättyvät sanaan GUI) kutsuvat TravelPlanControllerin metodeja, ja kaikilla käyttöliittymäluokilla on viittaus TravelPlanControlleriin. TravelPlanController on toteutettu singleton-paradigmalla, eli oliosta on vain yksi instance ja sen saa staattisella getInstance()-metodilla.
- Perusluokat TravelPlan.java, DayPlan.java ja DayEvent.java. Nämä muodostavat ohjelman perusyksiköt eli matkasuunnitelmat, matkasuunnitelmien päivät ja päivien sisällä olevat tapahtumat. FileManager pitää kirjaa matkasuunnitelmista.


Yksinkertaisesti siis TravelPlanController ottaa vastaan käskyjä käyttöliittymältä ja käyttää FileManageria matkasuunnitelmien hallinnointiin kuten tallentamiseen.

Lukija voi ihmetellä miksi esim. TravelPlan-luokassa käytetään tavallista array tietotyyppiä tyyliin DayPlan[], vaikka voisi käyttää javan tarjoamaa ArrayList tietotyyppiä. Tämä on siksi, että GSON:in(jsonin käsittely luokka) on helppo serialisoida luokka json-muotoon, kun käytetään yksinkertaisia tietotyyppejä.

