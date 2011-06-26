package br.org.aappe.erp.interceptor;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

import br.org.aappe.erp.controller.LoginController;
import br.org.aappe.erp.session.EmployeeSession;

/**
 * @author Phelipe Melanias
 */
@Intercepts
public class AuthInterceptor implements Interceptor {
    private final Result result;
    private final HttpServletRequest request;
    private final EmployeeSession employeeSession;

    public AuthInterceptor(Result result, HttpServletRequest request, EmployeeSession employeeSession) {
        this.result = result;
        this.request = request;
        this.employeeSession = employeeSession;
    }

    @Override
    public boolean accepts(ResourceMethod method) {
        return employeeSession == null || !employeeSession.isLogged();
    }

    @Override
    public void intercept(InterceptorStack stack, ResourceMethod method, Object o) throws InterceptionException {
        String uri = request.getRequestURI();

        if (uri.contains("/login")) {
            stack.next(method, o);
        } else {
            result.redirectTo(LoginController.class).frmLogin();
        }
    }
}