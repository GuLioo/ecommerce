package org.ecommerce.dto;

public enum userStateEnum {
    LOGIN_SUCCESS(1,"登陆成功"),
    ADD_SUCCESS(0,"增加用户成功"),
    Change_SUCCESS(2,"更改用户状态成功"),
    ADD_SALER_ERROR(-1,"销售商数目超限"),
    PASSWORD_ERROR(-2,"密码错误"),
    NO_USER(-3,"不存在用户"),
    INNER_ERROR(-4,"内部错误");

    private int state;
    private String info;
    userStateEnum(int state, String info){
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

    public static userStateEnum stateOf(int index){
        for(userStateEnum state:values()){
            if(state.getState()==index)
                return state;
        }
        return null;
    }
}
