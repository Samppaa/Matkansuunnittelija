package com.matkansuunnittelija.generators;

/**
 * Luokka jolla voi rakentaa helposti HTML-tauluja ilman että tarvitsee koskea raakaan koodiin.
 * @author Samuli
 */
public class HTMLTableBuilder {

    private final StringBuilder tableBuilder = new StringBuilder();
    private final int columnCount;

    private void generateTableHeader() {
        tableBuilder.append("<table style=\"width:100%\">\n");
    }

    private void addTableRow(boolean isHeader, String... values) {
        tableBuilder.append("<tr>\n");
        for (String value : values) {
            if (isHeader) {
                tableBuilder.append(HTMLBuilder.encloseString(value, "<th>"));
            } else {
                tableBuilder.append(HTMLBuilder.encloseString(value, "<td>"));
            }
            tableBuilder.append("\n");
        }
        tableBuilder.append("</tr>\n");
    }

    /**
     * Konstruktori jossa asetetaan taulun sarakkeiden nimet, nimien määrä määrittää samalla sarakkeiden määrän.
     * @param columnNames Taulun sarakkeiden nimet
     */
    public HTMLTableBuilder(String... columnNames) {
        columnCount = columnNames.length;
        generateTableHeader();
        addTableRow(true, columnNames);
    }

    /**
     * Lisää rivin tauluun.
     * @param values Arvot eli rivin sarakkeissa
     */
    public void addRowToTable(String... values) {
        addTableRow(false, values);
    }

    /**
     * Generoi taulun HTML-koodina.
     * @return taulu HTML-koodina
     */
    public String generateTable() {
        StringBuilder builder = new StringBuilder(tableBuilder);
        builder.append(HTMLBuilder.getClosureTag("<table>"));
        return builder.toString();
    }
}
