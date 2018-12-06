package org.zyyd.base.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zyyd.base.dao.BaseAreaMapper;
import org.zyyd.base.entity.BaseArea;
import org.zyyd.base.entity.BaseAreaExample;
import org.zyyd.base.entity.BaseUser;
import org.zyyd.base.entity.vo.BaseAreaVO;
import org.zyyd.base.entity.vo.Message;
import org.zyyd.base.service.BaseAreaService;
import org.zyyd.base.service.RedisService;
import org.zyyd.base.util.BasicService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class BaseAreaServiceImpl extends BasicService implements BaseAreaService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private BaseAreaMapper baseAreaMapper;


    @Override
    public List<BaseArea> listBaseArea(BaseArea baseArea) {
        BaseAreaExample baseAreaExample = new BaseAreaExample();
        List<BaseArea> baseAreaList = new ArrayList<>();

        baseAreaExample.setOrderByClause("seq_no ");
        if(StringUtils.isNotBlank(baseArea.getAreaCode())){
            baseAreaExample.createCriteria().andAreaCodeEqualTo(baseArea.getAreaCode());
        }

        baseAreaList = baseAreaMapper.selectByExample(baseAreaExample);

        return baseAreaList;
    }

    @Override
    public List<BaseArea> listBaseAreaByParentCode(BaseArea baseArea) {
        BaseAreaExample baseAreaExample = new BaseAreaExample();
        List<BaseArea> baseAreaList = new ArrayList<>();

        baseAreaExample.setOrderByClause("seq_no");
        if(StringUtils.isNotBlank(baseArea.getAreaCode())){
            baseAreaExample.createCriteria().andParentCodeEqualTo(baseArea.getAreaCode());
        }

        baseAreaList = baseAreaMapper.selectByExample(baseAreaExample);

        return baseAreaList;
    }


    @Override
    public Message updateBaseArea(BaseAreaVO baseAreaVO) {
        Message message = new Message();

        BaseArea baseArea = new BaseArea();
        if(StringUtils.isBlank(baseAreaVO.getAreaId())){
            message.setMessage("areaID不能为空");
            return  message;
        }
        baseArea.setAreaId(baseAreaVO.getAreaId());
        if(StringUtils.isBlank(baseAreaVO.getAreaName())){
            message.setMessage("areaName不能为空");
            return  message;
        }
        baseArea.setAreaName(baseAreaVO.getAreaName());
        baseArea.setNationalCode(baseAreaVO.getNationalCode());
        if(StringUtils.isBlank(baseAreaVO.getNodeType())){
            baseArea.setNodeType("1");
        }else if("0".equals(baseAreaVO.getNodeType())){
            // 验证该地区下还有没有地区，否则不能归于叶子节点
            if(baseAreaVO.getBaseAreaSize() != null){
                if(baseAreaVO.getBaseAreaSize() > 0){
                    message.setMessage("该地区下还有地区，不能归于叶子节点");
                    return message;
                }
            }
        }
        baseArea.setNodeType(baseAreaVO.getNodeType());
        // 添加时间
        BaseUser baseUser = (BaseUser) SecurityUtils.getSubject().getPrincipal();
        baseArea.setModTime(new Date());
        baseArea.setModUser(baseUser.getLoginName());
        Integer success = baseAreaMapper.updateByPrimaryKeySelective(baseArea);
        if(success > 0){
            message.setSuccess(true);
            message.setMessage("更新成功");
        }

        return message;
    }

    @Override
    public Message saveBaseArea(BaseAreaVO baseAreaVO) {
        Message message = new Message();
        BaseArea baseArea = new BaseArea();


        baseArea.setAreaId(UUID.randomUUID().toString());

        if(StringUtils.isBlank(baseAreaVO.getAreaName())){
            message.setMessage("areaName不能为空");
            return  message;
        }
        baseArea.setAreaName(baseAreaVO.getAreaName());

        if(StringUtils.isBlank(baseAreaVO.getAreaCode())){
            message.setMessage("areaCode不能为空");
            return  message;
        }
        baseArea.setAreaCode(baseAreaVO.getAreaCode());


        baseArea.setNationalCode(baseAreaVO.getNationalCode());
        if(StringUtils.isBlank(baseAreaVO.getNodeType())){
            baseArea.setNodeType("1");
        }
        baseArea.setNodeType(baseAreaVO.getNodeType());

        if(StringUtils.isBlank(baseAreaVO.getParentCode())){
            message.setMessage("parCode不能为空");
            return  message;
        }
        baseArea.setParentCode(baseAreaVO.getParentCode());

        if(baseAreaVO.getNodeLevel() == null){
            message.setMessage("parCode不能为空");
            return  message;
        }
        baseArea.setNodeLevel(baseAreaVO.getNodeLevel() + 1);

        baseArea.setSeqNo(999);

        // 添加时间
        BaseUser baseUser = (BaseUser) SecurityUtils.getSubject().getPrincipal();
        baseArea.setCreTime(new Date());
        baseArea.setCreUser(baseUser.getLoginName());

        Integer success = baseAreaMapper.insertSelective(baseArea);
        if(success > 0){
            message.setSuccess(true);
            message.setMessage("更新成功");
        }else{
            message.setMessage("发生异常");
        }


        return message;
    }

    @Override
    public Message deleteBaseArea(BaseAreaVO baseAreaVO) {
        Message message = new Message();
        BaseArea baseArea = new BaseArea();

        if(StringUtils.isBlank(baseAreaVO.getAreaCode())){
            message.setMessage("areaCode不能为空");
            return  message;
        }

        // 验证是否有子地区
        BaseAreaExample baseAreaExample = new BaseAreaExample();
        baseAreaExample.createCriteria().andParentCodeEqualTo(baseAreaVO.getAreaCode());
        List<BaseArea> baseAreaList = baseAreaMapper.selectByExample(baseAreaExample);
        if(baseAreaList.size() > 0){
            message.setMessage("该地区下还有子地区，不能删除！");
            return  message;
        }

        baseAreaExample.clear();
        baseAreaExample.createCriteria().andAreaCodeEqualTo(baseAreaVO.getAreaCode());
        Integer success = baseAreaMapper.deleteByExample(baseAreaExample);
        if(success > 0){
            message.setSuccess(true);
            message.setMessage("更新成功");
        }else{
            message.setMessage("发生异常");
        }

        return message;
    }

    @Override
    public Message updateBaseAreaSeqNo(BaseAreaVO baseAreaVO) {
        Message message = new Message();
        BaseArea baseArea = new BaseArea();
        String areaIds = baseAreaVO.getAreaIds();
        if(StringUtils.isBlank(areaIds)){
            message.setMessage("更新排序失败，ids为空！");
            return  message;
        }else{
            String[] areaIdss = areaIds.split(",");
            if(areaIdss.length > 0){

                for (int i = 0; i < areaIdss.length; i++) {
                    baseArea = new BaseArea();
                    baseArea.setAreaId(areaIdss[i]);
                    baseArea.setSeqNo(i);
                    baseAreaMapper.updateByPrimaryKeySelective(baseArea);
                }
            }

        }

        message.setSuccess(true);
        message.setMessage("更新成功");

        return message;
    }
}

