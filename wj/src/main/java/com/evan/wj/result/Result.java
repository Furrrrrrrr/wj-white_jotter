package com.evan.wj.result;

public class Result {

    // TODO: 2019/12/27 实际响应码是固定的，后期需要改成枚举类
    private int code;

    public Result(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
