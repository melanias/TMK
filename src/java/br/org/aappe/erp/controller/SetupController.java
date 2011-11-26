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
import br.org.aappe.erp.bean.Empresa;
import br.org.aappe.erp.repository.EmpresaRepository;
import br.org.aappe.erp.repository.FuncionarioRepository;
import br.org.aappe.erp.util.Utilities;

/**
 * @author Phelipe Melanias
 */
@Resource
public class SetupController extends MainController {
    private final EmpresaRepository empresaRepository;
    private final FuncionarioRepository funcionarioRepository;

    public SetupController(Result result, Validator validator, EmpresaRepository empresaRepository, FuncionarioRepository funcionarioRepository) {
        super(result, validator);

        this.empresaRepository = empresaRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Get("/setup")
    public void setup() {
        if (funcionarioRepository.hasEmployee())
            result.redirectTo(LoginController.class).frmLogin();

        result.include("title", "Configuração inicial");
    }

    @Transactional
    @Post("/setup")
    public void setup(final Empresa empresa) {
        List<Message> errors = new Validations(){{
            //Empresa
            if (that(!empresa.getCnpj().isEmpty(), "cnpj", "cnpj"))
                that(Utilities.cnpj(empresa.getCnpj()), "cnpj", "cnpj.invalido");

            that(!empresa.getRazaoSocial().isEmpty(), "razaoSocial", "razaoSocial");

            if (that(!empresa.getEmail().isEmpty(), "email", "email"))
                that(Utilities.mail(empresa.getEmail()), "email", "email.invalido");

            that(!empresa.getTelefone().isEmpty() , "telefone", "telefone");

            //Funcionário responsável (Gerente)
            if (that(!empresa.getFuncionarios().get(0).getNome().isEmpty(), "nome", "setup.nome"))
                that(empresa.getFuncionarios().get(0).getNome().length() > 2, "nome", "setup.nome.invalido");

            if (that(empresa.getFuncionarios().get(0).getRg() != null, "rg", "setup.rg"))
                that(empresa.getFuncionarios().get(0).getRg().toString().length() > 5 &&
                     empresa.getFuncionarios().get(0).getRg().toString().length() < 20, "rg", "rg.invalido", 6, 20);

            if (that(!empresa.getFuncionarios().get(0).getCpf().isEmpty(), "cpf", "setup.cpf"))
                that(Utilities.cpf(empresa.getFuncionarios().get(0).getCpf()), "cpf", "cpf.invalido");

            if (!empresa.getFuncionarios().get(0).getEmail().isEmpty())
                that(Utilities.mail(empresa.getFuncionarios().get(0).getEmail()), "mail", "setup.email.invalido");

            that(empresa.getFuncionarios().get(0).getAdmissao() != null, "dataAdmissao", "setup.admissao");

            if (empresa.getFuncionarios().get(0).getTelefone().isEmpty() && empresa.getFuncionarios().get(0).getCelular().isEmpty()) {
                that(false, "phone", "setup.telefone.ou.celular");
                that(false, "cellphone", "setup.telefone.ou.celular");
            }

            that(!empresa.getFuncionarios().get(0).getLogin().isEmpty(), "login", "setup.login");

            if (that(!empresa.getFuncionarios().get(0).getSenha().isEmpty(), "senha", "setup.senha"))
                that(empresa.getFuncionarios().get(0).getSenha().length() > 5, "senha", "setup.senha.invalida");
        }}.getErrors();
        validator.addAll(errors);
        validator.onErrorUse(json()).withoutRoot().from(errors).serialize();

        //Definir data de cadastro da empresa
        empresa.setData(new Date());

        //Criptografar a senha
        empresa.getFuncionarios().get(0).setSenha(Utilities.md5(empresa.getFuncionarios().get(0).getLogin()+empresa.getFuncionarios().get(0).getSenha()));

        //Definir relacionamento do responsável com a empresa
        empresa.getFuncionarios().get(0).setEmpresa(empresa);

        //Salvar os dados
        empresaRepository.persist(empresa);

        result.use(json()).withoutRoot().from("OK").serialize();
    }
}