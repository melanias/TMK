package br.org.aappe.erp.bean;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Phelipe Melanias
 */
@Entity
@Cacheable
@Table(name="root")
public class Root implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="id_root_seq", sequenceName="id_root_seq")
    @GeneratedValue(generator="id_root_seq", strategy=GenerationType.AUTO)
    private int id;

    @Column(length=14, nullable=false)
    private String cpf;

    @Column(length=200, nullable=false)
    private String nome;

    @Column(length=32, nullable=false)
    private String senha;

    @Column(length=100, nullable=false)
    private String email;

    @Column(length=14)
    private String celular;

    @Column(length=14)
    private String telefone;

    //getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome.replaceAll("\\d", "").trim(); }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha.trim(); }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email.trim().toLowerCase(); }

    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular.trim(); }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone.trim(); }
}