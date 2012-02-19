package br.org.aappe.erp.repository;

import java.util.List;

import br.org.aappe.erp.bean.Smtp;

/**
 * @author Thales Imbruglia
 */
public interface SmtpRepository {

    Smtp find(int id);

    Smtp merge(Smtp smtp);

    Smtp getActiveServer();

    void remove(Smtp smtp);

    void persist(Smtp smtp);

    List<Smtp> listAllById();

    boolean isUniqueServerActive(Smtp smtp);
}