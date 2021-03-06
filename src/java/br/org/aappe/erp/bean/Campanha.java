package br.org.aappe.erp.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import br.org.aappe.erp.enums.CampaignType;
import br.org.aappe.erp.enums.CampaignStatus;

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

    @Enumerated(EnumType.ORDINAL)
    @Column(name="type", columnDefinition="smallint", nullable=false)
    private CampaignType type;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="status", columnDefinition="smallint", nullable=false)
    private CampaignStatus status = CampaignStatus.PLANEJANDO;

    @Column(columnDefinition="text")
    private String objetivo;

    @Column(nullable=false, precision=12, scale=2)
    private BigDecimal custoPrevisto;

    @Column(nullable=false, precision=12, scale=2)
    private BigDecimal custoReal;

    @Column(nullable=false, precision=12, scale=2)
    private BigDecimal receitaEsperada;

    @Column(nullable=false, precision=12, scale=2)
    private BigDecimal receitaReal;

    @Temporal(TemporalType.DATE)
    @Column(name="dt_inicial", nullable=false)
    private Date dataInicial;

    @Temporal(TemporalType.DATE)
    @Column(name="dt_final", nullable=false)
    private Date dataFinal;

    @OneToOne
    @JoinColumn(name="id_funcionario", referencedColumnName="id", nullable=false, updatable=false)
    private Funcionario funcionario;

    @OneToMany(mappedBy="campanha")
    private List<Doacao> doacoes;

    //getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome.trim(); }

    public CampaignType getType() { return type; }
    public void setType(CampaignType type) { this.type = type; }

    public CampaignStatus getStatus() { return status; }
    public void setStatus(CampaignStatus status) { this.status = status; }

    public String getObjetivo() { return objetivo; }
    public void setObjetivo(String objetivo) { this.objetivo = objetivo; }

    public BigDecimal getCustoPrevisto() { return custoPrevisto; }
    public void setCustoPrevisto(BigDecimal custoPrevisto) { this.custoPrevisto = custoPrevisto; }

    public BigDecimal getCustoReal() { return custoReal; }
    public void setCustoReal(BigDecimal custoReal) { this.custoReal = custoReal; }

    public BigDecimal getReceitaEsperada() { return receitaEsperada; }
    public void setReceitaEsperada(BigDecimal receitaEsperada) { this.receitaEsperada = receitaEsperada; }

    public BigDecimal getReceitaReal() { return receitaReal; }
    public void setReceitaReal(BigDecimal receitaReal) { this.receitaReal = receitaReal; }

    public Date getDataInicial() { return dataInicial; }
    public void setDataInicial(Date dataInicial) { this.dataInicial = dataInicial; }

    public Date getDataFinal() { return dataFinal; }
    public void setDataFinal(Date dataFinal) { this.dataFinal = dataFinal; }

    public Funcionario getFuncionario() { return funcionario; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }

    public List<Doacao> getDoacoes() { return doacoes; }
    public void setDoacoes(List<Doacao> doacoes) { this.doacoes = doacoes; }
}