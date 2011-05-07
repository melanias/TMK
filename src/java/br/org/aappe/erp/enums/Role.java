package br.org.aappe.erp.enums;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author Phelipe Melanias
 */
public enum Role implements Serializable {
    ESTAGIARIO("Estagiário"), REPRESENTANTE("Representante"), OPERADOR("Operador"), GERENTE("Gerente");

    private String userRole;

    private Role(String userRole) {
        this.userRole = userRole;
    }

    //getters
    public String getUserRole() { return userRole; }

    public static List<Role> getAll() { return Arrays.asList(values()); }
}