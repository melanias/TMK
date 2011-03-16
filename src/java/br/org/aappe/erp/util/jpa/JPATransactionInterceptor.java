package br.org.aappe.erp.util.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Lazy;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

@Intercepts @Lazy
public class JPATransactionInterceptor implements Interceptor {
    private final EntityManager manager;
    private final Validator validator;

    public JPATransactionInterceptor(EntityManager manager, Validator validator) {
        this.manager = manager;
        this.validator = validator;
    }

    public void intercept(InterceptorStack stack, ResourceMethod method, Object o) {
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            stack.next(method, o);

            if (!validator.hasErrors())
                transaction.commit();
        } finally {
            if (transaction != null && transaction.isActive())
                transaction.rollback();
        }
    }

    public boolean accepts(ResourceMethod method) {
        return method.getMethod().isAnnotationPresent(Transactional.class) ||
               method.getResource().getType().isAnnotationPresent(Transactional.class);
    }
}