package com.gyh.base.exception;

import lombok.Data;

/**
 * @author guoyanhong
 * @date 2018/9/11 15:56
 */
@Data
public class NoPrintException extends RuntimeException {

    /**
     * 异常消息
     */
    private String msg;
    /**
     * 异常状态码
     */
    private int code;

    public NoPrintException(String msg) {
        super(msg);
        this.msg = msg;
        this.code = GyhException.SERVER_INTERNAL;
    }

    public NoPrintException() {
        super("this is a GyhException...");
        this.code = GyhException.SERVER_INTERNAL;
    }
}
