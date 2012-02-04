package br.org.aappe.erp.interceptor;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
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
@Intercepts
public class AuthorizedInterceptor implements Interceptor {
    private final Result result;
    private final HttpServletRequest request;
    private final EmployeeSession employeeSession;

    public AuthorizedInterceptor(Result result, HttpServletRequest request, EmployeeSession employeeSession) {
        this.result = result;
        this.request = request;
        this.employeeSession = employeeSession;
    }

    @Override
    public boolean accepts(ResourceMethod method) {
        return method.containsAnnotation(Authorized.class) ||
               method.getResource().getType().isAnnotationPresent(Authorized.class) || request.getRequestURI().contains("/admin");
    }

    @Override
    public void intercept(InterceptorStack stack, ResourceMethod method, Object o) throws InterceptionException {
        String uri   = request.getRequestURI();
        Authorized a = whereIsTheAnnotation(method);

        if (isAuthorized(a) || isAdministrator()) {
            stack.next(method, o);
        } else {
            if (uri.contains("/add") || uri.contains("/edit") || uri.contains("/view"))
                result.redirectTo(IndexController.class).unauthorizedAccess();
            else
                result.redirectTo(IndexController.class).index();
        }
    }

    private boolean isAuthorized(Authorized a) {
        if (a != null) {
            for (Role role : a.value()) {
                if (role.equals(employeeSession.getPerfil()))
                    return true;
            }
        }

        return false;
    }

    private Authorized whereIsTheAnnotation(ResourceMethod method) {
        return method.containsAnnotation(Authorized.class) ? method.getMethod().getAnnotation(Authorized.class)
                                                           : method.getResource().getType().getAnnotation(Authorized.class);
    }

    private boolean isAdministrator() {
        return employeeSession.getPerfil().equals(Role.ADMINISTRADOR);
    }
}