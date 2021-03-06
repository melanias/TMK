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
import br.org.aappe.erp.bean.Campanha;
import br.org.aappe.erp.enums.CampaignType;
import br.org.aappe.erp.enums.CampaignStatus;
import br.org.aappe.erp.enums.Role;
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
    @Authorized({Role.GERENTE, Role.MARKETING, Role.OPERADOR, Role.ESTAGIARIO})
    public List<Campanha> list() {
        result.include("title", "Campanhas");
        return repository.listAllById();
    }

    @Get("/campanha/refresh")
    @Authorized({Role.GERENTE, Role.MARKETING, Role.OPERADOR, Role.ESTAGIARIO})
    public List<Campanha> refresh() {
        return list();
    }

    @Get("/campanha/view/{id}")
    @Authorized({Role.GERENTE, Role.MARKETING, Role.OPERADOR, Role.ESTAGIARIO})
    public Campanha view(int id) {
        result.include("title", "Informações da Campanha");
        return repository.find(id);
    }

    @Get("/campanha/add")
    @Authorized({Role.GERENTE, Role.MARKETING})
    public void frmAdd() {
        result.include("title", "Cadastrar Campanha");
        result.include("type", CampaignType.getAll());
        result.include("status", CampaignStatus.getAll());
    }

    @Transactional
    @Post("/campanha/add")
    @Authorized({Role.GERENTE, Role.MARKETING})
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
    @Authorized({Role.GERENTE, Role.MARKETING})
    public Campanha frmEdit(int id) {
        result.include("title", "Editar Campanha");
        result.include("type", CampaignType.getAll());
        result.include("status", CampaignStatus.getAll());

        return repository.find(id);
    }

    @Transactional
    @Post("/campanha/edit")
    @Authorized({Role.GERENTE, Role.MARKETING})
    public void edit(final Campanha campanha) {
        List<Message> errors = validate(campanha);

        validator.addAll(errors);
        validator.onErrorUse(json()).withoutRoot().from(errors).exclude("category").serialize();

        repository.merge(campanha);
        result.use(json()).withoutRoot().from("OK").serialize();
    }

    @Transactional
    @Post("/campanha/delete/{id}")
    @Authorized(Role.GERENTE)
    public void delete(int id) {
        final Campanha campanha = repository.find(id);

        List<Message> errors = new Validations(){{
            if (that(campanha != null, "campanha", "campanha.nao.cadastrada"))
                that(campanha.getDoacoes().isEmpty(), "campanha", "campanha.tem.doacao");
        }}.getErrors();

        validator.addAll(errors);
        validator.onErrorUse(json()).withoutRoot().from(errors).exclude("category").serialize();

        repository.remove(campanha);
        result.use(json()).withoutRoot().from("OK").serialize();
    }

    private List<Message> validate(final Campanha campanha) {
        return new Validations() {{
            //Nome da campanha
            that(!campanha.getNome().isEmpty(), "campanha.nome", "nome");

            //Custo Previsto
            //that(campanha.getCustoPrevisto().compareTo(BigDecimal.ZERO) > 0, "campanha.custoPrevisto", "custoPrevisto");

            //Custo Real
            //that(campanha.getCustoReal().compareTo(BigDecimal.ZERO) > 0, "campanha.custoReal", "custoReal");

            //Receita Esperada
            //that(campanha.getReceitaEsperada().compareTo(BigDecimal.ZERO) > 0, "campanha.receitaEsperada", "receitaEsperada");

            //Receita Real
            //that(campanha.getReceitaReal().compareTo(BigDecimal.ZERO) > 0, "campanha.receitaReal", "receitaReal");

            //Data inicial e data final da campanha
            if (that(campanha.getDataInicial() != null, "campanha.dataInicial", "dataInicial") & that(campanha.getDataFinal() != null, "campanha.dataFinal", "dataFinal"))
                that(campanha.getDataFinal().compareTo(campanha.getDataInicial()) >= 0, "data.dataFinal", "dataFinal.maior");
        }}.getErrors();
    }
}