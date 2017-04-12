package com.zqr.snake.mytest.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/24 0024.
 */

public class MyTest implements Serializable {
    private String code;
    private String data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
