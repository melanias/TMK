package br.org.aappe.erp.repository;

import java.util.List;

import br.org.aappe.erp.bean.Campanha;

/**
 * @author Phelipe Melanias
 */
public interface CampanhaRepository {

    Campanha find(int id);

    List<Campanha> listAllById();

    void remove(Campanha campanha);

    void persist(Campanha campanha);

    Campanha merge(Campanha campanha);
}