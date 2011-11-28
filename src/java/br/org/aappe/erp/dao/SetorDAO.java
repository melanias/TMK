package br.org.aappe.erp.dao;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;

import br.org.aappe.erp.bean.Setor;
import br.org.aappe.erp.repository.SetorRepository;

/**
 * @author Phelipe Melanias
 */
@Component
public class SetorDAO extends DAO<Setor> implements SetorRepository {

    public SetorDAO(EntityManager manager) {
        super(manager);
    }

    @Override
    public boolean isUniqueSection(Setor setor) {
        return setor.getId() > 0 ? manager.createQuery("SELECT setor FROM Setor setor WHERE setor.id <> ? AND lower(setor.nome) = ? AND setor.filial.id = ?")
                                          .setParameter(1, setor.getId())
                                          .setParameter(2, setor.getNome().toLowerCase())
                                          .setParameter(3, setor.getFilial().getId())
                                          .getResultList().isEmpty()
                                 : manager.createQuery("SELECT setor FROM Setor setor WHERE lower(setor.nome) = ? AND setor.filial.id = ?")
                                          .setParameter(1, setor.getNome().toLowerCase())
                                          .setParameter(2, setor.getFilial().getId())
                                          .getResultList().isEmpty();
    }

    @Override
    public boolean isUniqueAcronym(Setor setor) {
        return setor.getId() > 0 ? manager.createQuery("SELECT setor FROM Setor setor WHERE setor.id <> ? AND setor.sigla = ? AND setor.filial.id = ?")
                                          .setParameter(1, setor.getId())
                                          .setParameter(2, setor.getSigla())
                                          .setParameter(3, setor.getFilial().getId())
                                          .getResultList().isEmpty()
                                 : manager.createQuery("SELECT setor FROM Setor setor WHERE setor.sigla = ? AND setor.filial.id = ?")
                                          .setParameter(1, setor.getSigla())
                                          .setParameter(2, setor.getFilial().getId())
                                          .getResultList().isEmpty();
    }
}