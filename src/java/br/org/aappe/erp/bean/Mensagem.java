package br.org.aappe.erp.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.org.aappe.erp.enums.SendType;

/**
 * @author Phelipe Melanias
 */
@Entity
@Table(name="mensagem")
public class Mensagem implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="id_mensagem_seq", sequenceName="id_mensagem_seq")
    @GeneratedValue(generator="id_mensagem_seq", strategy= GenerationType.AUTO)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="dt_cadastro", nullable=false, updatable=false)
    private Date data;

    @Column(columnDefinition="text", nullable=false)
    private String texto;

    @Column(length=10, nullable=false)
    private SendType tipo;

    @ManyToOne
    @JoinColumn(name="id_funcionario", referencedColumnName="id", nullable=false, updatable=false)
    private Funcionario funcionario;

    //getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }

    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto.trim(); }

    public SendType getTipo() { return tipo; }
    public void setTipo(SendType tipo) { this.tipo = tipo; }

    public Funcionario getFuncionario() { return funcionario; }
    public void setFuncionario(Funcionario funcionario) {this.funcionario = funcionario; }
}