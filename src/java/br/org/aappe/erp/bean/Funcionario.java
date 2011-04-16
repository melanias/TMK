package br.org.aappe.erp.bean;

import java.io.Serializable;

//import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Thales Imbruglia
 */
//@Entity
@Table(name="funcionario")
public class Funcionario extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="id_funcionario_seq", sequenceName="id_funcionario_seq")
    @GeneratedValue(generator="id_funcionario_seq", strategy=GenerationType.AUTO)
    private int id;

    //getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
}