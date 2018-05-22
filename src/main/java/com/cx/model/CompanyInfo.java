package com.cx.model;

import java.io.Serializable;
import java.util.Date;

public class CompanyInfo implements Serializable {
    private Integer companyid;

    private String companyname;

    private String province;

    private String city;

    private String area;

    private String comaddress;

    private String phonenumber;

    private String comdescribe;

    private String website;

    private String photourl;

    private Integer status;

    private String outtime;

    private Date updatetime;

    private byte[] createtime;

    private static final long serialVersionUID = 1L;

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname == null ? null : companyname.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getComaddress() {
        return comaddress;
    }

    public void setComaddress(String comaddress) {
        this.comaddress = comaddress == null ? null : comaddress.trim();
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber == null ? null : phonenumber.trim();
    }

    public String getComdescribe() {
        return comdescribe;
    }

    public void setComdescribe(String comdescribe) {
        this.comdescribe = comdescribe == null ? null : comdescribe.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl == null ? null : photourl.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOuttime() {
        return outtime;
    }

    public void setOuttime(String outtime) {
        this.outtime = outtime == null ? null : outtime.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public byte[] getCreatetime() {
        return createtime;
    }

    public void setCreatetime(byte[] createtime) {
        this.createtime = createtime;
    }
}