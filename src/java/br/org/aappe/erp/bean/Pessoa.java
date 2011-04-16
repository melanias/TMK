package br.org.aappe.erp.bean;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

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

    @Column(length=200, nullable=false)
    private String nome;

    //getters e setters
    public long getRg() { return rg; }
    public void setRg(long rg) { this.rg = rg; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome.replaceAll("\\d", "").trim(); }    
}