/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.aappe.erp.dao;

import br.org.aappe.erp.bean.Campanha;
import br.org.aappe.erp.repository.CampanhaRepository;
import javax.persistence.EntityManager;

/**
 *
 * @author imbr8
 */
public class NewsletterDAO extends DAO<Campanha> implements CampanhaRepository {

    public NewsletterDAO(EntityManager manager) {
        super(manager);
    }
    
    
}
