package com.cx.service.impl;

import com.cx.dao.CompanyInfoMapper;
import com.cx.model.CompanyInfo;
import com.cx.service.CompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyInfoServiceImpl implements CompanyInfoService {
    @Autowired
    private CompanyInfoMapper companyInfoMapper;
    @Override
    public int insert(CompanyInfo companyInfo) {
        return companyInfoMapper.insert(companyInfo);
    }
}
