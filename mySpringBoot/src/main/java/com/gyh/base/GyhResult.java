package com.gyh.base;

import lombok.Data;

/**
 * @author guoyanhong
 * @date 2018/9/12 16:56
 */
@Data
public class GyhResult {

    private boolean success;
    private int code;
    private String msg;
    private Object data;
    public GyhResult(){
        this.success = true;
        this.code = 200;
        this.msg = "success";
    }
    public GyhResult(boolean success,int code,String msg,Object data){
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
