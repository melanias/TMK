package br.org.aappe.erp.dao;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;

import br.org.aappe.erp.bean.Doacao;
import br.org.aappe.erp.repository.DoacaoRepository;

/**
 * @author Phelipe Melanias
 */
@Component
public class DoacaoDAO extends DAO<Doacao> implements DoacaoRepository {

    public DoacaoDAO(EntityManager manager) {
        super(manager);
    }
}