package br.org.aappe.erp.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Jadson Ronald
 */
//@Entity
@Table(name="filial")
public class Filial implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="id_filial_seq", sequenceName="id_filial_seq")
    @GeneratedValue(generator="id_filial_seq", strategy=GenerationType.AUTO)
    private int id;

    @Column(length=200, nullable=false)
    private String nome;

    @ManyToOne
    @JoinColumn(name="id_empresa", referencedColumnName="id", nullable=false, updatable=false)
    private Empresa empresa;

    //getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome.replaceAll("\\d", "").trim(); }

    public Empresa getEmpresa() { return empresa; }
    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }
}