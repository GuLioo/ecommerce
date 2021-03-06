package org.ecommerce.dto;

import java.util.HashMap;
import java.util.Map;

public class pageResult {
    //表示状态码
    private int code;
    //提示信息
    private String msg;
    //要返回给浏览器的数据
    private Map<String,Object> extend = new HashMap<String,Object>();

    //在controller中调用success方法，返回Msg对象
    public static pageResult success(){
        pageResult result = new pageResult();
        result.setCode(100);
        result.setMsg("处理成功！");
        return result;
    }
    public static pageResult fail(){
        pageResult result = new pageResult();
        result.setCode(200);
        result.setMsg("处理失败");
        return result;
    }

    //把pageInfo中的数据保存到pageResult中，一并返回
    public pageResult add(String key, Object value){
        this.getExtend().put(key, value);
        return this;
    }


    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Map<String, Object> getExtend() {
        return extend;
    }
    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}