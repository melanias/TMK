package br.org.aappe.erp.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Phelipe Melanias
 */
@Embeddable
public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(length=2)
    private String uf;

    @Column(length=9)
    private String cep;

    private int numero;

    @Column(length=100)
    private String pais;

    @Column(length=100)
    private String bairro;

    @Column(length=100)
    private String cidade;

    @Column(length=200)
    private String logradouro;

    @Column(length=150)
    private String complemento;

    //getters e setters
    public String getUf() { return uf; }
    public void setUf(String uf) { this.uf = uf.trim(); }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep.trim(); }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais.trim(); }

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro.trim(); }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade.trim(); }

    public String getLogradouro() { return logradouro; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro.trim(); }

    public String getComplemento() { return complemento; }
    public void setComplemento(String complemento) { this.complemento = complemento.trim(); }
}