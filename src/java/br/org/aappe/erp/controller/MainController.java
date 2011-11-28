package br.org.aappe.erp.controller;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

import br.org.aappe.erp.session.EmployeeSession;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Phelipe Melanias
 */
public abstract class MainController {
    protected final Result result;
    protected Validator validator;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected EmployeeSession employeeSession;

    public MainController(Result result) {
        this.result = result;
    }

    public MainController(Result result, Validator validator) {
        this.result = result;
        this.validator = validator;
    }

    public MainController(Result result, Validator validator, HttpServletRequest request) {
        this.result = result;
        this.validator = validator;
        this.request = request;
    }

    public MainController(Result result, Validator validator, HttpServletResponse response) {
        this.result = result;
        this.validator = validator;
        this.response = response;
    }

    public MainController(Result result, Validator validator, EmployeeSession employeeSession) {
        this.result = result;
        this.validator = validator;
        this.employeeSession = employeeSession;
    }

    public MainController(Result result, Validator validator, HttpServletRequest request, EmployeeSession employeeSession) {
        this.result = result;
        this.validator = validator;
        this.request = request;
        this.employeeSession = employeeSession;
    }
}