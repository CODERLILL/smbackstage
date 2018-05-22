package com.cx.controller;

import com.cx.base.BaseController;
import com.cx.base.ResponseHttpStatus;
import com.cx.base.response.ResponseHead;
import com.cx.model.CompanyInfo;
import com.cx.service.CacheService;
import com.cx.service.CompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
/**
 * @version V1.0
 * @Title: CompanyInfoController.java <br>
 * @Package com.cx.controller <br>
 * @Description: (处理公司信息相关内容) <br>
 * @author: 李会龙 <br>
 * @date: 2018/5/21 10:10 <br>
 */
@Controller
@RequestMapping(value = "api/pc/company")
public class CompanyInfoController extends BaseController {

    private ResponseHead responseHead = createResponse();

    @Autowired
    private CacheService cacheService;
    @Autowired
    private CompanyInfoService companyInfoService;

    /**
     * @throws
     * @author: 李会龙 <br>
     * @date: 2018/5/21 11:15<br>
     * @modify 修改人 <br>
     * @modifyDate: 修改时间 <br>
     * @Title: insert <br>
     * @Description: (添加企业及商家信息) <br>
     * @param: [response, companyInfo] <br>
     * @return: com.cx.base.response.ResponseHead <br>
     */
    @RequestMapping(value = "/addCompany",method = RequestMethod.GET)
    @ResponseBody
    public ResponseHead insert(HttpServletResponse response, CompanyInfo companyInfo){
        int status = response.getStatus();
            if(status==200){
               int isSuccess =  companyInfoService.insert(companyInfo);
               if(isSuccess == 1){
                   return ResponseHttpStatus.successRequest(responseHead,"success&成功");
               }else {
                   return ResponseHttpStatus.errorRequest(responseHead,"error&失败");
               }
            }else {
                   return ResponseHttpStatus.errorRequest(responseHead,status);
            }
    }

}


