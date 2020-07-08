package org.ecommerce.entity;

public class category {

    private Integer cId;

    private String cname;

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "category{" +
                "cId=" + cId +
                ", cname='" + cname + '\'' +
                '}';
    }
}
