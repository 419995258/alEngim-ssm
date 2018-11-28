package org.zyyd.base.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="org.zyyd.base.entity.BasePropertiesGroup")
public class BasePropertiesGroup implements Serializable {
    /* 系统编码*/
    @ApiModelProperty(value="系统编码")
    private String gid;

    /* 属性组编码（如：001，001001）*/
    @ApiModelProperty(value="属性组编码（如：001，001001）")
    private String groupCode;

    /* 父节点代码(根节点：-1)*/
    @ApiModelProperty(value="父节点代码(根节点：-1)")
    private String parentCode;

    /* 属性组Key（如：teach.subject）*/
    @ApiModelProperty(value="属性组Key（如：teach.subject）")
    private String groupKey;

    /* 属性组名称*/
    @ApiModelProperty(value="属性组名称")
    private String groupName;

    /* 节点类型（1：非叶子节点；0：叶子节点）*/
    @ApiModelProperty(value="节点类型（1：非叶子节点；0：叶子节点）")
    private String nodeType;

    /* 排序*/
    @ApiModelProperty(value="排序")
    private Integer seqNo;

    /* */
    @ApiModelProperty(value="")
    private String creUser;

    /* */
    @ApiModelProperty(value="")
    private String modUser;

    /* */
    @ApiModelProperty(value="")
    private Date creTime;

    /* */
    @ApiModelProperty(value="")
    private Date modTime;

    private static final long serialVersionUID = 1L;

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
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
}