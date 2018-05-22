package com.cx.base.response;


import java.io.Serializable;

/**
 * @version V1.0
 * @Title: ResponseHead.java <br>
 * @Package com.cx.base.response <br>
 * @Description: (响应头的封装类) <br>
 * @author: 路逸冰(Allen) <br>
 * @date: 2018/5/18 11:23 <br>
 */
public class ResponseHead implements Serializable {
    private String code;
    private String message;
    private String interfaceStatus;
    private ResponseBody response;

    public String getInterfaceStatus() {
        return interfaceStatus;
    }

    public void setInterfaceStatus(String interfaceStatus) {
        this.interfaceStatus = interfaceStatus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseBody getResponse() {
        return response;
    }

    public void setResponse(ResponseBody response) {
        this.response = response;
    }
}
