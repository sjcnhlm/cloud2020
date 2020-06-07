package com.nuc.springcloud.entities;

import lombok.Data;

/*
* 向前端传递的封装的实体类
* */
@Data
public class CommonResult<T> {

    private Integer code; // 状态码
    private String message; // 信息

    private     T     data; // 传递的数据

    public CommonResult() {
    }

    public CommonResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public CommonResult(Integer code, String message)
    {
        this(code,message,null);
    }
}
