package br.org.aappe.erp.enums;

import java.util.Arrays;
import java.util.List;

/**
 * @author Phelipe Melanias
 */
public enum Month {
    JANEIRO("Janeiro", 1),
    FEVEREIRO("Fevereiro", 2),
    MARCO("Março", 3),
    ABRIL("Abril", 4),
    MAIO("Maio", 5),
    JUNHO("Junho", 6),
    JULHO("Julho", 7),
    AGOSTO("Agosto", 8),
    SETEMBRO("Setembro", 9),
    OUTUBRO("Outubro", 10),
    NOVEMBRO("Novembro", 11),
    DEZEMBRO("Dezembro", 12);

    private String month;
    private int index;

    private Month(String month, int index) {
        this.month = month;
        this.index = index;
    }

    //getters
    public String getMonth() { return month; }
    public int getIndex() { return index; }

    public static List<Month> getAll() { return Arrays.asList(values()); }
}