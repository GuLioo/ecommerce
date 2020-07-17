package org.ecommerce.dto;

public enum seckillStateEnum {
    SUCCESS(1,"购买成功"),
    UNDER_STOCK(0,"库存不足"),
    INNER_ERROR(-1,"系统异常");

    private int state;
    private String info;
    seckillStateEnum(int state, String info){
        this.state=state;
        this.info=info;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public static seckillStateEnum stateOf(int index){
        for(seckillStateEnum state:values()){
            if(state.getState()==index)
                return state;
        }
        return null;
    }
}
