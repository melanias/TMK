package br.org.aappe.erp.enums;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author Phelipe Melanias
 */
public enum DonorStatus implements Serializable {
    INATIVO("Inativo(a)"), ATIVO("Ativo(a)"), NOVO("Novo(a)"), FIDELIZADO("Fidelizado(a)");

    private String status;

    private DonorStatus(String status) {
        this.status = status;
    }

    //getters
    public String getStatus() { return status; }

    public static List<DonorStatus> getAll() { return Arrays.asList(values()); }
}