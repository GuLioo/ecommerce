package org.ecommerce.dto;

import org.ecommerce.entity.adminUser;


public class seckillExecution {

    private String oid;
    private int state;
    private String stateInfo;

    public seckillExecution(String oid, seckillStateEnum stateEnum) {
        this.oid = oid;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getInfo();
    }


    public String getoid() {
        return oid;
    }

    public void setoid(String username) {
        this.oid = username;
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


    @Override
    public String toString() {
        return "userLoginExecution{" +
                "oid='" + oid + '\'' +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +

                '}';
    }
}
