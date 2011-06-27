package br.org.aappe.erp.repository;

import br.org.aappe.erp.bean.Mensagem;
import java.util.List;

/**
 * @author Phelipe Melanias
 */
public interface MensagemRepository {

    Mensagem find(int id);

    List<Mensagem> listAllById();

    void persist(Mensagem mensagem);

    Mensagem merge(Mensagem mensagem);
}