package com.cx.dao;

import com.cx.model.UserInfo;

import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserInfoMapper {

    //验证用户是否存在
    UserInfo isUser(String userName);

    //新增用户
    Integer inUser(UserInfo userInfo);

    //更新用户相关信息
    Integer upUserInfo(UserInfo userInfo);

    //获取用户角色
    Set<String> getRoles(String userName);

    //获取用户权限
    Set<String> getPermissions(String userName);
}