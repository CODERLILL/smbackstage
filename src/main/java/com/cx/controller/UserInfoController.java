package com.cx.controller;

import com.cx.base.BaseController;
import com.cx.base.ResponseHttpStatus;
import com.cx.base.response.ResponseHead;
import com.cx.model.UserInfo;
import com.cx.service.CacheService;
import com.cx.service.UserInfoService;
import com.cx.utils.DateUtil;
import com.cx.utils.submail.MessageSend;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @version V1.0
 * @Title: UserInfoController.java <br>
 * @Package com.cx.controller <br>
 * @Description: (处理用户相关的内容) <br>
 * @author: 路逸冰(Allen) <br>
 * @date: 2018/5/18 09:26 <br>
 */
@Controller
@RequestMapping(value = "/api/pc/user")
public class UserInfoController extends BaseController {

    private ResponseHead responseHead = createResponse();

    @Autowired
    private CacheService cacheService;

    @Autowired
    private UserInfoService userInfoService;


    /**
     * @throws
     * @author: 路逸冰(Allen) <br>
     * @date: 2018/5/18 12:15<br>
     * @modify 修改人 <br>
     * @modifyDate: 修改时间 <br>
     * @Title: getSMSCode <br>
     * @Description: (获取验证码) <br>
     * @param: [response, phoneNo] <br>
     * @return: com.cx.base.response.ResponseHead <br>
     */
    @RequestMapping(value = "/getSMSCode", method = RequestMethod.GET)
    @ResponseBody
    public ResponseHead getSMSCode(HttpServletResponse response, String phoneNo) {
        int status = response.getStatus();
        if (status == 200) {
            if (phoneNo != null && !"".equals(phoneNo)) {
                int smsCode = MessageSend.sendMessage(phoneNo);
                //将验证码写入redis有效期300秒(5分钟)
                cacheService.setRedis(phoneNo, String.valueOf(smsCode),300);
                return ResponseHttpStatus.successRequest(responseHead, "success&发送成功");
            } else {
                //缺少参数
                return ResponseHttpStatus.responseStatus(responseHead, 010);
            }
        } else {
            //非200请求
            return ResponseHttpStatus.responseStatus(responseHead, status);
        }
    }

    /**
     * @throws
     * @author: 路逸冰(Allen) <br>
     * @date: 2018/5/18 12:15<br>
     * @modify 修改人 <br>
     * @modifyDate: 修改时间 <br>
     * @Title: userRegister <br>
     * @Description: (用户注册) <br>
     * @param: [response, phoneNum, passWord] <br>
     * @return: com.cx.base.response.ResponseHead <br>
     */
    @RequestMapping(value = "/userRegister", method = RequestMethod.GET)
    @ResponseBody
    public ResponseHead userRegister(HttpServletResponse response,
                                     String phoneNum, String passWord, int randomCode) {
        int status = response.getStatus();
        if (status == 200) {
            //如果json不为null，进行解析
            if (phoneNum != null && !"".equals(phoneNum) &&
                    !"".equals(passWord) && passWord != null) {
                //查询用户是否存在
                UserInfo userInfo = userInfoService.isUser(phoneNum);
                //如果user对象不等于null就代表用户存在，否则注册成功
                if (userInfo != null) {
                    return ResponseHttpStatus.errorRequest(responseHead, "error&用户已存在");
                } else {
                    //如果redis验证码不为空，代表存在此验证码，然后进行比对如果相等插入数据。
                    String redisCode = cacheService.getRedis(phoneNum);
                    if (redisCode != null && redisCode.equals(String.valueOf(randomCode))) {
                        //插入数据
                        int isSuccess = userInfoService.inUser(phoneNum, passWord, DateUtil.getCurrentDate());
                        //如果不等于1代表注册失败，否则注册成功
                        if (isSuccess == 1) {
                            return ResponseHttpStatus.successRequest(responseHead, "successs&注册成功");
                        } else {
                            return ResponseHttpStatus.successRequest(responseHead, "error&注册失败");
                        }
                    } else {//否则则提示验证码无效
                        return ResponseHttpStatus.errorRequest(responseHead, "error&验证码无效");
                    }
                }
            } else {//否则提示错误
                //缺少参数
                return ResponseHttpStatus.responseStatus(responseHead, 010);
            }
        } else {
            //非200请求
            return ResponseHttpStatus.responseStatus(responseHead, status);
        }
    }

    /**
     * @throws
     * @author: 路逸冰(Allen) <br>
     * @date: 2018/5/18 16:24<br>
     * @modify 修改人 <br>
     * @modifyDate: 修改时间 <br>
     * @Title: userForgetPwd <br>
     * @Description: (用户找回密码) <br>
     * @param: [response, phoneNum, passWord, randomCode] <br>
     * @return: com.cx.base.response.ResponseHead <br>
     */
    @RequestMapping(value = "/userForgetPwd", method = RequestMethod.GET)
    @ResponseBody
    public ResponseHead userForgetPwd(HttpServletResponse response,HttpServletRequest request,
                                      String phoneNum, String passWord, int randomCode) {
        int status = response.getStatus();
        if (status == 200) {
            //如果json不为null，进行解析
            if (phoneNum != null && !"".equals(phoneNum) &&
                    !"".equals(passWord) && passWord != null) {
                //查询用户是否存在
                UserInfo userInfo = userInfoService.isUser(phoneNum);
                //如果user对象不等于null就代表用户存在，执行密码找回逻辑，否则提示用户不存在
                if (userInfo != null) {
                    //如果redis验证码不为空，代表存在此验证码，然后进行比对如果相等更新数据。
                    String redisCode = cacheService.getRedis(phoneNum);
                    if (redisCode != null && redisCode.equals(String.valueOf(randomCode))) {
                        //插入数据
                        UserInfo upUser = new UserInfo();
                        upUser.setUsername(phoneNum);
                        upUser.setPassword(passWord);
                        int isSuccess = userInfoService.upUserInfo(upUser);
                        //如果不等于1代表注册失败，否则注册成功
                        if (isSuccess == 1) {
                            return ResponseHttpStatus.successRequest(responseHead, "successs&更新成功");
                        } else {
                            return ResponseHttpStatus.successRequest(responseHead, "error&更新失败");
                        }
                    } else {//否则则提示验证码无效
                        return ResponseHttpStatus.errorRequest(responseHead, "error&验证码无效");
                    }
                } else {
                    return ResponseHttpStatus.errorRequest(responseHead, "error&用户不存在");
                }
            } else {//否则提示错误
                //缺少参数
                return ResponseHttpStatus.responseStatus(responseHead, 010);
            }
        } else {
            //非200请求
            return ResponseHttpStatus.responseStatus(responseHead, status);
        }
    }

    /**
     * @throws
     * @author: 李会龙 <br>
     * @date: 2018/5/18 18:00<br>
     * @modify 修改人 <br>
     * @modifyDate: 修改时间 <br>
     * @Title: login <br>
     * @Description: (用户登录) <br>
     * @param: [response, userName, passWord, SMSCode,loginWay] <br>
     * @return: com.cx.base.response.ResponseHead <br>
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public ResponseHead userLogin(HttpServletRequest request, HttpServletResponse response,
                                  String userName, String passWord, String SMSCode, int loginWay, String pCode) {

        int status = response.getStatus();
        if (status == 200) {
            if (loginWay == 1) {
                //手机登录
                UserInfo user = userInfoService.isUser(userName);
                String phoneNo = userName;
                if (user != null && !"".equals(user)) {
                    if (SMSCode != null && !"".equals(SMSCode)) {
                        String Code = cacheService.getRedis(phoneNo);
                        if (Code != null && !"".equals(Code)) {//判断缓存中验证码是否还存在
                            if (SMSCode.equals(Code)) {//比对验证码是否正确
                                return ResponseHttpStatus.successRequest(responseHead, "seccess&登录成功" + user);
                            } else {
                                return ResponseHttpStatus.errorRequest(responseHead, "error&登录失败");
                            }
                        } else {
                            return ResponseHttpStatus.errorRequest(responseHead, "error&验证码已失效");
                        }
                    } else {
                        this.getSMSCode(response, phoneNo);
                    }
                } else {
                    return ResponseHttpStatus.errorRequest(responseHead, "error&用户不存在");
                }
            } else if (loginWay == 2) {
                //密码登录
                Subject subject = SecurityUtils.getSubject();
                UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord);
                UserInfo user = userInfoService.isUser(userName);
                if (user != null) {//判断用户是否存在
                    try {
                        subject.login(token);
                        return ResponseHttpStatus.successRequest(responseHead, "seccess&登录成功" + user);
                    } catch (IncorrectCredentialsException e) {
                        e.printStackTrace();
                        return ResponseHttpStatus.errorRequest(responseHead, "error&登录失败");
                    }
                } else {
                    return ResponseHttpStatus.errorRequest(responseHead, "error&用户不存在");
                }
            } /*else if (loginWay == 3) {
                //输错3次密码后，使用密码加验证码的登录方式
                String isOk = this.pictureCodeCompare(request, pCode);
                if ("success".equals(isOk)) {
                    //次数验证
                    //验证码校验
                } else {

                }
            }*/
            return null;
        } else {
            return ResponseHttpStatus.responseStatus(responseHead, status);
        }

    }

    /**
     * @author: 李会龙 <br>
     * @date: 2018/5/19 9:30<br>
     * @modify 修改人 <br>
     * @modifyDate: 修改时间 <br>
     * @Title: getPictureCode <br>
     * @Description: (获取图片验证码) <br>
     * @param: [request, response] <br>
     * @return: org.springframework.web.servlet.ModelAndView <br>
     * @throws
     */
    private Producer kaptchaProducer = null;

    @Autowired
    public void setCaptchaProducer(Producer kaptchaProducer) {
        this.kaptchaProducer = kaptchaProducer;
    }

    @RequestMapping(value = "/getPictureCode", method = RequestMethod.GET)
    //@ResponseBody
    public ModelAndView getPictureCode(HttpServletRequest request, HttpServletResponse response) {

        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        String capText = kaptchaProducer.createText();
        //将生成的验证码存入session中
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        BufferedImage bi = kaptchaProducer.createImage(capText);
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            ImageIO.write(bi, "jpg", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @throws
     * @author: 李会龙 <br>
     * @date: 2018/5/19 10:39<br>
     * @modify 修改人 <br>
     * @modifyDate: 修改时间 <br>
     * @Title: pictureCodeCompare <br>
     * @Description: (验证前端输入验证码是否正确) <br>
     * @param: [response，pCode] <br>
     * @return: com.cx.base.response.ResponseHead <br>
     */

    /*@RequestMapping(value = "/pictureCodeCompare",method = RequestMethod.GET)
    @ResponseBody*/
    public String pictureCodeCompare(HttpServletRequest request, String pCode) {
        String pictureCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (pCode.equals(pictureCode)) {
            return "success";
        } else {
            return "fail";
        }
    }

    /**
     * @throws
     * @author: 李会龙 <br>
     * @date: 2018/5/19 11:50<br>
     * @modify 修改人 <br>
     * @modifyDate: 修改时间 <br>
     * @Title: updatePassWord <br>
     * @Description: (修改用户密码) <br>
     * @param: [response，pCode] <br>
     * @return: com.cx.base.response.ResponseHead <br>
     */

    @RequestMapping(value = "/updatePassWord", method = RequestMethod.GET)
    @ResponseBody
    public ResponseHead updatePassWord(HttpServletResponse response, String userName, String SMSCode, String passWord, String newPassWord) {
        int status = response.getStatus();
        if (status == 200) {
            UserInfo userInfo = userInfoService.isUser(userName);
            String password = userInfo.getPassword();
            userInfo.setUsername(userName);
            userInfo.setPassword(newPassWord);
            String code = cacheService.getRedis(userName);
            if (SMSCode.equals(code) && passWord.equals(password)) {
                int result = userInfoService.upUserInfo(userInfo);
                if (result == 1) {
                    return ResponseHttpStatus.successRequest(responseHead, "success&修改成功");
                } else {
                    return ResponseHttpStatus.errorRequest(responseHead, "error&修改失败");
                }
            } else {
                return ResponseHttpStatus.errorRequest(responseHead, "error&验证码或密码错误");
            }
        } else {
            return ResponseHttpStatus.errorRequest(responseHead, status);
        }

    }


}
