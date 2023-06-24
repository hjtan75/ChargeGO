package com.lb02b.chargego.Utils;


public class CommonReturnType {

    private Integer status;

    private String msg;

    private Object data;

    public static CommonReturnType build(Integer status, String msg, Object data) {
        return new CommonReturnType(status, msg, data);
    }

    public static CommonReturnType success(Object data) {
        return new CommonReturnType(data);
    }

    public static CommonReturnType success() {
        return new CommonReturnType(null);
    }

    public static CommonReturnType errorMsg(String msg) {
        return new CommonReturnType(500, msg, null);
    }

    public static CommonReturnType errorTokenMsg(String msg) {
        return new CommonReturnType(401, msg, null);
    }

    public static CommonReturnType errorException(String msg) {
        return new CommonReturnType(555, msg, null);
    }

    public CommonReturnType() {

    }

    public CommonReturnType(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public CommonReturnType(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Boolean isOK() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
