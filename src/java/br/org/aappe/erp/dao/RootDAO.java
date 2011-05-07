package br.org.aappe.erp.dao;

import br.com.caelum.vraptor.ioc.Component;

import br.org.aappe.erp.bean.Root;
import br.org.aappe.erp.repository.RootRepository;
import javax.persistence.EntityManager;

/**
 * @author Phelipe Melanias
 */
@Component
public class RootDAO extends DAO<Root> implements RootRepository {

    public RootDAO(EntityManager manager) {
        super(manager);
    }
}