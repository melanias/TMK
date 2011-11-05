package br.org.aappe.erp.controller;

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
import br.org.aappe.erp.bean.Funcionario;
import br.org.aappe.erp.enums.Role;
import br.org.aappe.erp.enums.Status;
import br.org.aappe.erp.repository.FilialRepository;
import br.org.aappe.erp.repository.FuncionarioRepository;
import br.org.aappe.erp.repository.SetorRepository;
import br.org.aappe.erp.util.Utilities;

/**
 * @author Phelipe Melanias
 */
@Resource
public class FuncionarioController extends MainController {
    private final SetorRepository setorRepository;
    private final FuncionarioRepository repository;
    private final FilialRepository filialRepository;

    public FuncionarioController(Result result, Validator validator, SetorRepository setorRepository,
                                 FuncionarioRepository repository, FilialRepository filialRepository)
    {
        super(result, validator);

        this.repository = repository;
        this.setorRepository = setorRepository;
        this.filialRepository = filialRepository;
    }

    @Get("/funcionario")
    public List<Funcionario> list() {
        result.include("title", "Funcionários");
        return repository.listAllById();
    }

    @Get("/funcionario/refresh")
    public List<Funcionario> refresh() {
        return list();
    }

    @Get("/funcionario/view/{id}")
    public Funcionario view(int id) {
        result.include("title", "Informações do Funcionário");
        return repository.find(id);
    }

    @Get("/funcionario/add")
    public void frmAdd() {
        result.include("title", "Cadastrar Funcionário");
        result.include("roles", Role.getAll());
        result.include("status", Status.getAll());
        result.include("setores", setorRepository.listAllById());
        result.include("filiais", filialRepository.listAllById());
    }

    @Transactional
    @Post("/funcionario/add")
    public void add(final Funcionario funcionario) {
        List<Message> errors = new Validations(){{
            //Nome
            if (that(!funcionario.getNome().isEmpty(), "funcionario.nome", "nome"))
                that(repository.isUniqueName(funcionario), "funcionario.nome", "nome.unico");

            //Senha
            if (that(!funcionario.getSenha().isEmpty(), "funcionario.senha", "senha"))
                that(funcionario.getSenha().length() > 5, "funcionario.senha", "senha.invalida");

            //RG
            if (that(funcionario.getRg() != null, "funcionario.rg", "rg") &&
                that(funcionario.getRg().toString().length() > 5 && funcionario.getRg().toString().length() < 14, "funcionario.rg", "rg.invalido", 6, 13))
                that(repository.isUniqueRg(funcionario), "funcionario.rg", "rg.unico");

            //CPF
            if (that(!funcionario.getCpf().isEmpty(), "funcionario.cpf", "cpf") &&
                that(Utilities.cpf(funcionario.getCpf()), "funcionario.cpf", "cpf.invalido"))
                that(repository.isUniqueCpf(funcionario), "funcionario.cpf", "cpf.unico");

            //E-mail
            /*if (that(!funcionario.getEmail().isEmpty(), "funcionario.email", "email") &&
                that(Utilities.mail(funcionario.getEmail()), "funcionario.email", "email.invalido"))
                that(repository.isUniqueMail(funcionario), "funcionario.email", "email.unico");*/

            //Telefone ou Celular
            that(!funcionario.getCelular().isEmpty() || !funcionario.getTelefone().isEmpty(), "", "telefone.ou.celular");

            //Data de admissão
            that(funcionario.getAdmissao() != null, "funcionario.admissao", "admissao");

            //Endereço
            if (that(!funcionario.getEndereco().getCep().isEmpty(), "funcionario.cep", "cep"))
                that(!funcionario.getEndereco().getLogradouro().isEmpty() && !funcionario.getEndereco().getBairro().isEmpty() &&
                     !funcionario.getEndereco().getUf().isEmpty() && !funcionario.getEndereco().getCidade().isEmpty(), "", "address_is_not_complete");
        }}.getErrors();
        validator.addAll(errors);
        validator.onErrorUse(json()).withoutRoot().from(errors).exclude("category").serialize();

        //Criptografar a senha
        funcionario.setSenha(Utilities.md5(funcionario.getLogin()+funcionario.getSenha()));

        repository.persist(funcionario);
        result.use(json()).withoutRoot().from("OK").serialize();
    }

    @Get("/funcionario/edit/{id}")
    public Funcionario frmEdit(int id) {
        result.include("title", "Editar Funcionário");
        result.include("roles", Role.getAll());
        result.include("status", Status.getAll());
        result.include("setores", setorRepository.listAllById());
        result.include("filiais", filialRepository.listAllById());
        return repository.find(id);
    }

    @Transactional
    @Post("/funcionario/edit")
    public void edit(final Funcionario funcionario) {
        List<Message> errors = new Validations(){{
            //Nome
            if (that(!funcionario.getNome().isEmpty(), "funcionario.nome", "nome"))
                that(repository.isUniqueName(funcionario), "funcionario.nome", "nome.unico");

            //Senha
            if (!funcionario.getSenha().isEmpty() && that(funcionario.getSenha().length() > 5, "funcionario.senha", "senha.invalida")) {
                funcionario.setSenha(Utilities.md5(funcionario.getLogin()+funcionario.getSenha()));
            } else {
                funcionario.setSenha(repository.find(funcionario.getId()).getSenha());
            }

            //RG
            if (that(funcionario.getRg() != null, "funcionario.rg", "rg") &&
                that(funcionario.getRg().toString().length() > 5 && funcionario.getRg().toString().length() < 14, "funcionario.rg", "rg.invalido", 6, 13))
                that(repository.isUniqueRg(funcionario), "funcionario.rg", "rg.unico");

            //CPF
            if (that(!funcionario.getCpf().isEmpty(), "funcionario.cpf", "cpf") &&
                that(Utilities.cpf(funcionario.getCpf()), "funcionario.cpf", "cpf.invalido"))
                that(repository.isUniqueCpf(funcionario), "funcionario.cpf", "cpf.unico");

            //E-mail
            /*if (that(!funcionario.getEmail().isEmpty(), "funcionario.email", "email") &&
                that(Utilities.mail(funcionario.getEmail()), "funcionario.email", "email.invalido"))
                that(repository.isUniqueMail(funcionario), "funcionario.email", "email.unico");*/

            //Telefone ou Celular
            that(!funcionario.getCelular().isEmpty() || !funcionario.getTelefone().isEmpty(), "", "telefone.ou.celular");

            //Data de admissão
            that(funcionario.getAdmissao() != null, "funcionario.admissao", "admissao");

            //Endereço
            if (that(!funcionario.getEndereco().getCep().isEmpty(), "funcionario.cep", "cep"))
                that(!funcionario.getEndereco().getLogradouro().isEmpty() && !funcionario.getEndereco().getBairro().isEmpty() &&
                     !funcionario.getEndereco().getUf().isEmpty() && !funcionario.getEndereco().getCidade().isEmpty(), "", "address_is_not_complete");
        }}.getErrors();
        validator.addAll(errors);
        validator.onErrorUse(json()).withoutRoot().from(errors).exclude("category").serialize();

        repository.merge(funcionario);
        result.use(json()).withoutRoot().from("OK").serialize();
    }

    @Get("/setup")
    public void setup() {
        if (repository.hasEmployee())
            result.redirectTo(LoginController.class).frmLogin();

        result.include("title", "Cadastrar Responsável");
    }

    @Transactional
    @Post("/setup")
    public void setup(final Funcionario funcionario) {
        validator.checking(new Validations(){{
            //Nome
            if (that(!funcionario.getNome().isEmpty(), "funcionario.nome", "nome"))
                that(repository.isUniqueName(funcionario), "funcionario.nome", "nome.unico");

            //RG
            if (that(funcionario.getRg() != null, "funcionario.rg", "rg") &&
                that(funcionario.getRg().toString().length() > 5 && funcionario.getRg().toString().length() < 20, "funcionario.rg", "rg.invalido", 6, 20))
                that(repository.isUniqueRg(funcionario), "funcionario.rg", "rg.unico");

            //CPF
            if (that(!funcionario.getCpf().isEmpty(), "funcionario.cpf", "cpf") &&
                that(Utilities.cpf(funcionario.getCpf()), "funcionario.cpf", "cpf.invalido"))
                that(repository.isUniqueCpf(funcionario), "funcionario.cpf", "cpf.unico");

            //E-mail
            /*if (that(!funcionario.getEmail().isEmpty(), "funcionario.email", "email") &&
                that(Utilities.mail(funcionario.getEmail()), "funcionario.email", "email.invalido"))
                that(repository.isUniqueMail(funcionario), "funcionario.email", "email.unico");*/

            //Data de admissão
            that(funcionario.getAdmissao() != null, "funcionario.admissao", "admissao");

            //Telefone ou Celular
            that(!funcionario.getCelular().isEmpty() || !funcionario.getTelefone().isEmpty(), "", "telefone.ou.celular");

            //Login
            if (that(!funcionario.getLogin().isEmpty(), "funcionario.login", "login"))
                that(repository.isUniqueLogin(funcionario), "funcionario.login", "login.unico");

            //Senha
            if (that(!funcionario.getSenha().isEmpty(), "funcionario.senha", "senha"))
                that(funcionario.getSenha().length() > 5, "funcionario.senha", "senha.invalida");

            //Endereço
            if (that(!funcionario.getEndereco().getCep().isEmpty(), "funcionario.cep", "cep"))
                that(!funcionario.getEndereco().getLogradouro().isEmpty() && !funcionario.getEndereco().getBairro().isEmpty() &&
                     !funcionario.getEndereco().getUf().isEmpty() && !funcionario.getEndereco().getCidade().isEmpty(), "", "address_is_not_complete");
        }});
        validator.onErrorForwardTo(this).setup();

        //Criptografar a senha
        funcionario.setSenha(Utilities.md5(funcionario.getLogin()+funcionario.getSenha()));

        //Salvar os dados
        repository.persist(funcionario);
        result.redirectTo(LoginController.class).frmLogin();
    }
}