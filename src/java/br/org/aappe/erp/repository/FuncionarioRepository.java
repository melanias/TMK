package br.org.aappe.erp.repository;

import java.util.List;

import br.org.aappe.erp.bean.Funcionario;

/**
 * @author Phelipe Melanias
 */
public interface FuncionarioRepository {

    Funcionario find(int id);

    Funcionario merge(Funcionario Funcionario);

    void persist(Funcionario Funcionario);

    List<Funcionario> listAllById();

    boolean isUniqueRg(Funcionario funcionario);

    boolean isUniqueCpf(Funcionario funcionario);

    boolean isUniqueName(Funcionario funcionario);

    boolean isUniqueMail(Funcionario funcionario);
}