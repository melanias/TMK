package br.org.aappe.erp.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.Get;
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
import br.org.aappe.erp.bean.Filial;
import br.org.aappe.erp.repository.FilialRepository;
import br.org.aappe.erp.util.Utilities;

/**
 * @author Jadson Ronald
 */
@Resource
public class FilialController extends MainController {
    private final FilialRepository repository;

    public FilialController(Result result, Validator validator, HttpServletResponse response, FilialRepository repository) {
        super(result, validator, response);
        this.repository = repository;
    }

    @Get("/filial")
    public List<Filial> list() {
        result.include("title", "Filiais");
        return repository.listAllById();
    }

    @Get("/filial/refresh")
    public List<Filial> refresh() {
        return list();
    }

    @Get("/filial/view/{id}")
    public Filial view(int id) {
        result.include("title", "Informações da Filial");
        return repository.find(id);
    }

    @Get("/filial/add")
    public void frmAdd() {
        result.include("title", "Cadastrar Filial");
    }

    @Transactional
    @Post("/filial/add")
    public void add(final Filial filial) {
        List<Message> errors = validate(filial);

        validator.addAll(errors);
        validator.onErrorUse(json()).withoutRoot().from(errors).exclude("category").serialize();

        //Definir data de cadastro da filial
        filial.setData(new Date());

        repository.persist(filial);
        result.use(json()).withoutRoot().from("OK").serialize();
    }

    @Get("/filial/edit/{id}")
    public Filial frmEdit(int id) {
        result.include("title", "Editar Filial");
        return repository.find(id);
    }

    @Transactional
    @Post("/filial/edit")
    public void edit(final Filial filial) {
        List<Message> errors = validate(filial);

        validator.addAll(errors);
        validator.onErrorUse(json()).withoutRoot().from(errors).exclude("category").serialize();

        repository.merge(filial);
        result.use(json()).withoutRoot().from("OK").serialize();
    }

    @Get("/filial/pdf")
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
            cell = new PdfPCell(new Phrase("FILIAIS", title));
            cell.setBackgroundColor(BaseColor.GRAY);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPaddingTop(4);
            cell.setColspan(5);
            table.addCell(cell);

            List<Filial> filiais = repository.listAllById();

            if (filiais.isEmpty()) {
                cell = new PdfPCell(new Phrase("Nenhuma filial cadastrada até o momento.", normal));
                cell.setPadding(6);
                cell.setColspan(5);
                table.addCell(cell);
            } else {
                cell = new PdfPCell(new Phrase("CNPJ", header));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setPaddingTop(4);
                table.addCell(cell);

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

                for (Filial f : repository.listAllById()) {
                    table.addCell(new Phrase(f.getCnpj(), normal));
                    table.addCell(new Phrase(f.getNome(), normal));
                    table.addCell(new Phrase(f.getEmail(), normal));
                    table.addCell(new Phrase(f.getTelefone(), normal));
                    table.addCell(new Phrase(f.getFax(), normal));
                }
            }

            document.add(table);
            document.close();

            //Salvar PDF
            response.setContentType("application/pdf");
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setHeader("Content-Disposition", "attachment;filename=Filiais.pdf");
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

    private List<Message> validate(final Filial filial) {
        return new Validations(){{
            //CNPJ
            if (that(!filial.getCnpj().isEmpty(), "filial.cnpj", "cnpj") &&
                that(Utilities.cnpj(filial.getCnpj()), "filial.cnpj", "cnpj.invalido"))
                that(repository.isUniqueCnpj(filial), "filial.cnpj", "cnpj.unico");

            //Nome
            if (that(!filial.getNome().isEmpty(), "filial.nome", "nome"))
                that(repository.isUniqueCompanyName(filial), "filial.nome", "nome.unico");

            //E-mail
            if (!filial.getEmail().isEmpty() &&
                that(Utilities.mail(filial.getEmail()), "filial.email", "email.invalido"))
                that(repository.isUniqueMail(filial), "filial.email", "email.unico");

            that(!filial.getTelefone().isEmpty() , "filial.telefone", "telefone");
        }}.getErrors();
    }
}