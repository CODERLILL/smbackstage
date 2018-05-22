package com.cx.service.impl;

import com.cx.dao.UserInfoMapper;
import com.cx.model.UserInfo;
import com.cx.service.UserInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @version V1.0
 * @Title: UserInfoServiceImpl.java <br>
 * @Package com.cx.service.impl <br>
 * @Description: (处理用户相关的服务实现类) <br>
 * @author: 路逸冰(Allen) <br>
 * @date: 2018/5/18 11:58 <br>
 */
@Service
public class UserInfoServiceImpl implements UserInfoService{

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * <p>Title: isUser </p>
     * <p>Description: 验证用户是否存在 </p>
     */
    @Override
    public UserInfo isUser(String userName) {
        return userInfoMapper.isUser(userName);
    }

    /**
     * <p>Title: inUser </p>
     * <p>Description: 新增用户 </p>
     */
    @Override
    public Integer inUser(String userName, String passWord, String createTime) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(userName);
        userInfo.setPassword(passWord);
        userInfo.setCreatetime(createTime);
        return userInfoMapper.inUser(userInfo);
    }

    /**
     * <p>Title: upUserInfo </p>
     * <p>Description: 更新用户相关信息 </p>
     */
    @Override
    public Integer upUserInfo(UserInfo userInfo) {
        return userInfoMapper.upUserInfo(userInfo);
    }
    /**
     * <p>Title: getRoles </p>
     * <p>Description: 获取用户角色 </p>
     */
    @Override
    public Set<String> getRoles(String userName) {
        return userInfoMapper.getRoles(userName);
    }
    /**
     * <p>Title: getPermissions </p>
     * <p>Description: 获取用户权限 </p>
     */
    @Override
    public Set<String> getPermissions(String userName) {
        return userInfoMapper.getPermissions(userName);
    }

}
