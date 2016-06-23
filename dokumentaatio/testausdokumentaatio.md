# Testausdokumentaatio

Yleisesti kaikki mikä oli järkevää testata yksikkötesteillä on testattu.

##### ErrorManager.java
Kun PIT-raporttia katsoo, niin voi havaita, että *ErrorManager.java:n* mutaatioista on tapettu vain muutama. Tämä johtuu siitä, että `initErrorCodes()` suoritetaan vain kerran, kun olio luodaan. Luokka on toteutettu singletontyylillä eli niitä on vain yksi, ja se luodaan automaattisesti, kun käyttäjä kutsuu `getStringForErrorCode(StatusCode code)`-funktiota. Näiden mutanttien tappaminen ei ole hirveän suoraviivaista ilman muuttamatta luokan rakennetta, joten kaikkien virheviestien toiminta on testattu käsin ohjelman toiminnan yhteydessä, ja todettu toimivaksi.

##### FileManager.java
PIT-raportista voi huomata, että *FileManager.java-luokassa* on jonkin verran tappamattomia mutantteja. Nämä liittyvät yksityiseen(private) `saveDataFile()`-funktioon, jota on hankala testa yksikkötestillä. Toiminta on täten testattu ohjelman käytön yhteydessä. Funktio luo uuden *plans.json* tiedoston mikäli sellaista ei löydy. Ei ole siis mahdollista, että ohjelma ei pystyisi tallentamaan tietoaan. Ominaisuus on testattu kattavasti käyttämällä ohjelmaa eri tilanteissa esim. poistamalla *plans.json* ennen tallennusta. JSON-validointi on myös testattu käyttötestaamalla ohjelmaa.

##### TravelPlanController.java
TravelPlanControllerin tappamattomat mutantit liittyvät tilanteisiin, joissa ei olla testattu mahdollisuutta, että tiedostoa ei ole olemassa. Eli siis IOException tilannetta ei ole testattu, koska skenaario jossa tiedosto ei olisi olemassa on mahdoton, sillä se luodaan aina sitten uudelleen. Käytännössä tilanne jossa IOException voisi tulla on tilanne, jossa ohjelmalla ei jollakin tapaa olisi oikeuksia luoda tiedostoa tai kovalevyltä olisi tila lopussa(käytönnössä mahdoton testata). Mikäli IOException tulee, ohjelma toimii kuitenkin oikein.

Edellä mainittujen puutteiden ohella kaikki muut tappamattomat mutantit yms ovat asioita, joita ei ole mielekästä testata. Näiden osien toiminnallisuus on testattu käyttämällä ohjelmaa eri tilanteissa.
