package br.org.aappe.erp.interceptor;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Lazy;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;


import br.org.aappe.erp.annotations.Authorized;
import br.org.aappe.erp.controller.IndexController;
import br.org.aappe.erp.enums.Role;
import br.org.aappe.erp.session.EmployeeSession;

/**
 * @author Phelipe Melanias
 */
@Intercepts @Lazy
public class AuthorizedInterceptor implements Interceptor {
    private final Result result;
    private final EmployeeSession employeeSession;

    public AuthorizedInterceptor(Result result, EmployeeSession employeeSession) {
        this.result = result;
        this.employeeSession = employeeSession;
    }

    @Override
    public boolean accepts(ResourceMethod method) {
        return method.containsAnnotation(Authorized.class) ||
               method.getResource().getType().isAnnotationPresent(Authorized.class);
    }

    @Override
    public void intercept(InterceptorStack stack, ResourceMethod method, Object o) throws InterceptionException {
        Authorized a = whereIsTheAnnotation(method);

        if (isAuthorized(a))
            stack.next(method, o);
        else
            result.redirectTo(IndexController.class).index();
    }

    private boolean isAuthorized(Authorized a) {
        if (a != null) {
            for (Role role : a.value()) {
                if (role.equals(employeeSession.getPerfil()) || employeeSession.getPerfil().equals(Role.ADMINISTRADOR))
                    return true;
            }
        }

        return false;
    }

    private Authorized whereIsTheAnnotation(ResourceMethod method) {
        return method.containsAnnotation(Authorized.class) ? method.getMethod().getAnnotation(Authorized.class)
                                                           : method.getResource().getType().getAnnotation(Authorized.class);
    }
}