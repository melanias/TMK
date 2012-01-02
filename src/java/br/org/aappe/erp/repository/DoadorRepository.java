package br.org.aappe.erp.repository;

import java.util.List;

import br.org.aappe.erp.bean.Doador;

/**
 * @author Phelipe Melanias
 */
public interface DoadorRepository {

    Doador find(int id);

    List<Doador> listAllById();

    void persist(Doador doador);

    Doador merge(Doador doador);

    List<Doador> search(String nome);

    boolean isUniqueRg(Doador doador);

    boolean isUniqueCpf(Doador doador);

    boolean isUniqueCnpj(Doador doador);

    boolean isUniqueName(Doador doador);

    boolean isUniqueMail(Doador doador);
}