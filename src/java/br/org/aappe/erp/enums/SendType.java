package br.org.aappe.erp.enums;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author Phelipe Melanias
 */
public enum SendType implements Serializable {
    NEWSLETTER("Newsletter"), SMS("SMS"), ALL("Ambos");

    private String type;

    private SendType(String type) {
        this.type = type;
    }

    //getters
    public String getType() { return type; }

    public static List<SendType> getAll() { return Arrays.asList(values()); }
}