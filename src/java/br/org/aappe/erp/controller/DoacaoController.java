package br.org.aappe.erp.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
import br.org.aappe.erp.bean.Doador;
import br.org.aappe.erp.enums.Month;
import br.org.aappe.erp.enums.Role;
import br.org.aappe.erp.repository.CampanhaRepository;
import br.org.aappe.erp.repository.DoacaoRepository;
import br.org.aappe.erp.repository.DoadorRepository;
import br.org.aappe.erp.repository.FuncionarioRepository;
import br.org.aappe.erp.session.EmployeeSession;

/**
 * @author Phelipe Melanias
 */
@Resource
public class DoacaoController extends MainController {
    private final DoacaoRepository repository;
    private final DoadorRepository doadorRepository;
    private final CampanhaRepository campanhaRepository;
    private final FuncionarioRepository funcionarioRepository;

    public DoacaoController(Result result, Validator validator, EmployeeSession employeeSession, DoacaoRepository repository,
                            DoadorRepository doadorRepository, CampanhaRepository campanhaRepository, FuncionarioRepository funcionarioRepository)
    {
        super(result, validator, employeeSession);

        this.repository = repository;
        this.doadorRepository = doadorRepository;
        this.campanhaRepository = campanhaRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Get("/doacao")
    @Authorized({Role.GERENTE, Role.OPERADOR, Role.ESTAGIARIO})
    public List<Doacao> list() {
        result.include("title", "Doações");
        return repository.listAllById();
    }

    @Get("/doacao/refresh")
    @Authorized({Role.GERENTE, Role.OPERADOR, Role.ESTAGIARIO})
    public List<Doacao> refresh() {
        return list();
    }

    @Get("/doacao/view/{id}")
    @Authorized({Role.GERENTE, Role.OPERADOR, Role.ESTAGIARIO})
    public Doacao view(long id) {
        result.include("title", "Informações da Doação");
        return repository.find(id);
    }

    @Get("/doacao/filterMonthsOfTheYear/{year}")
    public List<Integer> filterMonthsOfTheYear(int year) {
        result.include("months", Month.getAll());
        return repository.getMonthsOfTheYear(year);
    }

    @Get("/doacao/add")
    @Authorized({Role.GERENTE, Role.OPERADOR, Role.ESTAGIARIO})
    public void frmAdd() {
        result.include("title", "Cadastrar Doação");
        result.include("campanhas", campanhaRepository.listAllById());
        result.include("funcionarios", funcionarioRepository.listAllById());
    }

    @Transactional
    @Post("/doacao/add")
    @Authorized({Role.GERENTE, Role.OPERADOR, Role.ESTAGIARIO})
    public void add(final Doacao doacao) {
        //Definir   data  da   ligação
        //antes de iniciar a validação
        if (doacao.getLigacao() == null)
            doacao.setLigacao(new Date());

        List<Message> errors = validate(doacao);

        validator.addAll(errors);
        validator.onErrorUse(json()).withoutRoot().from(errors).exclude("category").serialize();

        //Definir campanha
        if (doacao.getCampanha().getId() == 0)
            doacao.setCampanha(null);

        repository.persist(doacao);
        result.use(json()).withoutRoot().from("OK").serialize();
    }

    @Get("/doacao/edit/{id}")
    @Authorized(Role.GERENTE)
    public Doacao frmEdit(long id) {
        result.include("title", "Editar Doação");
        result.include("campanhas", campanhaRepository.listAllById());
        result.include("funcionarios", funcionarioRepository.listAllById());
        return repository.find(id);
    }

    @Transactional
    @Post("/doacao/edit")
    @Authorized(Role.GERENTE)
    public void edit(final Doacao doacao) {
        List<Message> errors = validate(doacao);

        validator.addAll(errors);
        validator.onErrorUse(json()).withoutRoot().from(errors).exclude("category").serialize();

        //Definir data da ligação
        if (doacao.getLigacao() == null)
            doacao.setLigacao(repository.find(doacao.getId()).getLigacao());

        //Definir campanha
        if (doacao.getCampanha().getId() == 0)
            doacao.setCampanha(null);

        repository.merge(doacao);
        result.use(json()).withoutRoot().from("OK").serialize();
    }

    @Transactional
    @Post("/doacao/delete/{id}")
    @Authorized(Role.GERENTE)
    public void delete(int id) {
        final Doacao doacao = repository.find(id);

        List<Message> errors = new Validations(){{
            that(doacao != null, "doacao", "doacao.nao.cadastrada");
        }}.getErrors();
        validator.addAll(errors);
        validator.onErrorUse(json()).withoutRoot().from(errors).exclude("category").serialize();

        repository.remove(doacao);
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

            //Data da Ligação
            if (doacao.getLigacao() != null && doacao.getDoador().getId() > 0) {
                Doador doador = doadorRepository.find(doacao.getDoador().getId());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                that(sdf.format(doacao.getLigacao()).compareTo(sdf.format(doador.getData())) >= 0,  "doacao.ligacao", "ligacao.menor");
            }

            //Data de Recebimento
            if (that(doacao.getRecebimento() != null, "doacao.recebimento", "recebimento")) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                that(sdf.format(doacao.getRecebimento()).compareTo(sdf.format(doacao.getLigacao())) >= 0, "doacao.recebimento", "recebimento.menor");
            }

            //Doação foi paga?
            if (doacao.isPaga())
                that(employeeSession.getPerfil().equals(Role.GERENTE), "doacao.paga", "doacao.paga", Role.GERENTE.getUserRole());
        }}.getErrors();
    }
}