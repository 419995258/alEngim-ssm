package org.zyyd.base.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="org.zyyd.base.entity.BaseBookVersion")
public class BaseBookVersion implements Serializable {
    /**
	* 
	*/
    @ApiModelProperty(value="")
    private String versionId;

    /**
	* 学科code
	*/
    @ApiModelProperty(value="学科code")
    private String subjectCode;

    /**
	* 版本名称
	*/
    @ApiModelProperty(value="版本名称")
    private String versionName;

    /**
	* 版本code
	*/
    @ApiModelProperty(value="版本code")
    private String versionCode;

    /**
	* 排序
	*/
    @ApiModelProperty(value="排序")
    private Integer seqNo;

    /**
	* 创建人
	*/
    @ApiModelProperty(value="创建人")
    private String creUser;

    /**
	* 修改人
	*/
    @ApiModelProperty(value="修改人")
    private String modUser;

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
	* 出版社
	*/
    @ApiModelProperty(value="出版社")
    private String abbreviation;

    /**
	* 简称
	*/
    @ApiModelProperty(value="简称")
    private String press;

    private static final long serialVersionUID = 1L;

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
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

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }
}