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
import br.org.aappe.erp.bean.User;
import br.org.aappe.erp.repository.UserRepository;
import br.org.aappe.erp.util.Utilities;

/**
 * @author Phelipe Melanias
 */
@Resource
public class UserController extends MainController {
    private final UserRepository repository;

    public UserController(Result result, Validator validator, UserRepository repository) {
        super(result, validator);
        this.repository = repository;
    }

    @Get("/admin/user")
    public List<User> list() {
        result.include("title", "Lista de Administradores");
        return repository.listAllById();
    }

    @Get("/admin/user/novo")
    public void frmAdd() {
        result.include("title", "Cadastrar Administrador");
    }

    @Transactional
    @Post("/admin/user/novo")
    public void add(final User user) {
        validator.checking(new Validations(){{
            //CPF
            if (that(!user.getCpf().isEmpty(), "user.cpf", "cpf") &&
                that(Utilities.cpf(user.getCpf()), "user.cpf", "cpf.invalido"))
                that(repository.isUniqueCpf(user), "user.cpf", "cpf.unico");

            //Nome
            if (that(!user.getNome().isEmpty(), "user.nome", "nome") &&
                that(user.getNome().length() > 2, "user.nome", "nome.invalido"))
                that(repository.isUniqueName(user), "user.nome", "nome.unico");

            //E-mail
            if (that(!user.getEmail().isEmpty(), "user.email", "email") &&
                that(Utilities.mail(user.getEmail()), "user.email", "email.invalido"))
                that(repository.isUniqueMail(user), "user.email", "email.unico");

            //Telefone ou celular
            that(!user.getTelefone().isEmpty() || !user.getCelular().isEmpty(), "", "telefone.ou.celular");

            //Login
            if (that(!user.getLogin().isEmpty(), "user.login", "login"))
                that(repository.isUniqueLogin(user), "user.login", "login.unico");

            //Senha
            if (that(!user.getSenha().isEmpty(), "root.senha", "senha"))
                that(user.getSenha().length() > 5, "root.senha", "senha.invalida");
        }});
        validator.onErrorForwardTo(this).frmAdd();

        //Criptografar a senha
        user.setSenha(Utilities.md5(user.getLogin()+user.getSenha()));

        //Persistir os dados
        repository.persist(user);
        result.redirectTo(this).list();
    }

    @Get("/admin/user/{id}")
    public User frmEdit(int id) {
        result.include("title", "Editar Administrador");

        User user = repository.find(id);

        if (user == null)
            result.redirectTo(this).list();

        return user;
    }

    @Transactional
    @Put("/admin/user/{user.id}")
    public void edit(final User user) {
        validator.checking(new Validations(){{
            //CPF
            if (that(!user.getCpf().isEmpty(), "user.cpf", "cpf") &&
                that(Utilities.cpf(user.getCpf()), "user.cpf", "cpf.invalido"))
                that(repository.isUniqueCpf(user), "user.cpf", "cpf.unico");

            //Nome
            if (that(!user.getNome().isEmpty(), "user.nome", "nome") &&
                that(user.getNome().length() > 2, "user.nome", "nome.invalido"))
                that(repository.isUniqueName(user), "user.nome", "nome.unico");

            //E-mail
            if (that(!user.getEmail().isEmpty(), "user.email", "email") &&
                that(Utilities.mail(user.getEmail()), "user.email", "email.invalido"))
                that(repository.isUniqueMail(user), "user.email", "email.unico");

            //Telefone ou celular
            that(!user.getTelefone().isEmpty() || !user.getCelular().isEmpty(), "", "telefone.ou.celular");

            //Login
            if (that(!user.getLogin().isEmpty(), "user.login", "login"))
                that(repository.isUniqueLogin(user), "user.login", "login.unico");
        }});

        if (validator.hasErrors())
            result.include("title", "Editar Administrador");

        validator.onErrorUsePageOf(this).frmEdit(user.getId());

        //Senha do administrador
        user.setSenha(repository.find(user.getId()).getSenha());

        //Persistir os dados
        repository.merge(user);
        result.redirectTo(this).list();
    }
}