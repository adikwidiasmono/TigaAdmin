package com.tiga.recview.model;

public class InvoiceTracking {

    private Long CreateDate;
    private String Description;
    private String Status;
    private String Title;

    public Long getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Long createDate) {
        CreateDate = createDate;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public InvoiceTracking() {
    }
}
