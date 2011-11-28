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
import br.org.aappe.erp.bean.Setor;
import br.org.aappe.erp.repository.FilialRepository;
import br.org.aappe.erp.repository.FuncionarioRepository;
import br.org.aappe.erp.repository.SetorRepository;
import br.org.aappe.erp.util.Utilities;

/**
 * @author Phelipe Melanias
 */
@Resource
public class SetorController extends MainController {
    private final SetorRepository repository;
    private final FilialRepository filialRepository;
    private final FuncionarioRepository funcionarioRepository;

    public SetorController(Result result, Validator validator, SetorRepository repository,
                           FilialRepository filialRepository, FuncionarioRepository funcionarioRepository)
    {
        super(result, validator);

        this.repository = repository;
        this.filialRepository = filialRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Get("/setor")
    public List<Setor> list() {
        result.include("title", "Setores");
        return repository.listAllById();
    }

    @Get("/setor/refresh")
    public List<Setor> refresh() {
        return list();
    }

    @Get("/setor/view/{id}")
    public Setor view(int id) {
        result.include("title", "Informações do Setor");
        return repository.find(id);
    }

    @Get("/setor/add")
    public void frmAdd() {
        result.include("title", "Cadastrar Setor");
        result.include("filiais", filialRepository.listAllById());
        result.include("funcionarios", funcionarioRepository.listAllById());
    }

    @Transactional
    @Post("/setor/add")
    public void add(final Setor setor) {
        List<Message> errors = validate(setor);

        validator.addAll(errors);
        validator.onErrorUse(json()).withoutRoot().from(errors).exclude("category").serialize();

        //Verificar se algum responsável foi selecionado
        if (setor.getResponsavel().getId() == 0)
            setor.setResponsavel(null);

        repository.persist(setor);
        result.use(json()).withoutRoot().from("OK").serialize();
    }

    @Get("/setor/edit/{id}")
    public Setor frmEdit(int id) {
        result.include("title", "Editar Setor");
        result.include("filiais", filialRepository.listAllById());
        result.include("funcionarios", funcionarioRepository.listAllById());
        return repository.find(id);
    }

    @Transactional
    @Post("/setor/edit")
    public void edit(final Setor setor) {
        List<Message> errors = validate(setor);

        validator.addAll(errors);
        validator.onErrorUse(json()).withoutRoot().from(errors).exclude("category").serialize();

        //Verificar se algum responsável foi selecionado
        if (setor.getResponsavel().getId() == 0)
            setor.setResponsavel(null);

        repository.merge(setor);
        result.use(json()).withoutRoot().from("OK").serialize();
    }

    private List<Message> validate(final Setor setor) {
        return new Validations(){{
            //Nome do setor
            if (that(!setor.getNome().isEmpty(), "setor.nome", "nome") & that(setor.getFilial().getId() > 0, "setor.filial", "filial_not_selected"))
                that(repository.isUniqueSection(setor), "setor.nome", "nome.setor.unico");

            //Sigla e Filial
            if (that(!setor.getSigla().isEmpty(), "setor.sigla", "sigla") & that(setor.getFilial().getId() > 0, "setor.filial", "filial_not_selected"))
                that(repository.isUniqueAcronym(setor), "setor.sigla", "sigla.unica");

            //E-mail
            if (!setor.getEmail().isEmpty())
                that(Utilities.mail(setor.getEmail()), "setor.email", "email.invalido");
        }}.getErrors();
    }
}