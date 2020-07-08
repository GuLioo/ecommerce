package org.ecommerce.dto;



public class ecommerceExecution {
    private String username;
    private int state;
    private String stateInfo;
    private SuccesKilled succesKilled;

    public ecommerceExecution(long seckillId, SeckillStateEnum stateEnum, SuccesKilled succesKilled) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getInfo();
        this.succesKilled = succesKilled;
    }

    public ecommerceExecution(long seckillId, SeckillStateEnum stateEnum) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getInfo();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
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

    public SuccesKilled getSuccesKilled() {
        return succesKilled;
    }

    public void setSuccesKilled(SuccesKilled succesKilled) {
        this.succesKilled = succesKilled;
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", succesKilled=" + succesKilled +
                '}';
    }
}
