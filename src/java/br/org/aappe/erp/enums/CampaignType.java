package br.org.aappe.erp.enums;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author Thales Imbruglia
 */
public enum CampaignType implements Serializable {
    TELEMARKETING("Telemarketing"), NEWSLETTER("Newsletter"), EMAIL("E-mail");

    private String type;

    private CampaignType(String type) {
        this.type = type;
    }

    //getters
    public String getType() { return type; }

    public static List<CampaignType> getAll() { return Arrays.asList(values()); }
}
