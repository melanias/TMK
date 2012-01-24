package br.org.aappe.erp.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Message;
import br.com.caelum.vraptor.validator.Validations;
import static br.com.caelum.vraptor.view.Results.*;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.org.aappe.erp.annotations.Transactional;
import br.org.aappe.erp.bean.Setor;
import br.org.aappe.erp.repository.FuncionarioRepository;
import br.org.aappe.erp.repository.SetorRepository;
import br.org.aappe.erp.repository.UnidadeRepository;
import br.org.aappe.erp.util.Utilities;

/**
 * @author Phelipe Melanias
 */
@Resource @Path("/admin")
public class SetorController extends MainController {
    private final SetorRepository repository;
    private final UnidadeRepository unidadeRepository;
    private final FuncionarioRepository funcionarioRepository;

    public SetorController(Result result, Validator validator, HttpServletResponse response, SetorRepository repository,
                           UnidadeRepository unidadeRepository, FuncionarioRepository funcionarioRepository)
    {
        super(result, validator, response);

        this.repository = repository;
        this.unidadeRepository = unidadeRepository;
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
        result.include("unidades", unidadeRepository.listAllById());
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
        result.include("unidades", unidadeRepository.listAllById());
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

    @Get("/setor/pdf")
    public void pdf() throws IOException {
        try {
            Document document = new Document(PageSize.A3, 18, 18, 18, 18);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            PdfWriter.getInstance(document, baos);

            document.open();

            //Tabela
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);

            table.getDefaultCell().setPaddingTop(4);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

            //Célula
            PdfPCell cell;

            Font title  = FontFactory.getFont(FontFactory.HELVETICA, 10);
            Font header = FontFactory.getFont(FontFactory.HELVETICA, 8);
            Font normal = FontFactory.getFont(FontFactory.HELVETICA, 7);

            //Título da tabela
            cell = new PdfPCell(new Phrase("SETORES", title));
            cell.setBackgroundColor(BaseColor.GRAY);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPaddingTop(4);
            cell.setColspan(6);
            table.addCell(cell);

            List<Setor> setores = repository.listAllById();

            if (setores.isEmpty()) {
                cell = new PdfPCell(new Phrase("Nenhum setor cadastrado até o momento.", normal));
                cell.setPadding(6);
                cell.setColspan(5);
                table.addCell(cell);
            } else {
                cell = new PdfPCell(new Phrase("NOME", header));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setPaddingTop(4);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("E-MAIL", header));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setPaddingTop(4);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("TELEFONE", header));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setPaddingTop(4);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("FAX", header));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setPaddingTop(4);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("RESPONSÁVEL", header));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setPaddingTop(4);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("UNIDADE", header));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setPaddingTop(4);
                table.addCell(cell);

                for (Setor s : setores) {
                    table.addCell(new Phrase(s.getNome(), normal));
                    table.addCell(new Phrase(s.getEmail(), normal));
                    table.addCell(new Phrase(s.getTelefone(), normal));
                    table.addCell(new Phrase(s.getFax(), normal));

                    if (s.getResponsavel() != null)
                        table.addCell(new Phrase(s.getResponsavel().getFirstAndLastName(), normal));
                    else
                        table.addCell(new Phrase("-", normal));

                    table.addCell(new Phrase(s.getUnidade().getRazaoSocial()+ (s.getUnidade().isMatriz() ? " - Matriz" : ""), normal));
                }
            }

            document.add(table);
            document.close();

            //Salvar PDF
            response.setContentType("application/pdf");
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setHeader("Content-Disposition", "attachment;filename=Setores.pdf");
            response.setContentLength(baos.size());

            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();
        } catch (DocumentException e) {
            throw new IOException(e.getMessage());
        }

        result.nothing();
    }

    private List<Message> validate(final Setor setor) {
        return new Validations(){{
            //Nome, sigla e unidade
            if ((that(!setor.getNome().isEmpty(), "setor.nome", "nome") & that(!setor.getSigla().isEmpty(), "setor.sigla", "sigla")) &
                 that(setor.getUnidade().getId() > 0, "setor.unidade", "unidade_not_selected")) {
                that(repository.isUniqueSection(setor), "setor.nome", "nome.setor.unico");
                that(repository.isUniqueAcronym(setor), "setor.sigla", "sigla.unica");
            }

            //E-mail
            if (!setor.getEmail().isEmpty())
                that(Utilities.mail(setor.getEmail()), "setor.email", "email.invalido");
        }}.getErrors();
    }
}