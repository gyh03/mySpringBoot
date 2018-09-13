package com.gyh.base.exception;

import lombok.Data;

/**
 * @author guoyanhong
 * @date 2018/9/11 15:56
 */
@Data
public class GyhException extends RuntimeException {


    public static final int SUCCESS = 200;
    public static final int SERVER_INTERNAL = 500;

    /**
     * 异常消息
     */
    private String msg;
    /**
     * 异常状态码
     */
    private int code;

    public GyhException(String msg) {
        super(msg);
        this.msg = msg;
        this.code = SERVER_INTERNAL;
    }

    public GyhException() {
        super("this is a GyhException...");
        this.code = SERVER_INTERNAL;
    }
}
