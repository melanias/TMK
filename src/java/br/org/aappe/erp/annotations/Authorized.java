package br.org.aappe.erp.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import br.org.aappe.erp.enums.Role;

/**
 * @author Phelipe Melanias
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorized {

    Role[] value();
}