package br.org.aappe.erp;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.RequestInfo;
import br.com.caelum.vraptor.http.route.ResourceNotFoundException;
import br.com.caelum.vraptor.http.route.Router;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.resource.DefaultResourceNotFoundHandler;
import br.com.caelum.vraptor.resource.HttpMethod;

import br.org.aappe.erp.controller.IndexController;

@Component
public class CustomResourceNotFoundHandler extends DefaultResourceNotFoundHandler {
    private final Router router;
    private final Result result;

    public CustomResourceNotFoundHandler(Router router, Result result) {
        this.router = router;
        this.result = result;
    }

    @Override
    public void couldntFind(RequestInfo request) {
        String uri = request.getRequestedUri();

        try {
            if (uri.endsWith("/")) {
                tryMovePermanentlyTo(request, uri.substring(0, uri.length()-1));
            } else {
                tryMovePermanentlyTo(request, uri + "/");
            }
        } catch (ResourceNotFoundException e) {
            //TODO: Criar uma página padrão para exibir o erro HTTP Status 404
            result.forwardTo(IndexController.class).index();
        }
    }

    private void tryMovePermanentlyTo(RequestInfo request, String uri) {
        router.parse(uri, HttpMethod.of(request.getRequest()), request.getRequest());
        result.permanentlyRedirectTo(uri);
    }
}