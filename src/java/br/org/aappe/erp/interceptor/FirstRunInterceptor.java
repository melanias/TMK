package br.org.aappe.erp.interceptor;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

import br.org.aappe.erp.controller.SetupController;
import br.org.aappe.erp.repository.FuncionarioRepository;

/**
 * @author Phelipe Melanias
 */
@Intercepts(before={AuthInterceptor.class, AuthorizedInterceptor.class})
public class FirstRunInterceptor implements Interceptor {
    private final Result result;
    private final HttpServletRequest request;
    private final FuncionarioRepository repository;

    public FirstRunInterceptor(Result result, HttpServletRequest request, FuncionarioRepository repository) {
        this.result = result;
        this.request = request;
        this.repository = repository;
    }

    @Override
    public boolean accepts(ResourceMethod method) {
        return !repository.hasEmployee();
    }

    @Override
    public void intercept(InterceptorStack stack, ResourceMethod method, Object o) throws InterceptionException {
        if (request.getRequestURI().contains("/setup")) {
            stack.next(method, o);
        } else {
            result.redirectTo(SetupController.class).setup();
        }
    }
}