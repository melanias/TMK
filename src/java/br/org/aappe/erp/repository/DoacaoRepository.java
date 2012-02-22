package br.org.aappe.erp.repository;

import java.util.List;

import br.org.aappe.erp.bean.Doacao;

/**
 * @author Phelipe Melanias
 */
public interface DoacaoRepository {

    Doacao find(long id);

    List<Doacao> listAllById();

    void remove(Doacao doacao);

    void persist(Doacao doacao);

    Doacao merge(Doacao doacao);

    List<Integer> getYears();
    List<Integer> getMonthsOfTheYear(int year);
    List<Doacao> getDonationsByPeriod(int year, String months);
}