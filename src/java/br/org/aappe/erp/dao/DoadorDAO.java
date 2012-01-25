package br.org.aappe.erp.dao;

import java.util.List;
import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;

import br.org.aappe.erp.bean.Doador;
import br.org.aappe.erp.bean.Unidade;
import br.org.aappe.erp.repository.DoadorRepository;

/**
 * @author Phelipe Melanias
 */
@Component
public class DoadorDAO extends DAO<Doador> implements DoadorRepository {

    public DoadorDAO(EntityManager manager) {
        super(manager);
    }

    @Override
    public boolean isUniqueRg(Doador doador) {
        return doador.getId() > 0 ? manager.createQuery("FROM Doador doador WHERE doador.id <> ? AND doador.rg = ?")
                                           .setParameter(1, doador.getId())
                                           .setParameter(2, doador.getRg())
                                           .getResultList().isEmpty()
                                  : manager.createQuery("FROM Doador doador WHERE doador.rg = ?")
                                           .setParameter(1, doador.getRg())
                                           .getResultList().isEmpty();
    }

    @Override
    public boolean isUniqueCpf(Doador doador) {
        return doador.getId() > 0 ? manager.createQuery("FROM Doador doador WHERE doador.id <> ? AND doador.cpf = ?")
                                           .setParameter(1, doador.getId())
                                           .setParameter(2, doador.getCpf())
                                           .getResultList().isEmpty()
                                  : manager.createQuery("FROM Doador doador WHERE doador.cpf = ?")
                                           .setParameter(1, doador.getCpf())
                                           .getResultList().isEmpty();
    }

    @Override
    public boolean isUniqueCnpj(Doador doador) {
        return doador.getId() > 0 ? manager.createQuery("FROM Doador doador WHERE doador.id <> ? AND doador.cnpj = ?")
                                           .setParameter(1, doador.getId())
                                           .setParameter(2, doador.getCnpj())
                                           .getResultList().isEmpty()
                                  : manager.createQuery("FROM Doador doador WHERE doador.cnpj = ?")
                                           .setParameter(1, doador.getCnpj())
                                           .getResultList().isEmpty();
    }

    @Override
    public boolean isUniqueName(Doador doador) {
        return doador.getId() > 0 ? manager.createQuery("FROM Doador doador WHERE doador.id <> ? AND lower(doador.nome) = ?")
                                           .setParameter(1, doador.getId())
                                           .setParameter(2, doador.getNome().toLowerCase())
                                           .getResultList().isEmpty()
                                  : manager.createQuery("FROM Doador doador WHERE lower(doador.nome) = ?")
                                           .setParameter(1, doador.getNome().toLowerCase())
                                           .getResultList().isEmpty();
    }

    @Override
    public boolean isUniqueMail(Doador doador) {
        return doador.getId() > 0 ? manager.createQuery("FROM Doador doador WHERE doador.id <> ? AND doador.email = ?")
                                           .setParameter(1, doador.getId())
                                           .setParameter(2, doador.getEmail())
                                           .getResultList().isEmpty()
                                  : manager.createQuery("FROM Doador doador WHERE doador.email = ?")
                                           .setParameter(1, doador.getEmail())
                                           .getResultList().isEmpty();
    }

    @Override
    public List<Doador> search(String nome) {
        return manager.createQuery("FROM Doador d WHERE LOWER(d.nome) LIKE :nome AND d.status <> 0 ORDER BY d.nome")
                      .setParameter("nome", ""+ nome.toLowerCase() +"%")
                      .getResultList();
    }

    @Override
    public List<Doador> listAllByUnitId(Unidade unidade) {
        return manager.createQuery("FROM Doador WHERE unidade = ?").setParameter(1, unidade).getResultList();
    }
}