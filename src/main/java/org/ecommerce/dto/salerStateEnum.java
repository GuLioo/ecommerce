package org.ecommerce.dto;

public enum salerStateEnum {
    LOGIN_SUCCESS(1,"登陆成功"),
    ADD_SUCCESS(0,"增加用户成功"),
    INSERT_ERROR(-1,"商品数量超限"),
    SET_ERROR(-2,"商品数量设置错误"),
    NULL_ERROR(-3,"不存在该分类的商品");

    private int state;
    private String info;
    salerStateEnum(int state, String info){
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

    public static salerStateEnum stateOf(int index){
        for(salerStateEnum state:values()){
            if(state.getState()==index)
                return state;
        }
        return null;
    }
}
