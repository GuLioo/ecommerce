package org.ecommerce.entity;

import java.util.Date;

public class product {

    private Integer pid;

    private String pname;

    private Double market_Price;

    private String image;

    private String pdesc;

    private Integer pnum;

    private Date pdate;


    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public Double getMarketPrice() {
        return market_Price;
    }

    public void setMarketPrice(Double marketPrice) {
        this.market_Price = marketPrice;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getPdesc() {
        return pdesc;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc == null ? null : pdesc.trim();
    }


    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }

    public Integer getPnum() {
        return pnum;
    }

    public void setPnum(Integer pnum) {
        this.pnum = pnum;
    }

    @Override
    public String toString() {
        return "product{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", marketPrice=" + market_Price +
                ", image='" + image + '\'' +
                ", pdesc='" + pdesc + '\'' +
                ", pnum=" + pnum +
                ", pdate=" + pdate +
                '}';
    }
}
