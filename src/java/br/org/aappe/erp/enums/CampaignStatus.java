package br.org.aappe.erp.enums;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author Thales Imbruglia
 */
public enum CampaignStatus implements Serializable {
    CONCLUÍDA("Concluída"), CANCELADA("Cancelada"), EMANDAMENTO("Em andamento"), PLANEJANDO("Planejando");
            
    private String status;

    private CampaignStatus(String status) {
        this.status = status;
    }

    //getters
    public String getStatus() { return status; }

    public static List<CampaignStatus> getAll() { return Arrays.asList(values()); }
}