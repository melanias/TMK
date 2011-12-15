package br.org.aappe.erp.repository;

import java.util.List;

import br.org.aappe.erp.bean.Newsletter;

/**
 * @author Thales Imbruglia
 */
public interface NewsletterRepository {
    
    Newsletter find(int id);

    List<Newsletter> listAllById();

    void persist(Newsletter newsletter);

    Newsletter merge(Newsletter newsletter);
    
}
