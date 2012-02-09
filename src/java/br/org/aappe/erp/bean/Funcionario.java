package br.org.aappe.erp.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

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

    @Column(length=40)
    private String login;

    @Column(length=32)
    private String senha;

    @Transient
    private String checkPass;

    @Temporal(TemporalType.DATE)
    @Column(name="dt_admissao")
    private Date admissao;

    @Temporal(TemporalType.DATE)
    @Column(name="dt_demissao")
    private Date demissao;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="perfil", columnDefinition="smallint", nullable=false)
    private Role perfil;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="situacao", columnDefinition="smallint", nullable=false)
    private Status status = Status.ATIVO;

    @ManyToOne
    @JoinColumn(name="id_setor", referencedColumnName="id")
    private Setor setor;

    @ManyToOne
    @JoinColumn(name="id_unidade", referencedColumnName="id")
    private Unidade unidade;

    //getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login.trim().toLowerCase(); }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha.trim(); }

    public String getCheckPass() { return checkPass; }
    public void setCheckPass(String checkPass) { this.checkPass = checkPass.trim(); }

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

    public Unidade getUnidade() { return unidade; }
    public void setUnidade(Unidade unidade) { this.unidade = unidade; }
}