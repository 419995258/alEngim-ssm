package org.zyyd.base.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ApiModel(value="org.zyyd.base.entity.BaseUser")
public class BaseUserVO implements Serializable {

    /**
     * 第几页
     */
    private Integer pageNo;

    /**
     *
     */
    @ApiModelProperty(value="")
    private String userId;

    /**
     * 登录名
     */
    @ApiModelProperty(value="登录名")
    private String loginName;

    /**
     * 密码
     */
    @ApiModelProperty(value="密码")
    private String passWord;

    /**
     * 状态  1:正常  0:封杀
     */
    @ApiModelProperty(value="状态  1:正常  0:封杀")
    private String userStatus;

    /**
     * 真实姓名
     */
    @ApiModelProperty(value="真实姓名")
    private String realname;

    /**
     * 移动电话
     */
    @ApiModelProperty(value="移动电话")
    private String mobile;

    /**
     * email
     */
    @ApiModelProperty(value="email")
    private String email;

    /**
     * 地址
     */
    @ApiModelProperty(value="地址")
    private String address;

    /**
     * QQ
     */
    @ApiModelProperty(value="QQ")
    private String qq;

    /**
     * 登录次数
     */
    @ApiModelProperty(value="登录次数")
    private Integer loginCount;

    /**
     * 最后一次登录时间 到秒
     */
    @ApiModelProperty(value="最后一次登录时间 到秒")
    private Date lastLoginTime;

    /**
     * 最后一次登录ip
     */
    @ApiModelProperty(value="最后一次登录ip")
    private String lastLoginIp;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private Date creTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value="修改时间")
    private Date modTime;

    /**
     *
     */
    @ApiModelProperty(value="")
    private String creUser;

    /**
     *
     */
    @ApiModelProperty(value="")
    private String modUser;

    /**
     * 角色code，"#"隔开
     */
    @ApiModelProperty(value="角色code，#隔开")
    private String roleCodes;

    @ApiModelProperty(value="角色code集合")
    private String[] roleCodesList;

    private static final long serialVersionUID = 1L;

    public String[] getRoleCodesList() {
        return roleCodesList;
    }

    public void setRoleCodesList(String[] roleCodesList) {
        this.roleCodesList = roleCodesList;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Date getCreTime() {
        return creTime;
    }

    public void setCreTime(Date creTime) {
        this.creTime = creTime;
    }

    public Date getModTime() {
        return modTime;
    }

    public void setModTime(Date modTime) {
        this.modTime = modTime;
    }

    public String getCreUser() {
        return creUser;
    }

    public void setCreUser(String creUser) {
        this.creUser = creUser;
    }

    public String getModUser() {
        return modUser;
    }

    public void setModUser(String modUser) {
        this.modUser = modUser;
    }

    public String getRoleCodes() {
        return roleCodes;
    }

    public void setRoleCodes(String roleCodes) {
        this.roleCodes = roleCodes;
    }
}