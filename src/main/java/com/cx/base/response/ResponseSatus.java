package com.cx.base.response;

/**
 * @version V1.0
 * @Title: ResponseSatus.java <br>
 * @Package com.cx.base.response <br>
 * @Description: (响应状态的封装类) <br>
 * @author: 路逸冰(Allen) <br>
 * @date: 2018/5/18 11:23 <br>
 */
public enum ResponseSatus {
    //body体相关请求从000开始
    SUCCESS_PARAMETER_OK("000", "成功"),
    INTERFACE_USE("001", "启用"),
    INTERFACE_LOCK("002", "停用"),
    ERROR_REQUEST("003","请求失败"),
    //服务器出现错误时候接口返回状态
    INTERFACE_UNKONWN("003","未知"),
    //参数相关提示code状态码从010开始
    BAD_REQUEST("010", "请求不完整[参数]"),
    ERROR_DATA("011","异常"),

    //服务器提示内容
    OK("200","请求成功"),
    REQUEST_TIMEOUT("408", "请求超时"),
    INTERNAL_SERVER_ERROR("500", "内部服务器错误"),
    BAD_GATEWAY("502", "网关损坏"),
    SERVICE_UNAVAILABLE("503", "服务不可用"),
    GATEWAY_TIMEOUT("504", "网关超时"),
    HTTP_VERSION_NOT_SUPPORTED("505", "HTTP版本不支持"),
    NETWORK_AUTHENTICATION_REQUIRED("511", "网络身份验证要求");

    private final String value;
    private final String reasonPhrase;

    ResponseSatus(String value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public String getValue() {
        return value;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }
}
