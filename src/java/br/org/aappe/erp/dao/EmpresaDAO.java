package br.org.aappe.erp.dao;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;

import br.org.aappe.erp.bean.Empresa;
import br.org.aappe.erp.repository.EmpresaRepository;

/**
 * @author Phelipe Melanias
 */
@Component
public class EmpresaDAO extends DAO<Empresa> implements EmpresaRepository {

    public EmpresaDAO(EntityManager manager) {
        super(manager);
    }
}