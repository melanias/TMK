package br.org.aappe.erp.enums;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author Phelipe Melanias
 */
public enum DonorType implements Serializable {
    FISICA("Pessoa Física"), JURIDICA("Pessoa Jurídica");

    private String type;

    private DonorType(String type) {
        this.type = type;
    }

    //getters
    public String getType() { return type; }

    public static List<DonorType> getAll() { return Arrays.asList(values()); }
}