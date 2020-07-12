package org.ecommerce.entity;
import java.util.Date;

public class orders {

    private Integer oid;

    private Integer userId;

    private Double money;

    private short state;

    private Date order_time;


    public void setUser(adminUser user) {
        this.user = user;
    }

    // 关联用户
    private adminUser user;


    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getOrderTime() {
        return order_time;
    }

    public void setOrderTime(Date orderTime) {
        this.order_time = orderTime;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "oid=" + oid +
                ", userId=" + userId +
                ", money=" + money +
                ", state=" + state +
                ", orderTime=" + order_time +
                ", user=" + user +
                '}';
    }
}
