/*package br.org.aappe.erp.dao;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;

import br.org.aappe.erp.bean.Empresa;
import br.org.aappe.erp.repository.EmpresaRepository;

/**
 * @author Phelipe Melanias
 */
/*@Component
public class EmpresaDAO extends DAO<Empresa> implements EmpresaRepository {

    public EmpresaDAO(EntityManager manager) {
        super(manager);
    }

    @Override
    public boolean isUniqueCnpj(Empresa empresa) {
        return empresa.getId() > 0 ? manager.createQuery("SELECT empresa FROM Empresa empresa WHERE empresa.id <> ? AND empresa.cnpj = ?")
                                            .setParameter(1, empresa.getId())
                                            .setParameter(2, empresa.getCnpj())
                                            .getResultList().isEmpty()
                                   : manager.createQuery("SELECT empresa FROM Empresa empresa WHERE empresa.cnpj = ?")
                                            .setParameter(1, empresa.getCnpj())
                                            .getResultList().isEmpty();
    }

    @Override
    public boolean isUniqueCompanyName(Empresa empresa) {
        return empresa.getId() > 0 ? manager.createQuery("SELECT empresa FROM Empresa empresa WHERE empresa.id <> ? AND lower(empresa.razaoSocial) = ?")
                                         .setParameter(1, empresa.getId())
                                         .setParameter(2, empresa.getRazaoSocial().toLowerCase())
                                         .getResultList().isEmpty()
                                : manager.createQuery("SELECT empresa FROM Empresa empresa WHERE lower(empresa.razaoSocial) = ?")
                                         .setParameter(1, empresa.getRazaoSocial().toLowerCase())
                                         .getResultList().isEmpty();
    }

    @Override
    public boolean isUniqueMail(Empresa empresa) {
        return empresa.getId() > 0 ? manager.createQuery("SELECT empresa FROM Empresa empresa WHERE empresa.id <> ? AND empresa.email = ?")
                                         .setParameter(1, empresa.getId())
                                         .setParameter(2, empresa.getEmail())
                                         .getResultList().isEmpty()
                                : manager.createQuery("SELECT empresa FROM Empresa empresa WHERE empresa.email = ?")
                                         .setParameter(1, empresa.getEmail())
                                         .getResultList().isEmpty();
    }
}*/