package br.org.aappe.erp.controller;

import java.util.Date;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Message;
import br.com.caelum.vraptor.validator.Validations;
import static br.com.caelum.vraptor.view.Results.*;

import br.org.aappe.erp.annotations.Transactional;
import br.org.aappe.erp.bean.Doador;
import br.org.aappe.erp.enums.DonorStatus;
import br.org.aappe.erp.repository.DoadorRepository;
import br.org.aappe.erp.util.Utilities;

/**
 * @author Phelipe Melanias
 */
@Resource
public class DoadorController extends MainController {
    private final DoadorRepository repository;

    public DoadorController(Result result, Validator validator, DoadorRepository repository) {
        super(result, validator);

        this.repository = repository;
    }

    @Get("/doador")
    public List<Doador> list() {
        result.include("title", "Doadores");
        return repository.listAllById();
    }

    @Get("/doador/refresh")
    public List<Doador> refresh() {
        return list();
    }

    @Get("/doador/view/{id}")
    public Doador view(int id) {
        result.include("title", "Informações do Doador");
        return repository.find(id);
    }

    @Get("/doador/add")
    public void frmAdd() {
        result.include("title", "Cadastrar Doador");
        result.include("status", DonorStatus.getAll());
    }

    @Transactional
    @Post("/doador/add")
    public void add(final Doador doador) {
        List<Message> errors = new Validations(){{
            //Nome
            if (that(!doador.getNome().isEmpty(), "doador.nome", "nome"))
                that(repository.isUniqueName(doador), "doador.nome", "nome.unico");

            //RG
            if (that(doador.getRg() != null, "doador.rg", "rg") &&
                that(doador.getRg().toString().length() > 5 && doador.getRg().toString().length() < 12, "doador.rg", "rg.invalido", 6, 11))
                that(repository.isUniqueRg(doador), "doador.rg", "rg.unico");

            //CPF
            if (that(!doador.getCpf().isEmpty(), "doador.cpf", "cpf") &&
                that(Utilities.cpf(doador.getCpf()), "doador.cpf", "cpf.invalido"))
                that(repository.isUniqueCpf(doador), "doador.cpf", "cpf.unico");

            //E-mail
            if (that(!doador.getEmail().isEmpty(), "doador.email", "email") &&
                that(Utilities.mail(doador.getEmail()), "doador.email", "email.invalido"))
                that(repository.isUniqueMail(doador), "doador.email", "email.unico");

            //Telefone ou Celular
            that(!doador.getCelular().isEmpty() || !doador.getTelefone().isEmpty(), "", "telefone.ou.celular");

            //Endereço
            if (that(!doador.getEndereco().getCep().isEmpty(), "doador.cep", "cep"))
                that(!doador.getEndereco().getLogradouro().isEmpty() && !doador.getEndereco().getBairro().isEmpty() &&
                     !doador.getEndereco().getUf().isEmpty() && !doador.getEndereco().getCidade().isEmpty(), "", "address_is_not_complete");
        }}.getErrors();
        validator.addAll(errors);
        validator.onErrorUse(json()).withoutRoot().from(errors).exclude("category").serialize();

        //Definir data de cadastro da empresa
        doador.setData(new Date());

        repository.persist(doador);
        result.use(json()).withoutRoot().from("OK").serialize();
    }

    @Get("/doador/edit/{id}")
    public Doador frmEdit(int id) {
        result.include("title", "Editar Funcionário");
        result.include("status", DonorStatus.getAll());
        return repository.find(id);
    }

    @Transactional
    @Post("/doador/edit")
    public void edit(final Doador doador) {
        List<Message> errors = new Validations(){{
            //Nome
            if (that(!doador.getNome().isEmpty(), "doador.nome", "nome"))
                that(repository.isUniqueName(doador), "doador.nome", "nome.unico");

            //RG
            if (that(doador.getRg() != null, "doador.rg", "rg") &&
                that(doador.getRg().toString().length() > 5 && doador.getRg().toString().length() < 12, "doador.rg", "rg.invalido", 6, 11))
                that(repository.isUniqueRg(doador), "doador.rg", "rg.unico");

            //CPF
            if (that(!doador.getCpf().isEmpty(), "doador.cpf", "cpf") &&
                that(Utilities.cpf(doador.getCpf()), "doador.cpf", "cpf.invalido"))
                that(repository.isUniqueCpf(doador), "doador.cpf", "cpf.unico");

            //E-mail
            if (that(!doador.getEmail().isEmpty(), "doador.email", "email") &&
                that(Utilities.mail(doador.getEmail()), "doador.email", "email.invalido"))
                that(repository.isUniqueMail(doador), "doador.email", "email.unico");

            //Telefone ou Celular
            that(!doador.getCelular().isEmpty() || !doador.getTelefone().isEmpty(), "", "telefone.ou.celular");

            //Endereço
            if (that(!doador.getEndereco().getCep().isEmpty(), "doador.cep", "cep"))
                that(!doador.getEndereco().getLogradouro().isEmpty() && !doador.getEndereco().getBairro().isEmpty() &&
                     !doador.getEndereco().getUf().isEmpty() && !doador.getEndereco().getCidade().isEmpty(), "", "address_is_not_complete");
        }}.getErrors();
        validator.addAll(errors);
        validator.onErrorUse(json()).withoutRoot().from(errors).exclude("category").serialize();

        repository.merge(doador);
        result.use(json()).withoutRoot().from("OK").serialize();
    }
}