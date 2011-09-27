package br.org.aappe.erp.dao;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;

import br.org.aappe.erp.bean.User;
import br.org.aappe.erp.repository.UserRepository;

/**
 * @author Phelipe Melanias
 */
@Component
public class UserDAO extends DAO<User> implements UserRepository {

    public UserDAO(EntityManager manager) {
        super(manager);
    }

    @Override
    public boolean hasAdmin() {
        return !super.listAllById().isEmpty();
    }

    @Override
    public boolean isUniqueCpf(User user) {
        return user.getId() > 0 ? manager.createQuery("SELECT user FROM User user WHERE user.id <> ? AND user.cpf = ?")
                                         .setParameter(1, user.getId())
                                         .setParameter(2, user.getCpf())
                                         .getResultList().isEmpty()
                                : manager.createQuery("SELECT user FROM User user WHERE user.cpf = ?")
                                         .setParameter(1, user.getCpf())
                                         .getResultList().isEmpty();
    }

    @Override
    public boolean isUniqueName(User user) {
        return user.getId() > 0 ? manager.createQuery("SELECT user FROM User user WHERE user.id <> ? AND lower(user.nome) = ?")
                                         .setParameter(1, user.getId())
                                         .setParameter(2, user.getNome().toLowerCase())
                                         .getResultList().isEmpty()
                                : manager.createQuery("SELECT user FROM User user WHERE lower(user.nome) = ?")
                                         .setParameter(1, user.getNome().toLowerCase())
                                         .getResultList().isEmpty();
    }

    @Override
    public boolean isUniqueMail(User user) {
        return user.getId() > 0 ? manager.createQuery("SELECT user FROM User user WHERE user.id <> ? AND lower(user.email) = ?")
                                         .setParameter(1, user.getId())
                                         .setParameter(2, user.getEmail().toLowerCase())
                                         .getResultList().isEmpty()
                                : manager.createQuery("SELECT user FROM User user WHERE lower(user.email) = ?")
                                         .setParameter(1, user.getEmail().toLowerCase())
                                         .getResultList().isEmpty();
    }

    @Override
    public boolean isUniqueLogin(User user) {
        return user.getId() > 0 ? manager.createQuery("SELECT user FROM User user WHERE user.id <> ? AND lower(user.login) = ?")
                                         .setParameter(1, user.getId())
                                         .setParameter(2, user.getLogin().toLowerCase())
                                         .getResultList().isEmpty()
                                : manager.createQuery("SELECT user FROM User user WHERE lower(user.login) = ?")
                                         .setParameter(1, user.getLogin().toLowerCase())
                                         .getResultList().isEmpty();
    }
}