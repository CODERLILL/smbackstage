package com.cx.base;


import com.cx.base.response.ResponseBody;
import com.cx.base.response.ResponseHead;
import com.cx.base.response.ResponseSatus;

/**
 * @version V1.0
 * @Title: BaseController.java <br>
 * @Package com.cx.base <br>
 * @Description: (所有controller的基类,默认失败) <br>
 * @author: 路逸冰(Allen) <br>
 * @date: 2018/5/18 11:24 <br>
 */
public class BaseController {
    public ResponseHead createResponse(){
        //默认响应头
        ResponseHead responseHead = new ResponseHead();
        //默认响应体
        ResponseBody responseBody = new ResponseBody();
        responseHead.setCode(ResponseSatus.INTERNAL_SERVER_ERROR.getValue());
        responseHead.setMessage(ResponseSatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        responseHead.setInterfaceStatus(ResponseSatus.INTERFACE_LOCK.getReasonPhrase());
        responseBody.setResStatus(ResponseSatus.ERROR_REQUEST.getValue());
        responseBody.setResMessage(ResponseSatus.ERROR_REQUEST.getReasonPhrase());
        responseBody.setData(ResponseSatus.ERROR_DATA.getReasonPhrase());
        responseHead.setResponse(responseBody);
        return responseHead;
    }
}
