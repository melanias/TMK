package br.org.aappe.erp.bean;

import java.io.Serializable;

//import javax.persistence.Entity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Thales Imbruglia
 */
//@Entity
@Table(name="doador")
public class Doador extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="id_doador_seq", sequenceName="id_doador_seq")
    @GeneratedValue(generator="id_doador_seq", strategy=GenerationType.AUTO)
    private int id;

    @Column(name="dt_cadastro", nullable=false, updatable=false)
    private Date dataDeCadastro;

    @Column(name="dt_nascimento", nullable=false)
    private Date dataDeNascimento;

    @Column(length=255, nullable=false)
    private String endereco;

    @Column(length=10, nullable=false)
    private int numero;

    @Column(length=200, nullable=false)
    private String complemento;

    @Column(name="ponto_referencia", length=200, nullable=false)
    private String pontoDeReferencia;

    /*Phelipe... Vai usar aquele dados que tu tem... o cara digita o cep e aparece o endereço, cidade, estado? Precisa colocar aqui como atributo?

    @Column(length=150, nullable=false)
    private String bairro;
    
    private int cep;

    private String cidade;

    private String estado;
    */

    //Acharia interessante nao deixar telefone residencial como obrigatorio, já hoje em dia nem todo mundo tem. No caso algum dos 3 é que deve ser preenchido. o que acham?
    // melhor colocar todas esses atributos em Pessoa, nao?
    @Column(length=10)
    private int telefoneResidencial;

    @Column(length=10)
    private int telefoneComercial;

    @Column(length=10)
    private int celular;

    @Column(length=100)
    private String email;

    //private String campanha;
    
    //private String operadora;

    //getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getDataDeCadastro() { return dataDeCadastro; }
    public void setDataDeCadastro(Date dataDeCadastro) { this.dataDeCadastro = dataDeCadastro; }

    public Date getDataDeNascimento() { return dataDeNascimento; }
    public void setDataDeNascimento(Date dataDeNascimento) { this.dataDeNascimento = dataDeNascimento; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public String getComplemento() { return complemento; }
    public void setComplemento(String complemento) { this.complemento = complemento; }

    public String getPontoDeReferencia() { return pontoDeReferencia; }
    public void setPontoDeReferencia(String pontoDeReferencia) { this.pontoDeReferencia = pontoDeReferencia; }

    public int getTelefoneResidencial() { return telefoneResidencial; }
    public void setTelefoneResidencial(int telefoneResidencial) { this.telefoneResidencial = telefoneResidencial; }

    public int getTelefoneComercial() { return telefoneComercial; }
    public void setTelefoneComercial(int telefoneComercial) { this.telefoneComercial = telefoneComercial; }

    public int getCelular() { return celular; }
    public void setCelular(int celular) { this.celular = celular; }

}