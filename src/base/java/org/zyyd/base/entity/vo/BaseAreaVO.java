package org.zyyd.base.entity.vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

@ApiModel(value="org.zyyd.base.entity.BaseArea")
public class BaseAreaVO implements Serializable {
    /* */
    @ApiModelProperty(value="")
    private String areaId;

    /* 地区code*/
    @ApiModelProperty(value="地区code")
    private String areaCode;

    /* 父code*/
    @ApiModelProperty(value="父code")
    private String parentCode;

    /* 节点类型*/
    @ApiModelProperty(value="节点类型")
    private String nodeType;

    /* 地区名称*/
    @ApiModelProperty(value="地区名称")
    private String areaName;

    /* 地区描述*/
    @ApiModelProperty(value="地区描述")
    private String areaDesc;

    /* 中部、东部、西部*/
    @ApiModelProperty(value="中部、东部、西部")
    private String areaType;

    /* 节点等级*/
    @ApiModelProperty(value="节点等级")
    private Integer nodeLevel;

    /* 国家行政区划编码*/
    @ApiModelProperty(value="国家行政区划编码")
    private String nationalCode;

    /* 排序号*/
    @ApiModelProperty(value="排序号")
    private Integer seqNo;

    /* 创建人*/
    @ApiModelProperty(value="创建人")
    private String creUser;

    /* 修改人*/
    @ApiModelProperty(value="修改人")
    private String modUser;

    /* 创建时间*/
    @ApiModelProperty(value="创建时间")
    private Date creTime;

    /* 修改时间*/
    @ApiModelProperty(value="修改时间")
    private Date modTime;


    /**
     * 该地区归属的地区数量
     */
    private Integer baseAreaSize;

    /**
     * 地区的ids排序
     */
    private String areaIds;

    public String getAreaIds() {
        return areaIds;
    }

    public void setAreaIds(String areaIds) {
        this.areaIds = areaIds;
    }

    public Integer getBaseAreaSize() {
        return baseAreaSize;
    }

    public void setBaseAreaSize(Integer baseAreaSize) {
        this.baseAreaSize = baseAreaSize;
    }

    private static final long serialVersionUID = 1L;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
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

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaDesc() {
        return areaDesc;
    }

    public void setAreaDesc(String areaDesc) {
        this.areaDesc = areaDesc;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public Integer getNodeLevel() {
        return nodeLevel;
    }

    public void setNodeLevel(Integer nodeLevel) {
        this.nodeLevel = nodeLevel;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
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