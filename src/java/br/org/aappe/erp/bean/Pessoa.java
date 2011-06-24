package br.org.aappe.erp.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Phelipe Melanias
 */
@MappedSuperclass
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Pessoa {

    @Column(unique=true, nullable=false)
    private long rg;

    @Column(length=14, unique=true, nullable=false)
    private String cpf;

    @Temporal(TemporalType.DATE)
    @Column(name="dt_nascimento")
    private Date nascimento;

    @Column(length=200, nullable=false)
    private String nome;

    @Column(length=100, nullable=false)
    private String email;

    @Column(length=14)
    private String celular;

    @Column(length=14)
    private String telefone;

    @Embedded
    private Endereco endereco;

    //getters e setters
    public long getRg() { return rg; }
    public void setRg(long rg) { this.rg = rg; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf.trim(); }

    public Date getNascimento() { return nascimento; }
    public void setNascimento(Date nascimento) { this.nascimento = nascimento; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome.replaceAll("\\d", "").trim(); }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email.trim().toLowerCase(); }

    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular.trim(); }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone.trim(); }

    public Endereco getEndereco() { return endereco; }
    public void setEndereco(Endereco endereco) { this.endereco = endereco; }
}