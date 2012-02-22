package br.org.aappe.erp.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;

import br.org.aappe.erp.bean.Doacao;
import br.org.aappe.erp.repository.DoacaoRepository;

/**
 * @author Phelipe Melanias
 */
@Component
public class DoacaoDAO extends DAO<Doacao> implements DoacaoRepository {

    public DoacaoDAO(EntityManager manager) {
        super(manager);
    }

    @Override
    public List<Integer> getYears() {
        return manager.createQuery("SELECT DISTINCT(EXTRACT(YEAR FROM ligacao)) AS years FROM Doacao ORDER BY years").getResultList();
    }

    @Override
    public List<Integer> getMonthsOfTheYear(int year) {
        return manager.createQuery("SELECT DISTINCT(EXTRACT(MONTH FROM ligacao)) AS months FROM Doacao WHERE EXTRACT(YEAR FROM ligacao) = ? ORDER BY months")
                      .setParameter(1, year)
                      .getResultList();
    }

    @Override
    public List<Doacao> getDonationsByPeriod(int year, String months) {
        return months.isEmpty() ? manager.createQuery("FROM Doacao WHERE DATE_PART('YEAR', ligacao) = ? ORDER BY ligacao")
                                         .setParameter(1, year)
                                         .getResultList()
                                : manager.createQuery("FROM Doacao WHERE DATE_PART('MONTH', ligacao) IN ("+ months +") AND DATE_PART('YEAR', ligacao) = ? ORDER BY ligacao")
                                         .setParameter(1, year)
                                         .getResultList();
    }
}