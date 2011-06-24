package br.org.aappe.erp.repository;

import br.org.aappe.erp.bean.Filial;
import java.util.List;

/**
 * @author Phelipe Melanias
 */
public interface FilialRepository {

    Filial find(int id);

    List<Filial> listAllById();

    void persist(Filial filial);

    Filial merge(Filial filial);

    boolean isUniqueCnpj(Filial filial);
    boolean isUniqueMail(Filial filial);
    boolean isUniqueCompanyName(Filial filial);
}