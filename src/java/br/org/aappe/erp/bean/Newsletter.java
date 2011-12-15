package br.org.aappe.erp.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Thales imbruglia
 */
@Entity
@Table(name="newsletter")
public class Newsletter implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="id_newsletter_seq", sequenceName="id_newsletter_seq")
    @GeneratedValue(generator="id_newsletter_seq", strategy=GenerationType.AUTO)
    private int id;
    
    @Column(length=100, nullable=false)
    private String hostName;
    
    @Column(length=3, nullable=false)
    private int smtpPort;
    
    @Column(length=100, nullable=false)
    private String account;
    
    @Column(length=32, nullable=false)
    private String password;
    
    //private boolean tls = true; //Isso fica assim mesmo ou passa no metodo?

    //getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getHostName() { return hostName; }
    public void setHostName(String hostName) { this.hostName = hostName; }
    
    public int getSmtpPort() { return smtpPort; }
    public void setSmtpPort(int smtpPort) { this.smtpPort = smtpPort; }

    public String getAccount() { return account; }
    public void setAccount(String user) { this.account = account; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password.trim(); }

    //public void setTls(boolean tls) { this.tls = tls; }
    //public boolean getTls (Boolean tls) {return tls;}  

}
