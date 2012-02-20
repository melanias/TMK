package br.org.aappe.erp.bean;

import java.io.Serializable;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import br.org.aappe.erp.enums.DonorStatus;
import br.org.aappe.erp.enums.DonorType;

/**
 * @author Thales Imbruglia
 */
@Entity
@Table(name="doador")
public class Doador extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="id_doador_seq", sequenceName="id_doador_seq")
    @GeneratedValue(generator="id_doador_seq", strategy=GenerationType.AUTO)
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(name="dt_cadastro", nullable=false, updatable=false)
    private Date data;

    @Column(length=18)
    private String cnpj;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="tipo", columnDefinition="smallint", nullable=false)
    private DonorType tipo = DonorType.FISICA;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="situacao", columnDefinition="smallint", nullable=false)
    private DonorStatus status = DonorStatus.ATIVO;

    @Column(columnDefinition="text")
    private String observacao;

    @OneToMany(mappedBy="doador")
    private List<Doacao> doacoes;

    @ManyToOne
    @JoinColumn(name="id_unidade", referencedColumnName="id", nullable=false)
    private Unidade unidade;

    //getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj.trim(); }

    public DonorType getTipo() { return tipo; }
    public void setTipo(DonorType tipo) { this.tipo = tipo; }

    public DonorStatus getStatus() { return status; }
    public void setStatus(DonorStatus status) { this.status = status; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao.trim(); }

    public List<Doacao> getDoacoes() { return doacoes; }
    public void setDoacoes(List<Doacao> doacoes) { this.doacoes = doacoes; }

    public Unidade getUnidade() { return unidade; }
    public void setUnidade(Unidade unidade) { this.unidade = unidade; }

    public BigDecimal getTotalValueOfDonations() {
        BigDecimal total = new BigDecimal(BigInteger.ZERO);

        for (Doacao doacao : doacoes)
            total = total.add(doacao.getValor());

        return total;
    }
}