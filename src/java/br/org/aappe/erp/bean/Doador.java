package br.org.aappe.erp.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

    @Column(length=18, unique=true)
    private String cnpj; //Caso o doador seja uma pessoa jurídica

    @Enumerated(EnumType.ORDINAL)
    @Column(name="tipo", columnDefinition="smallint", nullable=false)
    private DonorType tipo = DonorType.FISICA; //0 = Pessoa Física, 1 = Pessoa Jurídica

    @Enumerated(EnumType.ORDINAL)
    @Column(name="situacao", columnDefinition="smallint", nullable=false)
    private DonorStatus status = DonorStatus.ATIVO; //0 = Inativo, 1 = Ativo, 2 = Novo, 3 = Fidelizado

    @Column(columnDefinition="text")
    private String observacao;

    @OneToMany(mappedBy="doador")
    private List<Doacao> doacoes;

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
}