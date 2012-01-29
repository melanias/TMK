package br.org.aappe.erp.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.org.aappe.erp.enums.Status;

/**
 * @author Thales Imbruglia
 */
@Entity
@Table(name="smtp")
public class Smtp implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="id_smtp_seq", sequenceName="id_smtp_seq")
    @GeneratedValue(generator="id_smtp_seq", strategy=GenerationType.AUTO)
    private int id;

    @Column(length=100, nullable=false)
    private String hostName;

    @Column(nullable=false)
    private int port;

    @Column(length=150, nullable=false)
    private String account;

    @Column(length=50, nullable=false)
    private String password;

    @Column(nullable=false)
    private boolean tls;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="situacao", columnDefinition="smallint", nullable=false)
    private Status status = Status.ATIVO; //0 = Inativo, 1 = Ativo

    //getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getHostName() { return hostName; }
    public void setHostName(String hostName) { this.hostName = hostName.trim(); }

    public int getPort() { return port; }
    public void setPort(int port) { this.port = port; }

    public String getAccount() { return account; }
    public void setAccount(String account) { this.account = account.trim(); }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password.trim(); }

    public boolean isTls() { return tls; }
    public void setTls(boolean tls) { this.tls = tls; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}