package br.org.aappe.erp.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.util.Date;
import java.util.List;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

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
import br.org.aappe.erp.enums.DonorStatus;
import br.org.aappe.erp.enums.DonorType;
import br.org.aappe.erp.enums.Role;
import br.org.aappe.erp.repository.DoadorRepository;
import br.org.aappe.erp.util.Utilities;

/**
 * @author Phelipe Melanias
 */
@Resource
public class DoadorController extends MainController {
    private final DoadorRepository repository;

    public DoadorController(Result result, Validator validator, HttpServletResponse response, DoadorRepository repository) {
        super(result, validator, response);
        this.repository = repository;
    }

    @Get("/doador")
    @Authorized({Role.GERENTE, Role.OPERADOR, Role.ESTAGIARIO})
    public List<Doador> list() {
        result.include("title", "Doadores");
        return repository.listAllById();
    }

    @Get("/doador/refresh")
    @Authorized({Role.GERENTE, Role.OPERADOR, Role.ESTAGIARIO})
    public List<Doador> refresh() {
        return list();
    }

    @Get("/doador/view/{id}")
    @Authorized({Role.GERENTE, Role.OPERADOR, Role.ESTAGIARIO})
    public Doador view(int id) {
        result.include("title", "Informações do Doador");
        return repository.find(id);
    }

    @Get("/doador/search")
    @Authorized({Role.GERENTE, Role.OPERADOR, Role.ESTAGIARIO})
    public void search(String term) {
        String nome = ((term == null) ? "" : Utilities.decodeText(term.trim()));

        result.use(json()).withoutRoot().from(repository.search(nome)).exclude("data").serialize();
    }

    @Get("/doador/add")
    @Authorized({Role.GERENTE, Role.OPERADOR})
    public void frmAdd() {
        result.include("title", "Cadastrar Doador");
        result.include("types", DonorType.getAll());
        result.include("status", DonorStatus.getAll());
    }

    @Transactional
    @Post("/doador/add")
    @Authorized({Role.GERENTE, Role.OPERADOR})
    public void add(final Doador doador) {
        List<Message> errors = validate(doador);

        validator.addAll(errors);
        validator.onErrorUse(json()).withoutRoot().from(errors).exclude("category").serialize();

        //Definir data de cadastro do doador
        doador.setData(new Date());

        repository.persist(doador);
        result.use(json()).withoutRoot().from("OK").serialize();
    }

    @Get("/doador/edit/{id}")
    @Authorized({Role.GERENTE, Role.OPERADOR})
    public Doador frmEdit(int id) {
        result.include("title", "Editar Doador");
        result.include("types", DonorType.getAll());
        result.include("status", DonorStatus.getAll());
        return repository.find(id);
    }

    @Transactional
    @Post("/doador/edit")
    @Authorized({Role.GERENTE, Role.OPERADOR})
    public void edit(final Doador doador) {
        List<Message> errors = validate(doador);

        validator.addAll(errors);
        validator.onErrorUse(json()).withoutRoot().from(errors).exclude("category").serialize();

        repository.merge(doador);
        result.use(json()).withoutRoot().from("OK").serialize();
    }

    @Transactional
    @Post("/doador/delete/{id}")
    @Authorized(Role.GERENTE)
    public void delete(int id) {
        final Doador doador = repository.find(id);

        List<Message> errors = new Validations(){{
            if (that(doador != null, "doador", "doador.nao.cadastrado"))
                that(doador.getDoacoes().isEmpty(), "doador", "doador.tem.doacao");
        }}.getErrors();

        validator.addAll(errors);
        validator.onErrorUse(json()).withoutRoot().from(errors).exclude("category").serialize();

        repository.remove(doador);
        result.use(json()).withoutRoot().from("OK").serialize();
    }

    @Get("/doacao/pdf")
    @Authorized(Role.GERENTE)
    public void pdfAll() throws IOException {
        List<Doador> doadores = repository.listAllById();

        if (doadores.isEmpty())
            result.redirectTo(this).list();

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            Document document = new Document(PageSize.A4.rotate(), 18, 18, 18, 18);
            PdfWriter.getInstance(document, baos);

            document.open();

            //Formatação do texto
            Font header = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, BaseColor.DARK_GRAY);
            Font label  = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, BaseColor.DARK_GRAY);
            Font text   = FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.DARK_GRAY);
            Font aviso  = FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.RED);

            //Parágrafo
            Paragraph p;

            //Título
            p = new Paragraph("Relatório de Doações", header);
            p.setAlignment(Element.ALIGN_CENTER);
            p.setSpacingAfter(20);
            document.add(p);

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setSpacingBefore(20);

            table.getDefaultCell().setPaddingTop(4);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

            //Célula
            PdfPCell cell;

            cell = new PdfPCell(new Phrase("DATA DE CADASTRO", label));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setPaddingTop(4);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("NOME", label));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setPaddingTop(4);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("TIPO", label));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setPaddingTop(4);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("SITUAÇÃO", label));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setPaddingTop(4);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("VALOR TOTAL", label));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setPaddingTop(4);
            table.addCell(cell);

            //Dados da tabela
            for (Doador doador : doadores) {
                table.addCell(new Phrase(new SimpleDateFormat("dd/MM/yyyy").format(doador.getData()), text));
                table.addCell(new Phrase(doador.getFirstAndLastName(), text));
                table.addCell(new Phrase(doador.getTipo().getType(), text));
                table.addCell(new Phrase(doador.getStatus().getStatus(), text));
                table.addCell(new Phrase(DecimalFormat.getCurrencyInstance().format(doador.getTotalValueOfDonations()), text));
            }

            document.add(table);
            document.close();

            //Salvar PDF
            response.setContentType("application/pdf");
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setHeader("Content-Disposition", "attachment;filename=Doações.pdf");
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

    @Get("/doador/pdf/{id}")
    @Authorized(Role.GERENTE)
    public void pdf(int id) throws IOException {
        Doador doador = repository.find(id);

        if (doador == null)
            result.redirectTo(this).list();

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            Document document = new Document(PageSize.A4.rotate(), 18, 18, 18, 18);
            PdfWriter.getInstance(document, baos);

            document.open();

            //Formatação do texto
            Font header = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, BaseColor.DARK_GRAY);
            Font label  = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, BaseColor.DARK_GRAY);
            Font text   = FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.DARK_GRAY);
            Font aviso  = FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.RED);

            //Parágrafo
            Paragraph p;

            //Título
            p = new Paragraph("Histórico de Doações", header);
            p.setAlignment(Element.ALIGN_CENTER);
            p.setSpacingAfter(20);
            document.add(p);

            //Doador
            p = new Paragraph();
            p.add(new Phrase("Doador: ", label));
            p.add(new Phrase(doador.getNome() +" ("+ doador.getTipo().getType() +")", text));
            document.add(p);

            p = new Paragraph();
            p.add(new Phrase("Unidade: ", label));
            p.add(new Phrase(doador.getUnidade().getRazaoSocial(), text));
            document.add(p);

            if (doador.getDoacoes().isEmpty()) {
                Paragraph warning = new Paragraph("Nenhuma doação até o momento.", aviso);
                warning.setSpacingBefore(20);

                document.add(warning);
            } else {
                PdfPTable table = new PdfPTable(7);
                table.setWidthPercentage(100);
                table.setSpacingBefore(20);

                table.getDefaultCell().setPaddingTop(4);
                table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

                //Célula
                PdfPCell cell;

                cell = new PdfPCell(new Phrase("DATA DA LIGAÇÃO", label));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setPaddingTop(4);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("FUNCIONÁRIO", label));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setPaddingTop(4);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("VALOR", label));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setPaddingTop(4);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("DATA DE RECEBIMENTO", label));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setPaddingTop(4);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("REPRESENTANTE", label));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setPaddingTop(4);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("CAMPANHA", label));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setPaddingTop(4);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("DESCRIÇÃO", label));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setPaddingTop(4);
                table.addCell(cell);

                for (Doacao doacao : doador.getDoacoes()) {
                    table.addCell(new Phrase(new SimpleDateFormat("dd/MM/yyyy").format(doacao.getLigacao()), text));
                    table.addCell(new Phrase(doacao.getFuncionario().getFirstAndLastName(), text));
                    table.addCell(new Phrase(DecimalFormat.getCurrencyInstance().format(doacao.getValor()), text));
                    table.addCell(new Phrase(new SimpleDateFormat("dd/MM/yyyy").format(doacao.getRecebimento()), text));
                    table.addCell(new Phrase(doacao.getRepresentante().getFirstAndLastName(), text));
                    table.addCell(new Phrase(doacao.getCampanha() == null ? "-" : doacao.getCampanha().getNome(), text));
                    table.addCell(new Phrase(doacao.getDescricao().isEmpty() ? "-" : doacao.getDescricao(), text));
                }

                document.add(table);
            }

            document.close();

            //Salvar PDF
            response.setContentType("application/pdf");
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setHeader("Content-Disposition", "attachment;filename="+ doador.getFirstAndLastName() +".pdf");
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

    private List<Message> validate(final Doador doador) {
        return new Validations(){{
            //Nome
            if (that(!doador.getNome().isEmpty(), "doador.nome", "nome"))
                that(repository.isUniqueName(doador), "doador.nome", "nome.unico");

            //RG
            if (doador.getRg() != null && that(doador.getRg().toString().length() > 5 && doador.getRg().toString().length() < 14, "doador.rg", "rg.invalido", 6, 13))
                that(repository.isUniqueRg(doador), "doador.rg", "rg.unico");

            //CPF
            if (doador.getCpf() != null && !doador.getCpf().isEmpty() && that(Utilities.cpf(doador.getCpf()), "doador.cpf", "cpf.invalido"))
                that(repository.isUniqueCpf(doador), "doador.cpf", "cpf.unico");

            //CNPJ
            if (doador.getCnpj() != null && !doador.getCnpj().isEmpty() && that(Utilities.cnpj(doador.getCnpj()), "empresa.cnpj", "cnpj.invalido"))
                that(repository.isUniqueCnpj(doador), "empresa.cnpj", "cnpj.unico");

            //E-mail
            if (!doador.getEmail().isEmpty() && that(Utilities.mail(doador.getEmail()), "doador.email", "email.invalido"))
                that(repository.isUniqueMail(doador), "doador.email", "email.unico");

            //Telefone ou Celular
            that(!doador.getCelular().isEmpty() || !doador.getTelefone().isEmpty(), "", "telefone.ou.celular");

            //Endereço
            that(!doador.getEndereco().getLogradouro().isEmpty() && !doador.getEndereco().getBairro().isEmpty() &&
                 !doador.getEndereco().getCidade().isEmpty() && !doador.getEndereco().getUf().isEmpty(), "", "incomplete_address");
        }}.getErrors();
    }
}