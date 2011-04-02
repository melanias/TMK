package br.org.aappe.erp.enums;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author Phelipe Melanias
 */
public enum Role implements Serializable {
    ESTAGIARIO("Estagiário"), DIRETOR("Diretor"), ADMINISTRADOR("Administrador");

    private String userRole;

    private Role(String userRole) {
        this.userRole = userRole;
    }

    //getter
    public String getUserRole() {
        return userRole;
    }

    public static Collection<Role> getAll() {
        Collection<Role> roles = new ArrayList<Role>();
        roles.addAll(Arrays.asList(values()));

        return roles;
    }
}