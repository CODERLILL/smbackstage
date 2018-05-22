package com.cx.dao;

import com.cx.model.CompanyInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyInfoMapper {

    int deleteByPrimaryKey(Integer companyid);
    //添加公司信息
    int insert(CompanyInfo companyInfo);

    int insertSelective(CompanyInfo record);

    CompanyInfo selectByPrimaryKey(Integer companyid);

    int updateByPrimaryKeySelective(CompanyInfo record);

    int updateByPrimaryKeyWithBLOBs(CompanyInfo record);

    int updateByPrimaryKey(CompanyInfo record);
}