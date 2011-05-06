package br.org.aappe.erp.controller;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import br.org.aappe.erp.repository.EmpresaRepository;

/**
 * @author Phelipe Melanias
 */
@Resource
public class EmpresaController extends MainController {
    private final EmpresaRepository repository;

    public EmpresaController(Result result, EmpresaRepository repository) {
        super(result);
        this.repository = repository;
    }
}