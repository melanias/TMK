/*package br.org.aappe.erp.dao;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;

import br.org.aappe.erp.bean.Filial;
import br.org.aappe.erp.repository.FilialRepository;

/**
 * @author Phelipe Melanias
 */
/*@Component
public class FilialDAO extends DAO<Filial> implements FilialRepository {

    public FilialDAO(EntityManager manager) {
        super(manager);
    }

    @Override
    public boolean isUniqueCnpj(Filial filial) {
        return filial.getId() > 0 ? manager.createQuery("SELECT filial FROM Filial filial WHERE filial.id <> ? AND filial.cnpj = ?")
                                           .setParameter(1, filial.getId())
                                           .setParameter(2, filial.getCnpj())
                                           .getResultList().isEmpty()
                                  : manager.createQuery("SELECT filial FROM Filial filial WHERE filial.cnpj = ?")
                                           .setParameter(1, filial.getCnpj())
                                           .getResultList().isEmpty();
    }

    @Override
    public boolean isUniqueCompanyName(Filial filial) {
        return filial.getId() > 0 ? manager.createQuery("SELECT filial FROM Filial filial WHERE filial.id <> ? AND lower(filial.nome) = ?")
                                           .setParameter(1, filial.getId())
                                           .setParameter(2, filial.getNome().toLowerCase())
                                           .getResultList().isEmpty()
                                  : manager.createQuery("SELECT filial FROM Filial filial WHERE lower(filial.nome) = ?")
                                           .setParameter(1, filial.getNome().toLowerCase())
                                           .getResultList().isEmpty();
    }

    @Override
    public boolean isUniqueMail(Filial filial) {
        return filial.getId() > 0 ? manager.createQuery("SELECT filial FROM Filial filial WHERE filial.id <> ? AND filial.email = ?")
                                           .setParameter(1, filial.getId())
                                           .setParameter(2, filial.getEmail())
                                           .getResultList().isEmpty()
                                  : manager.createQuery("SELECT filial FROM Filial filial WHERE filial.email = ?")
                                           .setParameter(1, filial.getEmail())
                                           .getResultList().isEmpty();
    }
}*/