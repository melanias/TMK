package br.org.aappe.erp.dao;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;

import br.org.aappe.erp.bean.Smtp;
import br.org.aappe.erp.enums.Status;
import br.org.aappe.erp.repository.SmtpRepository;

/**
 * @author Thales Imbruglia
 */
@Component
public class SmtpDAO extends DAO<Smtp> implements SmtpRepository {

    public SmtpDAO(EntityManager manager) {
        super(manager);
    }

    @Override
    public Smtp getActiveServer() {
        return (Smtp) manager.createQuery("FROM Smtp WHERE status = ?")
                             .setParameter(1, Status.ATIVO)
                             .getSingleResult();
    }

    @Override
    public boolean isUniqueServerActive(Smtp smtp) {
        return smtp.getId() > 0 ? manager.createQuery("FROM Smtp WHERE status = ? AND id <> ?")
                                         .setParameter(1, Status.ATIVO)
                                         .setParameter(2, smtp.getId())
                                         .getResultList().isEmpty()
                                : manager.createQuery("FROM Smtp WHERE status = ?")
                                         .setParameter(1, Status.ATIVO)
                                         .getResultList().isEmpty();
    }
}