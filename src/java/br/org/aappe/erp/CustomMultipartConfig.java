package br.org.aappe.erp;

import br.com.caelum.vraptor.interceptor.multipart.DefaultMultipartConfig;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class CustomMultipartConfig extends DefaultMultipartConfig {

    @Override
    public long getSizeLimit() {
        return 30 * 1024 * 1024;
    }
}