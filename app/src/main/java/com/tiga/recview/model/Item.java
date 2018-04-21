package com.tiga.recview.model;

public class Item {

    private String code;
    private String img_url;
    private boolean is_subsidi;
    private String name;
    private int price;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public boolean isIs_subsidi() {
        return is_subsidi;
    }

    public void setIs_subsidi(boolean is_subsidi) {
        this.is_subsidi = is_subsidi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Item() {

    }
}
