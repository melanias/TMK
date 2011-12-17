package br.org.aappe.erp.repository;

import java.util.List;

import br.org.aappe.erp.bean.Smtp;

/**
 * @author Thales Imbruglia
 */
public interface SmtpRepository {

    Smtp find(int id);

    Smtp getActiveServer();

    List<Smtp> listAllById();

    void persist(Smtp newsletter);

    Smtp merge(Smtp newsletter);

    boolean isUniqueServerActive(Smtp smtp);
}