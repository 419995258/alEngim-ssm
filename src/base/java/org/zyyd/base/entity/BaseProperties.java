package org.zyyd.base.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="org.zyyd.base.entity.BaseProperties")
public class BaseProperties implements Serializable {
    /**
	* 系统编码
	*/
    @ApiModelProperty(value="系统编码")
    private String pid;

    /**
	* 属性代码
	*/
    @ApiModelProperty(value="属性代码")
    private String propertyKey;

    /**
	* 属性值
	*/
    @ApiModelProperty(value="属性值")
    private String propertyValue;

    /**
	* 属性值描述
	*/
    @ApiModelProperty(value="属性值描述")
    private String propertyDesc;

    /**
	* 属性组代码[FK](如：teach.subject)
	*/
    @ApiModelProperty(value="属性组代码[FK](如：teach.subject)")
    private String groupKey;

    /**
	* 排序
	*/
    @ApiModelProperty(value="排序")
    private Integer seqNo;

    /**
	* 
	*/
    @ApiModelProperty(value="")
    private Date creTime;

    /**
	* 
	*/
    @ApiModelProperty(value="")
    private String creUser;

    /**
	* 
	*/
    @ApiModelProperty(value="")
    private Date modTime;

    /**
	* 
	*/
    @ApiModelProperty(value="")
    private String modUser;

    private static final long serialVersionUID = 1L;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPropertyKey() {
        return propertyKey;
    }

    public void setPropertyKey(String propertyKey) {
        this.propertyKey = propertyKey;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    public String getPropertyDesc() {
        return propertyDesc;
    }

    public void setPropertyDesc(String propertyDesc) {
        this.propertyDesc = propertyDesc;
    }

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public Date getCreTime() {
        return creTime;
    }

    public void setCreTime(Date creTime) {
        this.creTime = creTime;
    }

    public String getCreUser() {
        return creUser;
    }

    public void setCreUser(String creUser) {
        this.creUser = creUser;
    }

    public Date getModTime() {
        return modTime;
    }

    public void setModTime(Date modTime) {
        this.modTime = modTime;
    }

    public String getModUser() {
        return modUser;
    }

    public void setModUser(String modUser) {
        this.modUser = modUser;
    }
}