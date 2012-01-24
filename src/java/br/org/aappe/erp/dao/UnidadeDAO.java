package br.org.aappe.erp.dao;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;

import br.org.aappe.erp.bean.Unidade;
import br.org.aappe.erp.enums.Status;
import br.org.aappe.erp.repository.UnidadeRepository;

/**
 * @author Phelipe Melanias
 */
@Component
public class UnidadeDAO extends DAO<Unidade> implements UnidadeRepository {

    public UnidadeDAO(EntityManager manager) {
        super(manager);
    }

    @Override
    public boolean isUniqueCnpj(Unidade unidade) {
        return unidade.getId() > 0 ? manager.createQuery("FROM Unidade unidade WHERE unidade.id <> ? AND unidade.cnpj = ?")
                                            .setParameter(1, unidade.getId())
                                            .setParameter(2, unidade.getCnpj())
                                            .getResultList().isEmpty()
                                   : manager.createQuery("FROM Unidade unidade WHERE unidade.cnpj = ?")
                                            .setParameter(1, unidade.getCnpj())
                                            .getResultList().isEmpty();
    }

    @Override
    public boolean isUniqueMail(Unidade unidade) {
        return unidade.getId() > 0 ? manager.createQuery("FROM Unidade unidade WHERE unidade.id <> ? AND unidade.email = ?")
                                           .setParameter(1, unidade.getId())
                                           .setParameter(2, unidade.getEmail())
                                           .getResultList().isEmpty()
                                   : manager.createQuery("FROM Unidade unidade WHERE unidade.email = ?")
                                           .setParameter(1, unidade.getEmail())
                                           .getResultList().isEmpty();
    }

    @Override
    public boolean isUniqueFancyName(Unidade unidade) {
        return unidade.getId() > 0 ? manager.createQuery("FROM Unidade unidade WHERE unidade.id <> ? AND lower(unidade.nomeFantasia) = ?")
                                           .setParameter(1, unidade.getId())
                                           .setParameter(2, unidade.getNomeFantasia().toLowerCase())
                                           .getResultList().isEmpty()
                                   : manager.createQuery("FROM Unidade unidade WHERE lower(unidade.nomeFantasia) = ?")
                                           .setParameter(1, unidade.getNomeFantasia().toLowerCase())
                                           .getResultList().isEmpty();
    }

    @Override
    public boolean isUniqueCompanyName(Unidade unidade) {
        return unidade.getId() > 0 ? manager.createQuery("FROM Unidade unidade WHERE unidade.id <> ? AND lower(unidade.razaoSocial) = ?")
                                            .setParameter(1, unidade.getId())
                                            .setParameter(2, unidade.getRazaoSocial().toLowerCase())
                                            .getResultList().isEmpty()
                                   : manager.createQuery("FROM Unidade unidade WHERE lower(unidade.razaoSocial) = ?")
                                            .setParameter(1, unidade.getRazaoSocial().toLowerCase())
                                            .getResultList().isEmpty();
    }

    @Override
    public boolean isUniqueMatrixActive(Unidade unidade) {
        return unidade.getId() > 0 ? manager.createQuery("FROM Unidade WHERE matriz = ? AND status = ? AND id <> ?")
                                            .setParameter(1, true)
                                            .setParameter(2, Status.ATIVO)
                                            .setParameter(3, unidade.getId())
                                            .getResultList().isEmpty()
                                   : manager.createQuery("FROM Unidade WHERE matriz = ? AND status = ?")
                                            .setParameter(1, true)
                                            .setParameter(2, Status.ATIVO)
                                            .getResultList().isEmpty();
    }
}