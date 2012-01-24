package br.org.aappe.erp.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import br.org.aappe.erp.enums.Status;

/**
 * @author Phelipe Melanias
 */
@Entity
@Table(name="unidade")
public class Unidade implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="id_unidade_seq", sequenceName="id_unidade_seq")
    @GeneratedValue(generator="id_unidade_seq", strategy=GenerationType.AUTO)
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(name="dt_cadastro", nullable=false, updatable=false)
    private Date data;

    @Column(length=18, nullable=false)
    private String cnpj;

    @Column(length=200, nullable=false)
    private String razaoSocial;

    @Column(length=200)
    private String nomeFantasia;

    @Column(length=200)
    private String site;

    @Column(length=100)
    private String email;

    @Column(length=14)
    private String telefone;

    @Column(length=14)
    private String fax;

    @Embedded
    private Endereco endereco;

    @Column(nullable=false)
    private boolean matriz;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="situacao", columnDefinition="smallint", nullable=false)
    private Status status = Status.ATIVO;

    @OrderBy
    @OneToMany(mappedBy="unidade")
    private List<Setor> setores;

    @OneToMany(mappedBy="unidade", cascade=CascadeType.ALL)
    private List<Funcionario> funcionarios;

    //getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj.trim(); }

    public String getRazaoSocial() { return razaoSocial; }
    public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial.trim(); }

    public String getNomeFantasia() { return nomeFantasia; }
    public void setNomeFantasia(String nomeFantasia) { this.nomeFantasia = nomeFantasia.trim(); }

    public String getSite() { return site; }
    public void setSite(String site) { this.site = site.trim().toLowerCase(); }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email.trim().toLowerCase(); }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone.trim(); }

    public String getFax() { return fax; }
    public void setFax(String fax) { this.fax = fax.trim(); }

    public Endereco getEndereco() { return endereco; }
    public void setEndereco(Endereco endereco) { this.endereco = endereco; }

    public boolean isMatriz() { return matriz; }
    public void setMatriz(boolean matriz) { this.matriz = matriz; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public List<Setor> getSetores() { return setores; }
    public void setSetores(List<Setor> setores) { this.setores = setores; }

    public List<Funcionario> getFuncionarios() { return funcionarios; }
    public void setFuncionarios(List<Funcionario> funcionarios) { this.funcionarios = funcionarios; }
}