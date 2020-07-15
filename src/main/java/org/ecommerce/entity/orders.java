package org.ecommerce.entity;
import java.sql.Timestamp;
import java.util.Date;

public class orders {

    private String oid;

    private Integer userId;

    private Double orderPrice;
    private Double userDiscount;
    private Integer productId;
    private Double productPrice;
    private String productName;
    private String productImage;
    private String productDesc;
    private String productCate;
    private short orderState;
    private Timestamp orderTime;

    public orders(String oid, Integer userId, Double orderPrice, Double userDiscount, Integer productId, Double productPrice, String productName, String productImage, String productDesc, String productCate, short orderState, Timestamp orderTime) {
        this.oid = oid;
        this.userId = userId;
        this.orderPrice = orderPrice;
        this.userDiscount = userDiscount;
        this.productId = productId;
        this.productPrice = productPrice;
        this.productName = productName;
        this.productImage = productImage;
        this.productDesc = productDesc;
        this.productCate = productCate;
        this.orderState = orderState;
        this.orderTime = orderTime;
    }


    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Double getUserDiscount() {
        return userDiscount;
    }

    public void setUserDiscount(Double userDiscount) {
        this.userDiscount = userDiscount;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductCate() {
        return productCate;
    }

    public void setProductCate(String productCate) {
        this.productCate = productCate;
    }

    public short getOrderState() {
        return orderState;
    }

    public void setOrderState(short orderState) {
        this.orderState = orderState;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "orders{" +
                "oid='" + oid + '\'' +
                ", userId=" + userId +
                ", orderPrice=" + orderPrice +
                ", userDiscount=" + userDiscount +
                ", productId=" + productId +
                ", productPrice=" + productPrice +
                ", productName='" + productName + '\'' +
                ", productImage='" + productImage + '\'' +
                ", productDesc='" + productDesc + '\'' +
                ", productCate='" + productCate + '\'' +
                ", orderState=" + orderState +
                ", orderTime=" + orderTime +
                '}';
    }
}
