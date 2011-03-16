package br.org.aappe.erp.util.jpa;

import br.com.caelum.vraptor.ComponentRegistry;
import br.com.caelum.vraptor.ioc.spring.SpringProvider;
import br.com.caelum.vraptor.util.jpa.EntityManagerCreator;
import br.com.caelum.vraptor.util.jpa.EntityManagerFactoryCreator;

public class JPACustomProvider extends SpringProvider {
    
    @Override
    protected void registerCustomComponents(ComponentRegistry registry) {
        registry.register(EntityManagerCreator.class, EntityManagerCreator.class);
        registry.register(EntityManagerFactoryCreator.class, EntityManagerFactoryCreator.class);
    }
}