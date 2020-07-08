package org.ecommerce.entity;

public class adminUser {

    private Integer auid;

    private String username;

    private String password;

    private short uid;

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

    @Override
    public String toString() {
        return "adminUser{" +
                "auid=" + auid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", uid=" + uid +
                '}';
    }
}
