package br.org.aappe.erp;

import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.interceptor.TypeNameExtractor;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.serialization.ProxyInitializer;
import br.com.caelum.vraptor.serialization.xstream.XStreamJSONSerialization;
import br.com.caelum.vraptor.validator.Message;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * @author Phelipe Melanias
 */
@Component
public class CustomJSONSerialization extends XStreamJSONSerialization {

    public CustomJSONSerialization(HttpServletResponse response, TypeNameExtractor extractor, ProxyInitializer initializer) {
        super(response, extractor, initializer);
    }

    @Override
    protected XStream getXStream() {
        XStream stream = super.getXStream();

        stream.registerConverter(new Converter() {
            @Override
            public boolean canConvert(Class type) {
                return Message.class.isAssignableFrom(type);
            }

            @Override
            public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext uc) {
                return null;
            }

            @Override
            public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
                Message m = (Message) source;
                writer.startNode("message");
                writer.setValue(m.getMessage());
                writer.endNode();

                writer.startNode("category");
                writer.setValue(m.getCategory());
                writer.endNode();
            }
        });

        return stream;
    }
}