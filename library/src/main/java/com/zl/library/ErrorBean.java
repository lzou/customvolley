package com.zl.library;


/**
 * Created by zoulu on 16/4/1.
 * 自定义网络错误类
 */
public class ErrorBean {
    private int code;

    private String description;

    public void setCode(int code){
        this.code = code;
    }
    public int getCode(){
        return this.code;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }

}
