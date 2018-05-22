package com.cx.utils.qiniu;

import com.qiniu.util.Auth;

/**
 * @version V1.0
 * @Title: QiniuAuth.java <br>
 * @Package com.cx.utils.qiniu <br>
 * @Description: (获取七牛上传文件的token) <br>
 * @author: 路逸冰(Allen) <br>
 * @date: 2018/5/18 09:38 <br>
 */
public final class QiniuAuth {

    //七牛分配的默认域名链接资源名称p52ohxg2s.bkt.clouddn.com
    private static final String accessKey = "KKtbVVgFargHg8Iwsl_EKkBYcEYLxRKM56O6zDP7";
    private static final String secretKey = "OVB-m9NAmPSMREN3LNUEMW3b47UL1KCU3fPXdoQP";
    private static final String bucket = "xmcc";

    public static String getQiniuToken() {
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        return upToken;
    }
}
