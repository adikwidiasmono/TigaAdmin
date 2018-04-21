package com.tiga.recview.model;

import java.util.List;

public class Penjualan {

    private String AgentId;
    private Long CreateDate;
    private List<TransactionItems> Items;
    private int KKSNo;
    private String KKSOwner;
    private String PenjualanId;
    private Long TransactionDate;

    public String getAgentId() {
        return AgentId;
    }

    public void setAgentId(String agentId) {
        AgentId = agentId;
    }

    public Long getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Long createDate) {
        CreateDate = createDate;
    }

    public List<TransactionItems> getItems() {
        return Items;
    }

    public void setItems(List<TransactionItems> items) {
        Items = items;
    }

    public int getKKSNo() {
        return KKSNo;
    }

    public void setKKSNo(int KKSNo) {
        this.KKSNo = KKSNo;
    }

    public String getKKSOwner() {
        return KKSOwner;
    }

    public void setKKSOwner(String KKSOwner) {
        this.KKSOwner = KKSOwner;
    }

    public String getPenjualanId() {
        return PenjualanId;
    }

    public void setPenjualanId(String penjualanId) {
        PenjualanId = penjualanId;
    }

    public Long getTransactionDate() {
        return TransactionDate;
    }

    public void setTransactionDate(Long transactionDate) {
        TransactionDate = transactionDate;
    }

    public Penjualan() {

    }
}
