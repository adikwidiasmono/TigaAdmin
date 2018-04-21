package com.tiga.recview.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Agen {

    private String AgentAddress;
    private String AgentId;
    private String AgentName;
    private Long CreateDate;
    private String ImageURL;
    private String Status;
    private Integer StokBright12Kg;
    private Integer StokBright5Kg;
    private Integer StokEase14Kg;
    private Integer StokEase9Kg;
    private Integer StokElpiji12Kg;
    private Integer StokElpiji3Kg;

    public String getAgentAddress() {
        return AgentAddress;
    }

    public void setAgentAddress(String agentAddress) {
        AgentAddress = agentAddress;
    }

    public String getAgentId() {
        return AgentId;
    }

    public void setAgentId(String agentId) {
        AgentId = agentId;
    }

    public String getAgentName() {
        return AgentName;
    }

    public void setAgentName(String agentName) {
        AgentName = agentName;
    }

    public Long getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Long createDate) {
        CreateDate = createDate;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Integer getStokBright12Kg() {
        return StokBright12Kg;
    }

    public void setStokBright12Kg(Integer stokBright12Kg) {
        StokBright12Kg = stokBright12Kg;
    }

    public Integer getStokBright5Kg() {
        return StokBright5Kg;
    }

    public void setStokBright5Kg(Integer stokBright5Kg) {
        StokBright5Kg = stokBright5Kg;
    }

    public Integer getStokEase14Kg() {
        return StokEase14Kg;
    }

    public void setStokEase14Kg(Integer stokEase14Kg) {
        StokEase14Kg = stokEase14Kg;
    }

    public Integer getStokEase9Kg() {
        return StokEase9Kg;
    }

    public void setStokEase9Kg(Integer stokEase9Kg) {
        StokEase9Kg = stokEase9Kg;
    }

    public Integer getStokElpiji12Kg() {
        return StokElpiji12Kg;
    }

    public void setStokElpiji12Kg(Integer stokElpiji12Kg) {
        StokElpiji12Kg = stokElpiji12Kg;
    }

    public Integer getStokElpiji3Kg() {
        return StokElpiji3Kg;
    }

    public void setStokElpiji3Kg(Integer stokElpiji3Kg) {
        StokElpiji3Kg = stokElpiji3Kg;
    }

    public Agen() {

    }
}
