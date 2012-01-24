package br.org.aappe.erp.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.validator.Message;
import br.com.caelum.vraptor.validator.Validations;
import static br.com.caelum.vraptor.view.Results.*;

import br.org.aappe.erp.annotations.Transactional;
import br.org.aappe.erp.bean.Setor;
import br.org.aappe.erp.bean.Unidade;
import br.org.aappe.erp.repository.UnidadeRepository;
import br.org.aappe.erp.util.Utilities;

/**
 * @author Phelipe Melanias
 */
@Resource @Path("/admin")
public class UnidadeController extends MainController {
    private final UnidadeRepository repository;

    public UnidadeController(Result result, Validator validator, HttpServletResponse response, UnidadeRepository repository) {
        super(result, validator, response);
        this.repository = repository;
    }

    @Get("/unidade")
    public List<Unidade> list() {
        result.include("title", "Unidades");
        return repository.listAllById();
    }

    @Get("/unidade/refresh")
    public List<Unidade> refresh() {
        return list();
    }

    @Get("/unidade/view/{id}")
    public Unidade view(int id) {
        result.include("title", "Informações da Unidade");
        return repository.find(id);
    }

    @Get("/unidade/filterSectorsPerUnit/{id}")
    public List<Setor> filterSectorsPerUnit(int id) {
        Unidade unidade = repository.find(id);

        return ((unidade == null) ? null : unidade.getSetores());
    }

    @Get("/unidade/add")
    public void frmAdd() {
        result.include("title", "Cadastrar Unidade");
    }

    @Transactional
    @Post("/unidade/add")
    public void add(final Unidade unidade) {
        List<Message> errors = validate(unidade);

        validator.addAll(errors);
        validator.onErrorUse(json()).withoutRoot().from(errors).exclude("category").serialize();

        //Definir data de cadastro da Unidade
        unidade.setData(new Date());

        repository.persist(unidade);
        result.use(json()).withoutRoot().from("OK").serialize();
    }

    @Get("/unidade/edit/{id}")
    public Unidade frmEdit(int id) {
        result.include("title", "Editar Unidade");
        return repository.find(id);
    }

    @Transactional
    @Post("/unidade/edit")
    public void edit(final Unidade unidade) {
        List<Message> errors = validate(unidade);

        validator.addAll(errors);
        validator.onErrorUse(json()).withoutRoot().from(errors).exclude("category").serialize();

        repository.merge(unidade);
        result.use(json()).withoutRoot().from("OK").serialize();
    }

    @Get("/unidade/pdf")
    public void pdf() throws IOException {
        try {
            Document document = new Document(PageSize.A3, 18, 18, 18, 18);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            PdfWriter.getInstance(document, baos);

            document.open();

            //Tabela
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);

            table.getDefaultCell().setPaddingTop(4);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

            //Célula
            PdfPCell cell;

            Font title  = FontFactory.getFont(FontFactory.HELVETICA, 10);
            Font header = FontFactory.getFont(FontFactory.HELVETICA, 8);
            Font normal = FontFactory.getFont(FontFactory.HELVETICA, 7);

            //Título da tabela
            cell = new PdfPCell(new Phrase("UNIDADES", title));
            cell.setBackgroundColor(BaseColor.GRAY);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPaddingTop(4);
            cell.setColspan(5);
            table.addCell(cell);

            List<Unidade> unidades = repository.listAllById();

            if (unidades.isEmpty()) {
                cell = new PdfPCell(new Phrase("Nenhuma unidade cadastrada até o momento.", normal));
                cell.setPadding(6);
                cell.setColspan(5);
                table.addCell(cell);
            } else {
                cell = new PdfPCell(new Phrase("CNPJ", header));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setPaddingTop(4);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("RAZÃO SOCIAL", header));
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

                for (Unidade u : unidades) {
                    table.addCell(new Phrase(u.getCnpj(), normal));
                    table.addCell(new Phrase(u.getRazaoSocial() + (u.isMatriz() ? " - Matriz" : ""), normal));
                    table.addCell(new Phrase(u.getEmail(), normal));
                    table.addCell(new Phrase(u.getTelefone(), normal));
                    table.addCell(new Phrase(u.getFax(), normal));
                }
            }

            document.add(table);
            document.close();

            //Salvar PDF
            response.setContentType("application/pdf");
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setHeader("Content-Disposition", "attachment;filename=Unidades.pdf");
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

    private List<Message> validate(final Unidade unidade) {
        return new Validations(){{
            //CNPJ
            if (that(!unidade.getCnpj().isEmpty(), "unidade.cnpj", "cnpj") && that(Utilities.cnpj(unidade.getCnpj()), "unidade.cnpj", "cnpj.invalido"))
                that(repository.isUniqueCnpj(unidade), "unidade.cnpj", "cnpj.invalido");

            //Razão Social
            if (that(!unidade.getRazaoSocial().isEmpty(), "unidade.razaoSocial", "razaoSocial"))
                that(repository.isUniqueCompanyName(unidade), "unidade.razaoSocial", "razaoSocial.unica");

            //Nome Fantasia
            if (!unidade.getNomeFantasia().isEmpty())
                that(repository.isUniqueFancyName(unidade), "unidade.nomeFantasia", "nomeFantasia.unico");

            //E-mail
            if (!unidade.getEmail().isEmpty())
                that(Utilities.mail(unidade.getEmail()), "unidade.email", "email.invalido");

            //Matriz
            if (unidade.isMatriz())
                that(repository.isUniqueMatrixActive(unidade), "unidade.matriz", "matriz.unica");
        }}.getErrors();
    }
}