package br.org.aappe.erp.controller;

import java.util.List;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.validator.Validations;

import br.org.aappe.erp.bean.Doacao;
import br.org.aappe.erp.bean.Doador;
import br.org.aappe.erp.repository.DoacaoRepository;
import br.org.aappe.erp.util.Utilities;

/**
 * @author Phelipe Melanias
 */
@Resource
public class ReportController extends MainController {
    private final DoacaoRepository doacaoRepository;

    public ReportController(Result result, Validator validator, HttpServletResponse response, DoacaoRepository doacaoRepository) {
        super(result, validator, response);
        this.doacaoRepository = doacaoRepository;
    }

    @Get("/relatorio/doacoes-por-periodo")
    public void donationsByPeriod() {
        result.include("title", "Doações por período");
        result.include("years", doacaoRepository.getYears());
    }

    @Post("/relatorio/doacoes-por-periodo")
    public void pdf(final Integer year, String[] months) throws IOException {
        validator.checking(new Validations(){{
            that(year != null, "", "report.year");
        }});
        validator.onErrorForwardTo(this).donationsByPeriod();

        //Obter doações de acordo com o período definido pelo usuário
        final List<Doacao> doacoes = doacaoRepository.getDonationsByPeriod(year, Utilities.implode(months, ","));

        //TODO: Verificar se realmente é necessária a verificação abaixo.
        if (doacoes.isEmpty())
            result.redirectTo(this).donationsByPeriod();

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
            p = new Paragraph("Relatório de Doações por Período", header);
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

            cell = new PdfPCell(new Phrase("DATA DA LIGAÇÂO", label));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setPaddingTop(4);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("DATA DE RECEBIMENTO", label));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setPaddingTop(4);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("DOADOR", label));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setPaddingTop(4);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("CAMPANHA", label));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setPaddingTop(4);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("VALOR", label));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setPaddingTop(4);
            table.addCell(cell);

            //Dados da tabela
            for (Doacao doacao : doacoes) {
                table.addCell(new Phrase(new SimpleDateFormat("dd/MM/yyyy").format(doacao.getLigacao()), text));
                table.addCell(new Phrase(new SimpleDateFormat("dd/MM/yyyy").format(doacao.getRecebimento()), text));
                table.addCell(new Phrase(doacao.getDoador().getFirstAndLastName(), text));
                table.addCell(new Phrase(doacao.getCampanha() == null ? "-" : doacao.getCampanha().getNome(), text));
                table.addCell(new Phrase(DecimalFormat.getCurrencyInstance().format(doacao.getValor()), text));
            }

            document.add(table);
            document.close();

            //Salvar PDF
            response.setContentType("application/pdf");
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            //response.setHeader("Content-Disposition", "attachment;filename=Doações.pdf");
            response.setContentLength(baos.size());

            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();
        } catch (DocumentException e) {
            throw new IOException(e.getMessage());
        }

        result.nothing();


        result.nothing();
    }
}