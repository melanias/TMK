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
import br.org.aappe.erp.repository.NewsletterRepository;
import br.org.aappe.erp.bean.Newsletter;

/**
 * @author Thales Imbruglia
 */
@Resource @Path("/admin")
public class NewsletterController extends MainController {
    private final NewsletterRepository repository;

    public NewsletterController(Result result, Validator validator, NewsletterRepository repository) {
        super(result, validator);
        this.repository = repository;
    }

    @Get("/newsletter")
    public List<Newsletter> list() {
        result.include("title", "Cadastrar Conta SMTP");
        return repository.listAllById();
    }

    @Get("/newsletter/refresh")
    public List<Newsletter> refresh() {
        return list();
    }

    @Get("/newsletter/view/{id}")
    public Newsletter view(int id) {
        result.include("title", "Informações das Contas");
        return repository.find(id);
    }

    @Get("/newsletter/add")
    public void frmAdd() {
        result.include("title", "Adicionar Conta");
    }

    @Transactional
    @Post("/newsletter/add")
    public void add(Newsletter newsletter) {
        List<Message> errors = validate(newsletter);

        validator.addAll(errors);
        validator.onErrorUse(json()).withoutRoot().from(errors).exclude("category").serialize();

        repository.persist(newsletter);
        result.use(json()).withoutRoot().from("OK").serialize();

    }

    @Get("/newsletter/edit/{id}")
    public Newsletter frmEdit(int id) {
        result.include("title", "Editar Conta");
        return repository.find(id);
    }

    @Transactional
    @Post("/newsletter/edit")
    public void edit(final Newsletter newsletter) {
        List<Message> errors = validate(newsletter);

        validator.addAll(errors);
        validator.onErrorUse(json()).withoutRoot().from(errors).exclude("category").serialize();

        repository.merge(newsletter);
        result.use(json()).withoutRoot().from("OK").serialize();
    }

    private List<Message> validate(final Newsletter newsletter) {
        return new Validations() {{
            that(!newsletter.getHostName().isEmpty(), "newsletter.hostname", "newsletter.hostname");
   
        }}.getErrors();
    }
}