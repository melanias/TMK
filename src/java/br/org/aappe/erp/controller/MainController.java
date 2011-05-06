package br.org.aappe.erp.controller;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

/**
 * @author Phelipe Melanias
 */
public abstract class MainController {
    protected final Result result;
    protected Validator validator;

    public MainController(Result result) {
        this.result = result;
    }

    public MainController(Result result, Validator validator) {
        this.result = result;
        this.validator = validator;
    }
}