package br.org.aappe.erp.controller;

import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Message;
import br.com.caelum.vraptor.validator.Validations;
import static br.com.caelum.vraptor.view.Results.*;

import br.org.aappe.erp.annotations.Transactional;
import br.org.aappe.erp.repository.SmtpRepository;
import br.org.aappe.erp.bean.Smtp;
import br.org.aappe.erp.enums.Status;

/**
 * @author Thales Imbruglia
 */
@Resource @Path("/admin")
public class SmtpController extends MainController {
    private final SmtpRepository repository;

    public SmtpController(Result result, Validator validator, SmtpRepository repository) {
        super(result, validator);
        this.repository = repository;
    }

    @Get("/smtp")
    public List<Smtp> list() {
        result.include("title", "Servidores SMTP");
        return repository.listAllById();
    }

    @Get("/smtp/refresh")
    public List<Smtp> refresh() {
        return list();
    }

    @Get("/smtp/view/{id}")
    public Smtp view(int id) {
        result.include("title", "Informações do Servidor");
        return repository.find(id);
    }

    @Get("/smtp/add")
    public void frmAdd() {
        result.include("title", "Cadastrar Servidor");
        result.include("status", Status.getAll());
    }

    @Transactional
    @Post("/smtp/add")
    public void add(Smtp smtp) {
        List<Message> errors = validate(smtp);

        validator.addAll(errors);
        validator.onErrorUse(json()).withoutRoot().from(errors).exclude("category").serialize();

        repository.persist(smtp);
        result.use(json()).withoutRoot().from("OK").serialize();

    }

    @Get("/smtp/edit/{id}")
    public Smtp frmEdit(int id) {
        result.include("title", "Editar Servidor");
        result.include("status", Status.getAll());
        return repository.find(id);
    }

    @Transactional
    @Post("/smtp/edit")
    public void edit(final Smtp smtp) {
        List<Message> errors = validate(smtp);

        validator.addAll(errors);
        validator.onErrorUse(json()).withoutRoot().from(errors).exclude("category").serialize();

        //Setar a senha
        if (smtp.getPassword().isEmpty())
            smtp.setPassword(repository.find(smtp.getId()).getPassword());

        repository.merge(smtp);
        result.use(json()).withoutRoot().from("OK").serialize();
    }

    private List<Message> validate(final Smtp smtp) {
        return new Validations() {{
            //Hostname
            that(!smtp.getHostName().isEmpty(), "smtp.hostName", "smtpHostName");

            //Porta
            that(smtp.getPort() > 0, "smtp.port", "smtpPort");

            //Conta
            that(!smtp.getAccount().isEmpty(), "smtp.account", "smtpAccount");

            //Senha
            if (!(smtp.getId() > 0))
                that(!smtp.getPassword().isEmpty(), "smtp.password", "smtpPassword");

            //Situação
            if (smtp.getStatus().equals(Status.ATIVO))
                that(repository.isUniqueServerActive(smtp), "smtp.status", "smtpStatus");
        }}.getErrors();
    }
}