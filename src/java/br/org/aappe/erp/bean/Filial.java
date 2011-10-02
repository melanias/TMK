package br.org.aappe.erp.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Jadson Ronald
 */
@Entity
@Table(name="filial")
public class Filial implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="id_filial_seq", sequenceName="id_filial_seq")
    @GeneratedValue(generator="id_filial_seq", strategy=GenerationType.AUTO)
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(name="dt_cadastro", nullable=false, updatable=false)
    private Date data;

    @Column(length=18, nullable=false)
    private String cnpj;

    @Column(length=200, nullable=false)
    private String nome;

    @Column(length=100)
    private String email;

    @Column(length=14)
    private String telefone;

    @Column(length=14)
    private String fax;

    @Embedded
    private Endereco endereco;

    /*@ManyToOne
    @JoinColumn(name="id_empresa", referencedColumnName="id", nullable=false, updatable=false)
    private Empresa empresa;*/

    @OneToMany(mappedBy="filial")
    private List<Setor> setores;

    @OneToMany(mappedBy="filial")
    private List<Funcionario> funcionarios;

    //getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj.trim(); }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome.trim(); }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email.trim().toLowerCase(); }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone.trim(); }

    public String getFax() { return fax; }
    public void setFax(String fax) { this.fax = fax.trim(); }

    public Endereco getEndereco() { return endereco; }
    public void setEndereco(Endereco endereco) { this.endereco = endereco; }

    /*public Empresa getEmpresa() { return empresa; }
    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }*/

    public List<Setor> getSetores() { return setores; }
    public void setSetores(List<Setor> setores) { this.setores = setores; }

    public List<Funcionario> getFuncionarios() { return funcionarios; }
    public void setFuncionarios(List<Funcionario> funcionarios) { this.funcionarios = funcionarios; }
}