package br.org.aappe.erp.repository;

import java.util.List;

import br.org.aappe.erp.bean.Funcionario;

/**
 * @author Phelipe Melanias
 */
public interface FuncionarioRepository {

    Funcionario find(int id);

    List<Funcionario> listAllById();

    void persist(Funcionario funcionario);

    Funcionario merge(Funcionario funcionario);

    boolean isUniqueRg(Funcionario funcionario);

    boolean isUniqueCpf(Funcionario funcionario);

    boolean isUniqueName(Funcionario funcionario);

    boolean isUniqueMail(Funcionario funcionario);

    Funcionario authenticate(String cpf, String senha);
}