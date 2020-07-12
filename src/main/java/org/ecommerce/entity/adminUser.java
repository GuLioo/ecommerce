package org.ecommerce.entity;

public class adminUser {

    private Integer auid;

    private String username;

    private String password;

    private short uid;

    private double discount;

    private String name;

    private String email;

    private String phone;

    public short getUid() {
        return uid;
    }

    public void setUid(short uid) {
        this.uid = uid;
    }

    public Integer getAuid() {
        return auid;
    }

    public void setAuid(Integer auid) {
        this.auid = auid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "adminUser{" +
                "auid=" + auid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", uid=" + uid +
                ", discount=" + discount +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
