package br.org.aappe.erp.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

/**
 * @author Phelipe Melanias
 */
@Resource
public class IndexController extends MainController {

    public IndexController(Result result) {
        super(result);
    }

    @Get("/")
    public void index() {
        result.include("title", "AAPPE");
    }
}