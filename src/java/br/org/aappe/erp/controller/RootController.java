package br.org.aappe.erp.controller;

import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;

import br.org.aappe.erp.annotations.Transactional;
import br.org.aappe.erp.bean.Root;
import br.org.aappe.erp.repository.RootRepository;
import br.org.aappe.erp.util.Utilities;

/**
 * @author Phelipe Melanias
 */
@Resource
public class RootController extends MainController {
    private final RootRepository repository;

    public RootController(Result result, Validator validator, RootRepository repository) {
        super(result, validator);
        this.repository = repository;
    }

    @Get("/admin/root")
    public List<Root> list() {
        result.include("title", "Lista de Administradores");
        return repository.listAllById();
    }

    @Get("/admin/root/novo")
    public void frmAdd() {
        result.include("title", "Cadastrar Administrador");
    }

    @Transactional
    @Post("/admin/root/novo")
    public void add(final Root root) {
        validator.checking(new Validations(){{
            //CPF
            if (that(!root.getCpf().isEmpty(), "root.cpf", "cpf"))
                that(Utilities.cpf(root.getCpf()), "root.cpf", "cpf.invalido");

            //Nome
            if (that(!root.getNome().isEmpty(), "root.nome", "nome"))
                that(root.getNome().length() > 2, "root.nome", "nome.invalido");

            //E-mail
            if (that(!root.getEmail().isEmpty(), "root.email", "email"))
                that(Utilities.mail(root.getEmail()), "root.email", "email.invalido");

            //Telefone ou celular
            that(!root.getTelefone().isEmpty() || !root.getCelular().isEmpty(), "", "telefone.ou.celular");

            //Senha
            if (that(!root.getSenha().isEmpty(), "root.senha", "senha"))
                that(root.getSenha().length() > 5, "root.senha", "senha.invalida");
        }});
        validator.onErrorForwardTo(this).frmAdd();

        //Criptografar a senha
        root.setSenha(Utilities.md5(root.getCpf()+root.getNome()+root.getSenha()));

        //Persistir os dados
        repository.persist(root);
        result.redirectTo(this).list();
    }

    @Get("/admin/root/{id}")
    public Root frmEdit(int id) {
        result.include("title", "Editar Administrador");

        Root root = repository.find(id);

        if (root == null)
            result.redirectTo(this).list();

        return root;
    }

    @Transactional
    @Put("/admin/root/{root.id}")
    public void edit(final Root root) {
        validator.checking(new Validations(){{
            //CPF
            if (that(!root.getCpf().isEmpty(), "root.cpf", "cpf"))
                that(Utilities.cpf(root.getCpf()), "root.cpf", "cpf.invalido");

            //Nome
            if (that(!root.getNome().isEmpty(), "root.nome", "nome"))
                that(root.getNome().length() > 2, "root.nome", "nome.invalido");

            //E-mail
            if (that(!root.getEmail().isEmpty(), "root.email", "email"))
                that(Utilities.mail(root.getEmail()), "root.email", "email.invalido");

            //Telefone ou celular
            that(!root.getTelefone().isEmpty() || !root.getCelular().isEmpty(), "", "telefone.ou.celular");
        }});

        if (validator.hasErrors())
            result.include("title", "Editar Administrador");

        validator.onErrorUsePageOf(this).frmEdit(root.getId());

        //Senha do administrador
        root.setSenha(repository.find(root.getId()).getSenha());

        //Persistir os dados
        repository.merge(root);
        result.redirectTo(this).list();
    }
}