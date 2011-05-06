package br.org.aappe.erp.repository;

import java.util.List;

import br.org.aappe.erp.bean.Empresa;

/**
 * @author Phelipe Melanias
 */
public interface EmpresaRepository {

    Empresa find(int id);

    List<Empresa> listAllById();

    void persist(Empresa empresa);

    Empresa merge(Empresa empresa);
}