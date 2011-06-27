package br.org.aappe.erp.dao;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;

import br.org.aappe.erp.bean.Mensagem;
import br.org.aappe.erp.repository.MensagemRepository;

/**
 * @author Phelipe Melanias
 */
@Component
public class MensagemDAO extends DAO<Mensagem> implements MensagemRepository {

    public MensagemDAO(EntityManager manager) {
        super(manager);
    }
}