/*package br.org.aappe.erp.bo;

import javax.validation.Valid;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;

import br.org.aappe.erp.repository.FuncionarioRepository;

/**
 * @author Phelipe Melanias
 *
public class LoginBO {
    @Valid
    private String login, senha;

    private FuncionarioRepository repository;

    public LoginBO(String login, String senha, FuncionarioRepository repository) {
        this.login = login.trim();
        this.senha = senha.trim();
        this.repository = repository;
    }

    @AssertFalse(message="{login}")
    public boolean isLoginEmpty() {
        return login.isEmpty();
    }

    @AssertFalse(message="{senha}")
    public boolean isPasswordEmpty() {
        return senha.isEmpty();
    }

    @AssertTrue(message="{login.erro}")
    public boolean isAuthenticated() {
        return repository.authenticate(login, senha) != null;
    }
}*/