package com.tiga.recview.model;

import java.util.List;

public class Stok_Request {

    private String AgentId;
    private Long CreatedDate;
    private String InvoiceId;
    private List<InvoiceItems> Items;
    private String LastStatus;
    private List<InvoiceTracking> Tracking;

    public String getAgentId() {
        return AgentId;
    }

    public void setAgentId(String agentId) {
        AgentId = agentId;
    }

    public Long getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Long createdDate) {
        CreatedDate = createdDate;
    }

    public String getInvoiceId() {
        return InvoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        InvoiceId = invoiceId;
    }

    public List<InvoiceItems> getItems() {
        return Items;
    }

    public void setItems(List<InvoiceItems> items) {
        Items = items;
    }

    public String getLastStatus() {
        return LastStatus;
    }

    public void setLastStatus(String lastStatus) {
        LastStatus = lastStatus;
    }

    public List<InvoiceTracking> getTracking() {
        return Tracking;
    }

    public void setTracking(List<InvoiceTracking> tracking) {
        Tracking = tracking;
    }

    public Stok_Request() {

    }
}
