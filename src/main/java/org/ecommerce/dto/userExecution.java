package org.ecommerce.dto;

import org.ecommerce.entity.adminUser;


public class userExecution {

    private String username;
    private int state;
    private String stateInfo;
    private adminUser adminUser;

    public userExecution(String username, userStateEnum stateEnum, adminUser adminUser) {
        this.username = username;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getInfo();
        this.adminUser = adminUser;
    }

    public userExecution(String username, userStateEnum stateEnum) {
        this.username = username;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getInfo();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }


    public org.ecommerce.entity.adminUser getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(org.ecommerce.entity.adminUser adminUser) {
        this.adminUser = adminUser;
    }

    @Override
    public String toString() {
        return "userLoginExecution{" +
                "username='" + username + '\'' +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", adminUser=" + adminUser +
                '}';
    }
}
