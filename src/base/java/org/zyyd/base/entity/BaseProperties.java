package org.zyyd.base.entity;

import java.io.Serializable;
import java.util.Date;

public class BaseProperties implements Serializable {
    /**
	* 系统编码
	*/
    private String pid;

    /**
	* 属性代码
	*/
    private String propertyKey;

    /**
	* 属性值
	*/
    private String propertyValue;

    /**
	* 属性值描述
	*/
    private String propertyDesc;

    /**
	* 属性组代码[FK](如：teach.subject)
	*/
    private String groupKey;

    /**
	* 排序
	*/
    private Integer seqNo;

    /**
	* 
	*/
    private Date creTime;

    /**
	* 
	*/
    private String creUser;

    /**
	* 
	*/
    private Date modTime;

    /**
	* 
	*/
    private String modUser;

    /**
	* 扩展字段
	*/
    private String extra1;

    /**
	* 
	*/
    private String extra2;

    /**
	* 
	*/
    private String extra3;

    /**
	* 
	*/
    private String extra4;

    /**
	* 
	*/
    private String extra5;

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

    public String getExtra1() {
        return extra1;
    }

    public void setExtra1(String extra1) {
        this.extra1 = extra1;
    }

    public String getExtra2() {
        return extra2;
    }

    public void setExtra2(String extra2) {
        this.extra2 = extra2;
    }

    public String getExtra3() {
        return extra3;
    }

    public void setExtra3(String extra3) {
        this.extra3 = extra3;
    }

    public String getExtra4() {
        return extra4;
    }

    public void setExtra4(String extra4) {
        this.extra4 = extra4;
    }

    public String getExtra5() {
        return extra5;
    }

    public void setExtra5(String extra5) {
        this.extra5 = extra5;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pid=").append(pid);
        sb.append(", propertyKey=").append(propertyKey);
        sb.append(", propertyValue=").append(propertyValue);
        sb.append(", propertyDesc=").append(propertyDesc);
        sb.append(", groupKey=").append(groupKey);
        sb.append(", seqNo=").append(seqNo);
        sb.append(", creTime=").append(creTime);
        sb.append(", creUser=").append(creUser);
        sb.append(", modTime=").append(modTime);
        sb.append(", modUser=").append(modUser);
        sb.append(", extra1=").append(extra1);
        sb.append(", extra2=").append(extra2);
        sb.append(", extra3=").append(extra3);
        sb.append(", extra4=").append(extra4);
        sb.append(", extra5=").append(extra5);
        sb.append("]");
        return sb.toString();
    }
}