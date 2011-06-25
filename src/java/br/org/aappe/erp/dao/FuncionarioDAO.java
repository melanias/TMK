package br.org.aappe.erp.dao;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;

import br.org.aappe.erp.bean.Funcionario;
import br.org.aappe.erp.repository.FuncionarioRepository;

/**
 * @author Phelipe Melanias
 */
@Component
public class FuncionarioDAO extends DAO<Funcionario> implements FuncionarioRepository {

    public FuncionarioDAO(EntityManager manager) {
        super(manager);
    }

    @Override
    public boolean isUniqueRg(Funcionario funcionario) {
        return funcionario.getId() > 0 ? manager.createQuery("SELECT f FROM Funcionario f WHERE f.id <> ? AND f.rg = ?")
                                                .setParameter(1, funcionario.getId())
                                                .setParameter(2, funcionario.getRg())
                                                .getResultList().isEmpty()
                                       : manager.createQuery("SELECT f FROM Funcionario f WHERE f.rg = ?")
                                                .setParameter(1, funcionario.getRg())
                                                .getResultList().isEmpty();
    }

    @Override
    public boolean isUniqueCpf(Funcionario funcionario) {
        return funcionario.getId() > 0 ? manager.createQuery("SELECT f FROM Funcionario f WHERE f.id <> ? AND f.cpf = ?")
                                                .setParameter(1, funcionario.getId())
                                                .setParameter(2, funcionario.getCpf())
                                                .getResultList().isEmpty()
                                       : manager.createQuery("SELECT f FROM Funcionario f WHERE f.cpf = ?")
                                                .setParameter(1, funcionario.getCpf())
                                                .getResultList().isEmpty();
    }

    @Override
    public boolean isUniqueName(Funcionario funcionario) {
        return funcionario.getId() > 0 ? manager.createQuery("SELECT f FROM Funcionario f WHERE f.id <> ? AND lower(f.nome) = ?")
                                                .setParameter(1, funcionario.getId())
                                                .setParameter(2, funcionario.getNome().toLowerCase())
                                                .getResultList().isEmpty()
                                       : manager.createQuery("SELECT f FROM Funcionario f WHERE lower(f.nome) = ?")
                                                .setParameter(1, funcionario.getNome().toLowerCase())
                                                .getResultList().isEmpty();
    }

    @Override
    public boolean isUniqueMail(Funcionario funcionario) {
        return funcionario.getId() > 0 ? manager.createQuery("SELECT f FROM Funcionario f WHERE f.id <> ? AND f.email = ?")
                                                .setParameter(1, funcionario.getId())
                                                .setParameter(2, funcionario.getEmail())
                                                .getResultList().isEmpty()
                                       : manager.createQuery("SELECT f FROM Funcionario f WHERE f.email = ?")
                                                .setParameter(1, funcionario.getEmail())
                                                .getResultList().isEmpty();
    }
}