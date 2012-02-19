package br.org.aappe.erp.repository;

import java.util.List;

import br.org.aappe.erp.bean.Unidade;

/**
 * @author Phelipe Melanias
 */
public interface UnidadeRepository {

    Unidade find(int id);

    List<Unidade> listAllById();

    void remove(Unidade unidade);

    void persist(Unidade unidade);

    Unidade merge(Unidade unidade);

    boolean isUniqueCnpj(Unidade unidade);
    boolean isUniqueMail(Unidade unidade);
    boolean isUniqueFancyName(Unidade unidade);
    boolean isUniqueCompanyName(Unidade unidade);

    boolean isUniqueMatrixActive(Unidade unidade);
}