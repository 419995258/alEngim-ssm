package org.zyyd.base.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="org.zyyd.base.entity.BaseBookCatelog")
public class BaseBookCatelog implements Serializable {
    /**
	* 
	*/
    @ApiModelProperty(value="")
    private String bcId;

    /**
	* 
	*/
    @ApiModelProperty(value="")
    private String name;

    /**
	* 属性组编码（如：001，001001）
	*/
    @ApiModelProperty(value="属性组编码（如：001，001001）")
    private String code;

    /**
	* 父节点代码(根节点：-1)
	*/
    @ApiModelProperty(value="父节点代码(根节点：-1)")
    private String parentCode;

    /**
	* 节点类型（1：非叶子节点；0：叶子节点）
	*/
    @ApiModelProperty(value="节点类型（1：非叶子节点；0：叶子节点）")
    private String nodeType;

    /**
	* 节点级别
	*/
    @ApiModelProperty(value="节点级别")
    private Integer nodeLevel;

    /**
	* 排序
	*/
    @ApiModelProperty(value="排序")
    private Integer seqNo;

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
	* 
	*/
    @ApiModelProperty(value="")
    private Date creTime;

    /**
	* 
	*/
    @ApiModelProperty(value="")
    private Date modTime;

    /**
	* 版本id
	*/
    @ApiModelProperty(value="版本id")
    private String versionId;

    private static final long serialVersionUID = 1L;

    public String getBcId() {
        return bcId;
    }

    public void setBcId(String bcId) {
        this.bcId = bcId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public Integer getNodeLevel() {
        return nodeLevel;
    }

    public void setNodeLevel(Integer nodeLevel) {
        this.nodeLevel = nodeLevel;
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

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }
}