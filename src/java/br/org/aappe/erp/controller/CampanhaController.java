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
import br.org.aappe.erp.bean.Campanha;
import br.org.aappe.erp.repository.CampanhaRepository;

/**
 * @author Phelipe Melanias
 */
@Resource
public class CampanhaController extends MainController {
    private final CampanhaRepository repository;

    public CampanhaController(Result result, Validator validator, CampanhaRepository repository) {
        super(result, validator);

        this.repository = repository;
    }

    @Get("/campanha")
    public List<Campanha> list() {
        result.include("title", "Campanhas");
        return repository.listAllById();
    }

    @Get("/campanha/refresh")
    public List<Campanha> refresh() {
        return list();
    }

    @Get("/campanha/view/{id}")
    public Campanha view(int id) {
        result.include("title", "Informações da Campanha");
        return repository.find(id);
    }

    @Get("/campanha/add")
    public void frmAdd() {
        result.include("title", "Cadastrar Campanha");
    }

    @Transactional
    @Post("/campanha/add")
    public void add(final Campanha campanha) {
        List<Message> errors = validate(campanha);

        validator.addAll(errors);
        validator.onErrorUse(json()).withoutRoot().from(errors).exclude("category").serialize();

        //Definir data de cadastro da campanha
        campanha.setData(new Date());

        repository.persist(campanha);
        result.use(json()).withoutRoot().from("OK").serialize();
    }

    @Get("/campanha/edit/{id}")
    public Campanha frmEdit(int id) {
        result.include("title", "Editar Campanha");
        return repository.find(id);
    }

    @Transactional
    @Post("/campanha/edit")
    public void edit(final Campanha campanha) {
        List<Message> errors = validate(campanha);

        validator.addAll(errors);
        validator.onErrorUse(json()).withoutRoot().from(errors).exclude("category").serialize();

        repository.merge(campanha);
        result.use(json()).withoutRoot().from("OK").serialize();
    }

    private List<Message> validate(final Campanha campanha) {
        return new Validations(){{
            //Nome da campanha
            that(!campanha.getNome().isEmpty(), "campanha.nome", "nome");

            //Data inicial e data final da campanha
            if (that(campanha.getDataInicial() != null, "campanha.dataInicial", "dataInicial") &
                that(campanha.getDataFinal() != null, "campanha.dataFinal", "dataFinal"))
            {
                //Verificar se a data final é posterior ou igual a data inicial
                that(campanha.getDataFinal().compareTo(campanha.getDataInicial()) >= 0, "ata.dataFinal", "dataFinal.maior");
            }
        }}.getErrors();
    }
}