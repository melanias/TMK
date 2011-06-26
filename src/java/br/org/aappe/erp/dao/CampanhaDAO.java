package br.org.aappe.erp.dao;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;

import br.org.aappe.erp.bean.Campanha;
import br.org.aappe.erp.repository.CampanhaRepository;

/**
 * @author Phelipe Melanias
 */
@Component
public class CampanhaDAO extends DAO<Campanha> implements CampanhaRepository {

    public CampanhaDAO(EntityManager manager) {
        super(manager);
    }
}