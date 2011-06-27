package br.org.aappe.erp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.org.aappe.erp.bean.Mensagem;
import br.org.aappe.erp.enums.SendType;
import br.org.aappe.erp.repository.FuncionarioRepository;

/**
 * @author Phelipe Melanias
 */
@Resource
public class MensagemController extends MainController {
    private final FuncionarioRepository funcionarioRepository;

    public MensagemController(Result result, Validator validator, FuncionarioRepository funcionarioRepository) {
        super(result, validator);
        this.funcionarioRepository = funcionarioRepository;
    }

    @Get("/mensagem")
    public List<Mensagem> list() {
        result.include("title", "Newsletter e SMS");

        List<Mensagem> msgs = new ArrayList<Mensagem>();

        for (int i = 0; i < 10; i++) {
            msgs.add(new Mensagem(i, new Date(), "Texto Texto Texto Texto "+ (i+1), ((i % 2 == 0) ? SendType.NEWSLETTER : SendType.SMS), funcionarioRepository.find(1)));
        }

        return msgs;
    }

    @Get("/mensagem/refresh")
    public List<Mensagem> refresh() {
        return list();
    }

    @Get("/mensagem/view/{id}")
    public Mensagem view(int id) {
        result.include("title", "Informações da Mensagem");
        return list().get(id);
    }

    @Get("/mensagem/add")
    public void frmAdd() {
        result.include("title", "Enviar Mensagem");
        result.include("types", SendType.getAll());
    }
}