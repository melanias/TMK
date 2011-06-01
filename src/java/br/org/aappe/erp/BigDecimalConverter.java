package br.org.aappe.erp;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;
import br.com.caelum.vraptor.converter.ConversionError;
import br.com.caelum.vraptor.ioc.ApplicationScoped;

@Convert(BigDecimal.class)
@ApplicationScoped
public class BigDecimalConverter implements Converter<BigDecimal> {

    @Override
    public BigDecimal convert(String value, Class<? extends BigDecimal> type, ResourceBundle bundle) {
        if (value == null || value.trim().isEmpty())
            return null;

        try {
            return new BigDecimal(value.replace(".", "").replace(",", "."));
        } catch (NumberFormatException e) {
            throw new ConversionError(MessageFormat.format(bundle.getString("is_not_a_valid_number"), value));
        }
    }
}