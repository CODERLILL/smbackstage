package com.cx.base.response;

import java.io.Serializable;

/**
 * @version V1.0
 * @Title: ResponseBody.java <br>
 * @Package com.cx.base.response <br>
 * @Description: (响应body体的封装类) <br>
 * @author: 路逸冰(Allen) <br>
 * @date: 2018/5/18 11:23 <br>
 */
public class ResponseBody implements Serializable{
    private String resStatus;
    private String resMessage;
    private Object data;

    public String getResStatus() {
        return resStatus;
    }

    public void setResStatus(String resStatus) {
        this.resStatus = resStatus;
    }

    public String getResMessage() {
        return resMessage;
    }

    public void setResMessage(String resMessage) {
        this.resMessage = resMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
