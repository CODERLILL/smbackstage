package com.cx.service;


import com.cx.model.UserInfo;

import java.util.Set;

public interface UserInfoService {
    //验证用户是否存在
    UserInfo isUser(String userName);

    //新增用户
    Integer inUser(String userName,String passWord,String createTime);

    //更新用户相关信息
    Integer upUserInfo(UserInfo userInfo);

    //获取用户角色
    Set<String> getRoles(String userName);

    //获取用户权限
    Set<String> getPermissions(String userName);
}
