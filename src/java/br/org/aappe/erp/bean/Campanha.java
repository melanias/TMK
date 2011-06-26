package br.org.aappe.erp.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Phelipe Melanias
 */
@Entity
@Table(name="campanha")
public class Campanha implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="id_campanha_seq", sequenceName="id_campanha_seq")
    @GeneratedValue(generator="id_campanha_seq", strategy=GenerationType.AUTO)
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(name="dt_cadastro", nullable=false, updatable=false)
    private Date data;

    @Column(length=200, nullable=false)
    private String nome;

    @Temporal(TemporalType.DATE)
    @Column(name="dt_inicial", nullable=false)
    private Date dataInicial;

    @Temporal(TemporalType.DATE)
    @Column(name="dt_final", nullable=false)
    private Date dataFinal;

    @OneToOne
    @JoinColumn(name="id_funcionario", referencedColumnName="id", nullable=false, updatable=false)
    private Funcionario funcionario;

    //getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome.trim(); }

    public Date getDataInicial() { return dataInicial; }
    public void setDataInicial(Date dataInicial) { this.dataInicial = dataInicial; }

    public Date getDataFinal() { return dataFinal; }
    public void setDataFinal(Date dataFinal) { this.dataFinal = dataFinal; }

    public Funcionario getFuncionario() { return funcionario; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }
}