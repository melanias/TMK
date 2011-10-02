package br.org.aappe.erp.repository;

import java.util.List;

import br.org.aappe.erp.bean.User;

/**
 * @author Phelipe Melanias
 */
public interface UserRepository {

    User find(int id);

    User merge(User user);

    void persist(User user);

    List<User> listAllById();

    boolean isUniqueCpf(User user);

    boolean isUniqueName(User user);

    boolean isUniqueMail(User user);

    boolean isUniqueLogin(User user);
}