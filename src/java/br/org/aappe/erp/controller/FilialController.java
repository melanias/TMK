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
import br.org.aappe.erp.bean.Filial;
import br.org.aappe.erp.repository.FilialRepository;
import br.org.aappe.erp.util.Utilities;
import java.util.Date;

/**
 * @author Jadson Ronald
 */
@Resource
public class FilialController extends MainController {
    private final FilialRepository repository;

    public FilialController(Result result, Validator validator, FilialRepository repository) {
        super(result, validator);
        this.repository = repository;
    }

    @Get("/filial")
    public List<Filial> list() {
        result.include("title", "Filiais");
        return repository.listAllById();
    }

    @Get("/filial/refresh")
    public List<Filial> refresh() {
        return list();
    }

    @Get("/filial/view/{id}")
    public Filial view(int id) {
        result.include("title", "Informações da Filial");
        return repository.find(id);
    }

    @Get("/filial/add")
    public void frmAdd() {
        result.include("title", "Cadastrar Filial");
    }

    @Transactional
    @Post("/filial/add")
    public void add(final Filial filial) {
        List<Message> errors = new Validations(){{
            //Dados da filial
            if (that(!filial.getCnpj().isEmpty(), "filial.cnpj", "cnpj") &&
                that(Utilities.cnpj(filial.getCnpj()), "filial.cnpj", "cnpj.invalido"))
                that(repository.isUniqueCnpj(filial), "filial.cnpj", "cnpj.unico");

            if (that(!filial.getNome().isEmpty(), "filial.nome", "nome"))
                that(repository.isUniqueCompanyName(filial), "filial.nome", "nome.unico");

            if (!filial.getEmail().isEmpty() &&
                that(Utilities.mail(filial.getEmail()), "filial.email", "email.invalido"))
                that(repository.isUniqueMail(filial), "filial.email", "email.unico");

            that(!filial.getTelefone().isEmpty() , "filial.telefone", "telefone");
        }}.getErrors();
        validator.addAll(errors);
        validator.onErrorUse(json()).withoutRoot().from(errors).exclude("category").serialize();

        //Definir data de cadastro da filial
        filial.setData(new Date());

        repository.persist(filial);
        result.use(json()).withoutRoot().from("OK").serialize();
    }

    @Get("/filial/edit/{id}")
    public Filial frmEdit(int id) {
        result.include("title", "Editar Filial");
        return repository.find(id);
    }

    @Transactional
    @Post("/filial/edit")
    public void edit(final Filial filial) {
        List<Message> errors = new Validations(){{
            //Dados da filial
            if (that(!filial.getCnpj().isEmpty(), "filial.cnpj", "cnpj") &&
                that(Utilities.cnpj(filial.getCnpj()), "filial.cnpj", "cnpj.invalido"))
                that(repository.isUniqueCnpj(filial), "filial.cnpj", "cnpj.unico");

            if (that(!filial.getNome().isEmpty(), "filial.nome", "nome"))
                that(repository.isUniqueCompanyName(filial), "filial.nome", "nome.unico");

            if (!filial.getEmail().isEmpty() &&
                that(Utilities.mail(filial.getEmail()), "filial.email", "email.invalido"))
                that(repository.isUniqueMail(filial), "filial.email", "email.unico");

            that(!filial.getTelefone().isEmpty() , "filial.telefone", "telefone");
        }}.getErrors();
        validator.addAll(errors);
        validator.onErrorUse(json()).withoutRoot().from(errors).exclude("category").serialize();

        repository.merge(filial);
        result.use(json()).withoutRoot().from("OK").serialize();
    }
}

/*@Resource
public class EmpresaController extends MainController {
    private final EmpresaRepository repository;

    public EmpresaController(Result result, Validator validator, EmpresaRepository repository) {
        super(result, validator);
        this.repository = repository;
    }

    @Get("/admin/empresa")
    public List<Empresa> list() {
        result.include("title", "Lista de Empresas");
        return repository.listAllById();
    }

    @Get("/admin/empresa/nova")
    public void frmAdd() {
        result.include("title", "Cadastrar Empresa");
        result.include("status", Status.getAll());
    }

    @Transactional
    @Post("/admin/empresa/nova")
    public void add(final Empresa empresa) {
        validator.checking(new Validations(){{
            //Dados da empresa
            if (that(!empresa.getCnpj().isEmpty(), "empresa.cnpj", "cnpj") &&
                that(Utilities.cnpj(empresa.getCnpj()), "empresa.cnpj", "cnpj.invalido"))
                that(repository.isUniqueCnpj(empresa), "empresa.cnpj", "cnpj.unico");

            if (that(!empresa.getRazaoSocial().isEmpty(), "empresa.razaoSocial", "razaoSocial"))
                that(repository.isUniqueCompanyName(empresa), "empresa.razaoSocial", "razaoSocial.unica");

            if (that(!empresa.getEmail().isEmpty(), "empresa.email", "email") &&
                that(Utilities.mail(empresa.getEmail()), "empresa.email", "email.invalido"))
                that(repository.isUniqueMail(empresa), "empresa.email", "email.unico");

            that(!empresa.getTelefone().isEmpty() , "empresa.telefone", "telefone");

            //Endereço da empresa
            if (that(!empresa.getEndereco().getCep().isEmpty(), "empresa.cep", "cep"))
                that(!empresa.getEndereco().getLogradouro().isEmpty() && !empresa.getEndereco().getBairro().isEmpty() &&
                     !empresa.getEndereco().getUf().isEmpty() && !empresa.getEndereco().getCidade().isEmpty(), "", "address_is_not_complete");
        }});
        validator.onErrorForwardTo(this).frmAdd();

        //Definir data de cadastro da empresa
        empresa.setData(new Date());

        //Persistir os dados
        repository.persist(empresa);
        result.redirectTo(this).list();
    }

    @Get("/admin/empresa/{id}")
    public Empresa frmEdit(int id) {
        result.include("title", "Editar Empresa");
        result.include("status", Status.getAll());

        Empresa e = repository.find(id);

        if (e == null)
            result.redirectTo(this).list();

        return e;
    }

    @Transactional
    @Put("/admin/empresa/{empresa.id}")
    public void edit(final Empresa empresa) {
        validator.checking(new Validations(){{
            //Dados da empresa
            if (that(!empresa.getCnpj().isEmpty(), "empresa.cnpj", "cnpj") &&
                that(Utilities.cnpj(empresa.getCnpj()), "empresa.cnpj", "cnpj.invalido"))
                that(repository.isUniqueCnpj(empresa), "empresa.cnpj", "cnpj.unico");

            if (that(!empresa.getRazaoSocial().isEmpty(), "empresa.razaoSocial", "razaoSocial"))
                that(repository.isUniqueCompanyName(empresa), "empresa.razaoSocial", "razaoSocial.unica");

            if (that(!empresa.getEmail().isEmpty(), "empresa.email", "email") &&
                that(Utilities.mail(empresa.getEmail()), "empresa.email", "email.invalido"))
                that(repository.isUniqueMail(empresa), "empresa.email", "email.unico");

            that(!empresa.getTelefone().isEmpty() , "empresa.telefone", "telefone");

            //Endereço da empresa
            if (that(!empresa.getEndereco().getCep().isEmpty(), "empresa.cep", "cep"))
                that(!empresa.getEndereco().getLogradouro().isEmpty() && !empresa.getEndereco().getBairro().isEmpty() &&
                     !empresa.getEndereco().getUf().isEmpty() && !empresa.getEndereco().getCidade().isEmpty(), "", "address_is_not_complete");
        }});

        if (validator.hasErrors()) {
            result.include("title", "Editar Empresa");
            result.include("status", Status.getAll());
        }

        validator.onErrorUsePageOf(this).frmEdit(empresa.getId());

        //Persistir os dados
        repository.merge(empresa);
        result.redirectTo(this).list();
    }
}*/