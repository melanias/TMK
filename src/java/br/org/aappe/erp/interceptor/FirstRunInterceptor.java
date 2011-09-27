package br.org.aappe.erp.interceptor;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

import br.org.aappe.erp.controller.UserController;
import br.org.aappe.erp.repository.UserRepository;

/**
 * @author Phelipe Melanias
 */
@Intercepts(before=AuthInterceptor.class)
public class FirstRunInterceptor implements Interceptor {
    private final Result result;
    private final HttpServletRequest request;
    private final UserRepository userRepository;

    public FirstRunInterceptor(Result result, HttpServletRequest request, UserRepository userRepository) {
        this.result = result;
        this.request = request;
        this.userRepository = userRepository;
    }

    @Override
    public boolean accepts(ResourceMethod method) {
        return !userRepository.hasAdmin();
    }

    @Override
    public void intercept(InterceptorStack stack, ResourceMethod method, Object o) throws InterceptionException {
        if (request.getRequestURI().contains("/primeiro-usuario")) {
            stack.next(method, o);
        } else {
            result.redirectTo(UserController.class).firstUser();
        }
    }
}