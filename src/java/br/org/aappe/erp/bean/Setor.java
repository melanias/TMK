package br.org.aappe.erp.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Phelipe Melanias
 */
@Entity
@Table(name="setor")
public class Setor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="id_setor_seq", sequenceName="id_setor_seq")
    @GeneratedValue(generator="id_setor_seq", strategy= GenerationType.AUTO)
    private int id;

    @Column(length=150, nullable=false)
    private String nome;

    @Column(length=20, nullable=false)
    private String sigla;

    @Column(length=100)
    private String email;

    @Column(length=14)
    private String telefone;

    @Column(length=14)
    private String fax;

    @OneToOne
    @JoinColumn(name="id_funcionario", referencedColumnName="id")
    private Funcionario responsavel;

    @ManyToOne
    @JoinColumn(name="id_filial", referencedColumnName="id", nullable=false)
    private Filial filial;

    @OneToMany(mappedBy="setor")
    private List<Funcionario> funcionarios;

    //getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome.trim(); }

    public String getSigla() { return sigla; }
    public void setSigla(String sigla) { this.sigla = sigla.trim().toUpperCase(); }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email.trim().toLowerCase(); }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone.trim(); }

    public String getFax() { return fax; }
    public void setFax(String fax) { this.fax = fax.trim(); }

    public Funcionario getResponsavel() { return responsavel; }
    public void setResponsavel(Funcionario responsavel) { this.responsavel = responsavel; }

    public Filial getFilial() { return filial; }
    public void setFilial(Filial filial) { this.filial = filial; }

    public List<Funcionario> getFuncionarios() { return funcionarios; }
    public void setFuncionarios(List<Funcionario> funcionarios) { this.funcionarios = funcionarios; }
}