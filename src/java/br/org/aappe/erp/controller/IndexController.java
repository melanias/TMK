package br.org.aappe.erp.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

/**
 * @author Phelipe Melanias
 */
@Resource
public class IndexController {
    private final Result result;

    public IndexController(Result result) {
        this.result = result;
    }

    @Get("/")
    public void index() {
        result.include("title", "AAPPE");
    }
}