package com.matkansuunnittelija.generators;

/**
 * Luokka joka tarjoaa yksinkertaisia metodeja HTML-koodin käsittelyyn.
 *
 * @author Samuli
 */
public class HTMLBuilder {

    /**
     * Antaa parametrina annetulla tagille suljetun version.
     * 
     * @param tag Tagi jolle luodaan suljettu versio
     * @return tagin suljettu versio
     */
    static public String getClosureTag(String tag) {
        return "</" + tag.substring(1, tag.length() - 1) + ">";
    }

    /**
     * Ympäröi stringin tageilla.
     * @param stringToEnclose Teksti joka suljetaan tagien sisään
     * @param tag Tagi jolla tekstipätkä suljetaan
     * @return String joka on ympäröity tageilla
     */
    static public String encloseString(String stringToEnclose, String tag) {
        StringBuilder builder = new StringBuilder();
        builder.append(tag);
        builder.append(stringToEnclose);
        builder.append(getClosureTag(tag));
        return builder.toString();
    }

    /**
     * Luo HTML-sivun headerille sulkutagin.
     * @return HTML-sivun headerin sulkutagi
     */
    static public String generateHTMLPageHeaderClosure() {
        return "</head>\n";
    }

    /**
     * Luo bodyn aloituksen HTML-sivulle.
     * @return Body tag
     */
    static public String generateHTMLPageBodyStart() {
        return "<body>\n";
    }
    
    /**
     * Luo charset tagin, joka määrittää dokumentin charsetin
     * @param charSet Esim UTF-8
     * @return Meta tagi charsetillä
     */
    static public String generateHTMLMetaCharSet(String charSet) {
        return "<meta charset=\"" + charSet + "\">\n";
    }

    /**
     * Generoi HTML-dokumentin tietyllä tyylillä ja bodylla.
     * @param cssStyle Käytettävä CSS-tyyli
     * @param bodyContent Sivussa käytettävä runko eli body
     * @return Html-dokumentti
     */
    static public String generateDocument(String cssStyle, String bodyContent) {
        StringBuilder webPageGenerator = new StringBuilder();
        webPageGenerator.append(HTMLBuilder.generateHTMLPageHeader());
        webPageGenerator.append(HTMLBuilder.generateHTMLMetaCharSet("UTF-8"));
        webPageGenerator.append(cssStyle);
        webPageGenerator.append(HTMLBuilder.generateHTMLPageHeaderClosure());
        webPageGenerator.append(bodyContent);
        webPageGenerator.append(HTMLBuilder.generateHTMLPageFooter());
        return webPageGenerator.toString();
    }

    /**
     * Generoi HTML-sivun headerin.
     * @return HTML-sivun header
     */
    static public String generateHTMLPageHeader() {
        StringBuilder builder = new StringBuilder();
        builder.append("<!DOCTYPE html>\n");
        builder.append("<html>\n");
        builder.append("<head>\n");
        return builder.toString();
    }

    /**
     * Generoi HTML-sivulle alaosan(footerin).
     * @return HTML-sivun footer
     */
    static public String generateHTMLPageFooter() {
        StringBuilder builder = new StringBuilder();
        builder.append("</body>\n");
        builder.append("</html>");
        return builder.toString();
    }

    /**
     * Luo <h1></h1> tagien sisään tekstiä.
     * @param text Teksti joka suljetaan <h1></h1> tagien sisään
     * @return tekstiä <h1></h1> tagien sisässä
     */
    static public String generateHeader1(String text) {
        return encloseString(text, "<h1>");
    }

    /**
     * Luo <h3></h3> tagien sisään tekstiä.
     * @param text Teksti joka suljetaan <h3></h3> tagien sisään
     * @return tekstiä <h3></h3> tagien sisässä
     */
    static public String generateHeader3(String text) {
        return encloseString(text, "<h3>");
    }

    /**
     * Luo linebreakin eli <br> tagin.
     * @return <br> tagi
     */
    static public String generateLineBreak() {
        return "<br>\n";
    }

}
