package br.org.aappe.erp.controller;

import java.math.BigDecimal;
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
import br.org.aappe.erp.bean.Doacao;
import br.org.aappe.erp.enums.Role;
import br.org.aappe.erp.repository.CampanhaRepository;
import br.org.aappe.erp.repository.DoacaoRepository;
import br.org.aappe.erp.repository.FuncionarioRepository;

/**
 * @author Phelipe Melanias
 */
@Resource
public class DoacaoController extends MainController {
    private final DoacaoRepository repository;
    private final CampanhaRepository campanhaRepository;
    private final FuncionarioRepository funcionarioRepository;

    public DoacaoController(Result result, Validator validator, DoacaoRepository repository,
                            CampanhaRepository campanhaRepository, FuncionarioRepository funcionarioRepository)
    {
        super(result, validator);

        this.repository = repository;
        this.campanhaRepository = campanhaRepository;
        this.funcionarioRepository = funcionarioRepository;
    }
    
    @Get("/doacao")
    @Authorized({Role.ADMINISTRADOR, Role.GERENTE, Role.OPERADOR, Role.ESTAGIARIO})
    public List<Doacao> list() {
        result.include("title", "Doações");
        return repository.listAllById();
    }

    @Get("/doacao/refresh")
    @Authorized({Role.ADMINISTRADOR, Role.GERENTE, Role.OPERADOR, Role.ESTAGIARIO})
    public List<Doacao> refresh() {
        return list();
    }

    @Get("/doacao/view/{id}")
    @Authorized({Role.ADMINISTRADOR, Role.GERENTE, Role.OPERADOR, Role.ESTAGIARIO})
    public Doacao view(long id) {
        result.include("title", "Informações da Doação");
        return repository.find(id);
    }

    @Get("/doacao/add")
    @Authorized({Role.ADMINISTRADOR, Role.GERENTE, Role.OPERADOR, Role.ESTAGIARIO})
    public void frmAdd() {
        result.include("title", "Cadastrar Doação");
        result.include("campanhas", campanhaRepository.listAllById());
        result.include("funcionarios", funcionarioRepository.listAllById());
    }

    @Transactional
    @Post("/doacao/add")
    @Authorized({Role.ADMINISTRADOR, Role.GERENTE, Role.OPERADOR, Role.ESTAGIARIO})
    public void add(final Doacao doacao) {
        List<Message> errors = validate(doacao);

        validator.addAll(errors);
        validator.onErrorUse(json()).withoutRoot().from(errors).exclude("category").serialize();

        //Definir data de cadastro da ligação
        doacao.setLigacao(new Date());

        //Definir campanha
        if (doacao.getCampanha().getId() == 0)
            doacao.setCampanha(null);

        repository.persist(doacao);
        result.use(json()).withoutRoot().from("OK").serialize();
    }

    @Get("/doacao/edit/{id}")
    @Authorized({Role.ADMINISTRADOR, Role.GERENTE})
    public Doacao frmEdit(long id) {
        result.include("title", "Editar Doação");
        result.include("campanhas", campanhaRepository.listAllById());
        result.include("funcionarios", funcionarioRepository.listAllById());
        return repository.find(id);
    }

    @Transactional
    @Post("/doacao/edit")
    @Authorized({Role.ADMINISTRADOR, Role.GERENTE})    
    public void edit(final Doacao doacao) {
        List<Message> errors = validate(doacao);

        validator.addAll(errors);
        validator.onErrorUse(json()).withoutRoot().from(errors).exclude("category").serialize();

        //Definir campanha
        if (doacao.getCampanha().getId() == 0)
            doacao.setCampanha(null);

        repository.merge(doacao);
        result.use(json()).withoutRoot().from("OK").serialize();
    }

    private List<Message> validate(final Doacao doacao) {
        return new Validations(){{
            //Doador
            that(doacao.getDoador().getId() > 0, "doacao.doador", "doador");

            //Valor
            that(doacao.getValor().compareTo(BigDecimal.ZERO) > 0, "doacao.valor", "valor");

            //Representante
            that(doacao.getRepresentante().getId() > 0, "doacao.representante", "representante");

            //Data de Recebimento
            that(doacao.getRecebimento() != null, "doacao.recebimento", "recebimento");
        }}.getErrors();
    }
}