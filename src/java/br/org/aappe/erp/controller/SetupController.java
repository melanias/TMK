package br.org.aappe.erp.controller;

import java.util.ArrayList;
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
import br.org.aappe.erp.bean.Funcionario;
import br.org.aappe.erp.bean.Unidade;
import br.org.aappe.erp.repository.FuncionarioRepository;
import br.org.aappe.erp.repository.UnidadeRepository;
import br.org.aappe.erp.util.Utilities;

/**
 * @author Phelipe Melanias
 */
@Resource
public class SetupController extends MainController {
    private final UnidadeRepository unidadeRepository;
    private final FuncionarioRepository funcionarioRepository;

    public SetupController(Result result, Validator validator, UnidadeRepository unidadeRepository, FuncionarioRepository funcionarioRepository) {
        super(result, validator);

        this.unidadeRepository = unidadeRepository;
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
    public void setup(final Unidade unidade, final Funcionario funcionario) {
        List<Message> errors = new Validations(){{
            //Unidade
            if (that(!unidade.getCnpj().isEmpty(), "cnpj", "cnpj"))
                that(Utilities.cnpj(unidade.getCnpj()), "cnpj", "cnpj.invalido");

            that(!unidade.getRazaoSocial().isEmpty(), "razaoSocial", "razaoSocial");

            if (!unidade.getEmail().isEmpty())
                that(Utilities.mail(unidade.getEmail()), "email", "setup.email.invalido");

            //Administrador
            if (that(!funcionario.getNome().isEmpty(), "nome", "setup.nome"))
                that(funcionario.getNome().length() > 2, "nome", "setup.nome.invalido");

            if (funcionario.getRg() == null && funcionario.getCpf().isEmpty()) {
                that(false, "rg", "setup.rg.ou.cpf");
                that(false, "cpf", "setup.rg.ou.cpf");
            } else {
                //RG
                if (funcionario.getRg() != null)
                    that(funcionario.getRg().toString().length() > 5 && funcionario.getRg().toString().length() < 20, "rg", "rg.invalido", 6, 20);

                //CPF
                if (!funcionario.getCpf().isEmpty())
                    that(Utilities.cpf(funcionario.getCpf()), "cpf", "cpf.invalido");
            }

            if (!funcionario.getEmail().isEmpty())
                that(Utilities.mail(funcionario.getEmail()), "mail", "setup.email.invalido");

            if (funcionario.getTelefone().isEmpty() && funcionario.getCelular().isEmpty()) {
                that(false, "phone", "setup.fone.ou.celular");
                that(false, "cellphone", "setup.fone.ou.celular");
            }

            that(!funcionario.getLogin().isEmpty(), "login", "setup.login");

            if (that(!funcionario.getSenha().isEmpty(), "senha", "setup.senha"))
                that(funcionario.getSenha().length() > 5, "senha", "setup.senha.invalida");
        }}.getErrors();
        validator.addAll(errors);
        validator.onErrorUse(json()).withoutRoot().from(errors).serialize();

        //Definir data de cadastro da Unidade
        unidade.setData(new Date());

        //Criptografar a senha
        funcionario.setSenha(Utilities.md5(funcionario.getLogin()+funcionario.getSenha()));

        //Definir relacionamento do funcionário com a Unidade
        funcionario.setUnidade(unidade);

        unidade.setFuncionarios(new ArrayList<Funcionario>());
        unidade.getFuncionarios().add(funcionario);

        //Salvar os dados
        unidadeRepository.persist(unidade);

        result.use(json()).withoutRoot().from("OK").serialize();
    }
}