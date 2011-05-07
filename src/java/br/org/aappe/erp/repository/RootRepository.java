package br.org.aappe.erp.repository;

import java.util.List;

import br.org.aappe.erp.bean.Root;

/**
 * @author Phelipe Melanias
 */
public interface RootRepository {

    Root find(int id);

    List<Root> listAllById();

    void persist(Root root);

    Root merge(Root root);
}