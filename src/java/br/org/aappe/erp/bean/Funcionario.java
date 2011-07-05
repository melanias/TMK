package br.org.aappe.erp.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.org.aappe.erp.enums.Role;
import br.org.aappe.erp.enums.Status;

/**
 * @author Thales Imbruglia
 */
@Entity
@Table(name="funcionario")
public class Funcionario extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="id_funcionario_seq", sequenceName="id_funcionario_seq")
    @GeneratedValue(generator="id_funcionario_seq", strategy=GenerationType.AUTO)
    private int id;

    @Column(length=32, nullable=false)
    private String senha;

    @Temporal(TemporalType.DATE)
    @Column(name="dt_admissao", nullable=false)
    private Date admissao;

    @Temporal(TemporalType.DATE)
    @Column(name="dt_demissao")
    private Date demissao;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="perfil", columnDefinition="smallint", nullable=false)
    private Role perfil; //0 = Estagiário(a), 1 = Representante, 2 = Operador(a), 3 = Gerente

    @Enumerated(EnumType.ORDINAL)
    @Column(name="situacao", columnDefinition="smallint", nullable=false)
    private Status status = Status.ATIVO; //0 = Inativo, 1 = Ativo

    @ManyToOne
    @JoinColumn(name="id_setor", referencedColumnName="id", nullable=false)
    private Setor setor;

    @ManyToOne
    @JoinColumn(name="id_filial", referencedColumnName="id", nullable=false)
    private Filial filial;

    @ManyToOne
    @JoinColumn(name="id_empresa", referencedColumnName="id", nullable=false, updatable=false)
    private Empresa empresa;

    //getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha.trim(); }

    public Date getAdmissao() { return admissao; }
    public void setAdmissao(Date admissao) { this.admissao = admissao; }

    public Date getDemissao() { return demissao; }
    public void setDemissao(Date demissao) { this.demissao = demissao; }

    public Role getPerfil() { return perfil; }
    public void setPerfil(Role perfil) { this.perfil = perfil; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Setor getSetor() { return setor; }
    public void setSetor(Setor setor) { this.setor = setor; }

    public Filial getFilial() { return filial; }
    public void setFilial(Filial filial) { this.filial = filial; }

    public Empresa getEmpresa() { return empresa; }
    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }
}