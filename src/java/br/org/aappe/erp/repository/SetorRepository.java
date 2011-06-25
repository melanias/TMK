package br.org.aappe.erp.repository;

import java.util.List;

import br.org.aappe.erp.bean.Setor;

/**
 * @author Phelipe Melanias
 */
public interface SetorRepository {

    Setor find(int id);

    Setor merge(Setor setor);

    void persist(Setor setor);

    List<Setor> listAllById();

    boolean isUniqueSection(Setor setor);

    boolean isUniqueAcronym(Setor setor);
}