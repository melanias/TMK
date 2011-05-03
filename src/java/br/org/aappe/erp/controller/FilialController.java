package br.org.aappe.erp.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.org.aappe.erp.annotations.Transactional;

import br.org.aappe.erp.bean.Filial;

/**
 * @author Jadson Ronald
 */
@Resource
public class FilialController {
    private final Result result;

    public FilialController(Result result) {
        this.result = result;
    }

    @Get("/filial/nova")
    public void frmCadastro() {
        result.include("title", "Cadastrar Filial");
    }

    @Transactional
    @Post("/filial/nova")
    public void cadastrar(final Filial filial) {
        result.redirectTo(this).frmCadastro();
    }
}