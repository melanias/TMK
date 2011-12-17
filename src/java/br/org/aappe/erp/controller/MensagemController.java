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
import br.org.aappe.erp.annotations.Transactional;
import static br.com.caelum.vraptor.view.Results.*;
import static org.hamcrest.Matchers.*;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.HtmlEmail;

import br.org.aappe.erp.bean.Mensagem;
import br.org.aappe.erp.enums.SendType;
import br.org.aappe.erp.repository.MensagemRepository;
import br.org.aappe.erp.bean.Smtp;
import br.org.aappe.erp.repository.SmtpRepository;

/**
 * @author Phelipe Melanias
 */
@Resource
public class MensagemController extends MainController {
    private final MensagemRepository repository;
    private final SmtpRepository smtpRepository;

    public MensagemController(Result result, Validator validator, MensagemRepository repository, SmtpRepository smtpRepository) {
        super(result, validator);
        this.repository = repository;
        this.smtpRepository = smtpRepository;
    }

    @Get("/mensagem")
    public List<Mensagem> list() {
        result.include("title", "Newsletter e SMS");

        return repository.listAllById();
    }

    @Get("/mensagem/refresh")
    public List<Mensagem> refresh() {
        return list();
    }

    @Get("/mensagem/view/{id}")
    public Mensagem view(int id) {
        result.include("title", "Informações da Mensagem");
        return repository.find(id);
    }

    @Get("/mensagem/add")
    public void frmAdd() {
        result.include("title", "Enviar Mensagem");
        result.include("types", SendType.getAll());
    }

    @Transactional
    @Post("/mensagem/add")
    public void add(Mensagem mensagem) {
        List<Message> errors = validate(mensagem);

        validator.addAll(errors);
        validator.onErrorUse(json()).withoutRoot().from(errors).exclude("category").serialize();

        //Servidor SMTP
        //Smtp smtp = smtpRepository.getActiveServer();

        //TODO: o código abaixo está funcionado, mas está faltando
        //      definir o layout do newsletter.
        /*try {
            //TODO: Melhorar essa exceção, não permitindo o envio de mensagens
            //      sem um servidor configurado ou ativo.
            if (smtp == null)
                throw new Exception("Nenhum servidor SMTP ativo ou configurado.");

            Email email = new HtmlEmail();
            email.setHostName(smtp.getHostName());
            email.setSmtpPort(smtp.getPort());
            email.setAuthenticator(new DefaultAuthenticator(smtp.getAccount(), smtp.getPassword()));
            email.setTLS(smtp.isTls());
            email.setFrom("de");
            email.setSubject("AAPPE - Newsletter");//Acho que isso deve vim no formulário do mensagem!
            email.setMsg(mensagem.getTexto());
            email.addBcc("para");//BCC Cópia Oculta!
            email.send();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/

        //Definir data de envio da mensagem
        mensagem.setData(new Date());

        repository.persist(mensagem);
        result.use(json()).withoutRoot().from("OK").serialize();
    }

    private List<Message> validate(final Mensagem mensagem) {
        return new Validations(){{
            //Tipo de envio
            that(mensagem.getTipo(), is(notNullValue()), "mensagem.tipo", "mensagem.tipo");

            //Texto
            that(!mensagem.getTexto().isEmpty(), "mensagem.texto", "mensagem.texto");
        }}.getErrors();
    }
}