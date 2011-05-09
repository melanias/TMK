/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.aappe.erp.bean;

//import javax.persistence.Entity;

import java.io.Serializable;
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
@Table(name="campanha")
public class Campanha implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="id_campanha_seq", sequenceName="id_campanha_seq")
    @GeneratedValue(generator="id_campanha_seq", strategy=GenerationType.AUTO)
    private int id;

    @Column(length=255, nullable=false)
    private String campanha;

    /* Aqui fica como? DATA ínicial + Final? Era legal que colocasse algo informando duração os dias de duração. É regra de negócio? Fiz como diz abaixo.. dps corrigi! =P*/
    @Column(name="dt_inicial", nullable=false)
    private Date dataInicial;

    @Column(name="dt_final", nullable=false)
    private Date dataFinal;

    //É máximo é 255 ? Pode colocar mais? =p
    @Column(length=255, nullable=false)
    private String script;

    //getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCampanha() { return campanha; }
    public void setCampanha(String campanha) { this.campanha = campanha; }

    public Date getDataInicial() { return dataInicial; }
    public void setDataInicial(Date dataInicial) { this.dataInicial = dataInicial; }

    public Date getDataFinal() { return dataFinal; }
    public void setDataFinal(Date dataFinal) { this.dataFinal = dataFinal; }

    public String getScript() { return script; }
    public void setScript(String script) { this.script = script; }

    

}