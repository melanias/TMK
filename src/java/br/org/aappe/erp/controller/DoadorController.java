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

import br.org.aappe.erp.annotations.Authorized;
import br.org.aappe.erp.annotations.Transactional;
import br.org.aappe.erp.bean.Doador;
import br.org.aappe.erp.enums.DonorStatus;
import br.org.aappe.erp.enums.DonorType;
import br.org.aappe.erp.enums.Role;
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
    @Authorized({Role.GERENTE, Role.OPERADOR, Role.ESTAGIARIO})
    public List<Doador> list() {
        result.include("title", "Doadores");
        return repository.listAllById();
    }

    @Get("/doador/refresh")
    @Authorized({Role.GERENTE, Role.OPERADOR, Role.ESTAGIARIO})
    public List<Doador> refresh() {
        return list();
    }

    @Get("/doador/view/{id}")
    @Authorized({Role.GERENTE, Role.OPERADOR, Role.ESTAGIARIO})
    public Doador view(int id) {
        result.include("title", "Informações do Doador");
        return repository.find(id);
    }

    @Get("/doador/search")
    @Authorized({Role.GERENTE, Role.OPERADOR, Role.ESTAGIARIO})
    public void search(String term) {
        String nome = ((term == null) ? "" : Utilities.decodeText(term.trim()));

        result.use(json()).withoutRoot().from(repository.search(nome)).exclude("data").serialize();
    }

    @Get("/doador/add")
    @Authorized({Role.GERENTE, Role.OPERADOR})
    public void frmAdd() {
        result.include("title", "Cadastrar Doador");
        result.include("types", DonorType.getAll());
        result.include("status", DonorStatus.getAll());
    }

    @Transactional
    @Post("/doador/add")
    @Authorized({Role.GERENTE, Role.OPERADOR})
    public void add(final Doador doador) {
        List<Message> errors = validate(doador);

        validator.addAll(errors);
        validator.onErrorUse(json()).withoutRoot().from(errors).exclude("category").serialize();

        //Definir data de cadastro do doador
        doador.setData(new Date());

        repository.persist(doador);
        result.use(json()).withoutRoot().from("OK").serialize();
    }

    @Get("/doador/edit/{id}")
    @Authorized({Role.GERENTE, Role.OPERADOR})
    public Doador frmEdit(int id) {
        result.include("title", "Editar Doador");
        result.include("types", DonorType.getAll());
        result.include("status", DonorStatus.getAll());
        return repository.find(id);
    }

    @Transactional
    @Post("/doador/edit")
    @Authorized({Role.GERENTE, Role.OPERADOR})
    public void edit(final Doador doador) {
        List<Message> errors = validate(doador);

        validator.addAll(errors);
        validator.onErrorUse(json()).withoutRoot().from(errors).exclude("category").serialize();

        repository.merge(doador);
        result.use(json()).withoutRoot().from("OK").serialize();
    }

    private List<Message> validate(final Doador doador) {
        return new Validations(){{
            //Nome
            if (that(!doador.getNome().isEmpty(), "doador.nome", "nome"))
                that(repository.isUniqueName(doador), "doador.nome", "nome.unico");

            //RG
            if (doador.getRg() != null && that(doador.getRg().toString().length() > 5 && doador.getRg().toString().length() < 14, "doador.rg", "rg.invalido", 6, 13))
                that(repository.isUniqueRg(doador), "doador.rg", "rg.unico");

            //CPF
            if (doador.getCpf() != null && !doador.getCpf().isEmpty() && that(Utilities.cpf(doador.getCpf()), "doador.cpf", "cpf.invalido"))
                that(repository.isUniqueCpf(doador), "doador.cpf", "cpf.unico");

            //CNPJ
            if (doador.getCnpj() != null && !doador.getCnpj().isEmpty() && that(Utilities.cnpj(doador.getCnpj()), "empresa.cnpj", "cnpj.invalido"))
                that(repository.isUniqueCnpj(doador), "empresa.cnpj", "cnpj.unico");

            //E-mail
            if (!doador.getEmail().isEmpty() && that(Utilities.mail(doador.getEmail()), "doador.email", "email.invalido"))
                that(repository.isUniqueMail(doador), "doador.email", "email.unico");

            //Telefone ou Celular
            that(!doador.getCelular().isEmpty() || !doador.getTelefone().isEmpty(), "", "telefone.ou.celular");

            //Endereço
            that(!doador.getEndereco().getLogradouro().isEmpty() && !doador.getEndereco().getBairro().isEmpty() &&
                 !doador.getEndereco().getCidade().isEmpty() && !doador.getEndereco().getUf().isEmpty(), "", "incomplete_address");
        }}.getErrors();
    }
}