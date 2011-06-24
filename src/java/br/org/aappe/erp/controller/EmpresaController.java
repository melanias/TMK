package br.org.aappe.erp.controller;

import java.util.Date;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;

import br.org.aappe.erp.annotations.Transactional;
import br.org.aappe.erp.bean.Empresa;
import br.org.aappe.erp.enums.Status;
import br.org.aappe.erp.repository.EmpresaRepository;
import br.org.aappe.erp.util.Utilities;

/**
 * @author Phelipe Melanias
 */
@Resource
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
}