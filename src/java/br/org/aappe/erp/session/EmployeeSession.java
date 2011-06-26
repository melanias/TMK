package br.org.aappe.erp.session;

import java.io.Serializable;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.org.aappe.erp.bean.Funcionario;
import br.org.aappe.erp.enums.Role;
import br.org.aappe.erp.enums.Status;
import br.org.aappe.erp.util.Utilities;

/**
 * @author Phelipe Melanias
 */
@Component
@SessionScoped
public class EmployeeSession implements Serializable {
    private static final long serialVersionUID = 1L;

    private Funcionario funcionario;

    public void login(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void logout() {
        this.funcionario = null;
    }

    public boolean isLogged() {
        return funcionario != null;
    }

    public int getId() {
        return funcionario.getId();
    }

    public String getCpf() {
        return funcionario.getCpf();
    }

    public String getEmail() {
        return funcionario.getEmail();
    }

    public String getNome() {
        return funcionario.getNome();
    }

    public Role getPerfil() {
        return funcionario.getPerfil();
    }

    public Status getStatus() {
        return funcionario.getStatus();
    }
}