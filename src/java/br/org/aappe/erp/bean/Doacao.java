package br.org.aappe.erp.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

/**
 * @author Phelipe Melanias
 */
@Entity
@Table(name="doacao")
public class Doacao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="id_doacao_seq", sequenceName="id_doacao_seq")
    @GeneratedValue(generator="id_doacao_seq", strategy= GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name="dt_ligacao", nullable=false, updatable=true)
    private Date ligacao;

    @Temporal(TemporalType.DATE)
    @Column(name="dt_recebimento", nullable=false, updatable=true)
    private Date recebimento;

    @Column(nullable=false, precision=12, scale=2)
    private BigDecimal valor;

    @Column(nullable=false)
    private boolean paga;

    @Column(columnDefinition="text")
    private String descricao;

    @OneToOne
    @JoinColumn(name="id_doador", referencedColumnName="id", nullable=false)
    private Doador doador;

    @OneToOne
    @JoinColumn(name="id_funcionario", referencedColumnName="id", nullable=false, updatable=false)
    private Funcionario funcionario;

    @OneToOne
    @JoinColumn(name="id_representante", referencedColumnName="id", nullable=false)
    private Funcionario representante;

    @OneToOne
    @JoinColumn(name="id_campanha", referencedColumnName="id")
    private Campanha campanha;

    //getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Date getLigacao() { return ligacao; }
    public void setLigacao(Date ligacao) { this.ligacao = ligacao; }

    public Date getRecebimento() { return recebimento; }
    public void setRecebimento(Date recebimento) { this.recebimento = recebimento; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public boolean isPaga() { return paga; }
    public void setPaga(boolean paga) { this.paga = paga; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao.trim(); }

    public Doador getDoador() { return doador; }
    public void setDoador(Doador doador) { this.doador = doador; }

    public Funcionario getFuncionario() { return funcionario; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }

    public Funcionario getRepresentante() { return representante; }
    public void setRepresentante(Funcionario representante) { this.representante = representante; }

    public Campanha getCampanha() { return campanha;}
    public void setCampanha (Campanha campanha) { this.campanha = campanha; }
}