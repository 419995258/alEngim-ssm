package org.zyyd.base.service.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zyyd.base.dao.BasePermissionMapper;
import org.zyyd.base.dao.BasePropertiesGroupMapper;
import org.zyyd.base.dao.BasePropertiesMapper;
import org.zyyd.base.dao.BaseRoleMapper;
import org.zyyd.base.dao.BaseRolePermissionMapMapper;
import org.zyyd.base.dao.BaseUserMapper;
import org.zyyd.base.dao.BaseUserRoleMapMapper;
import org.zyyd.base.entity.BasePermission;
import org.zyyd.base.entity.BasePermissionExample;
import org.zyyd.base.entity.BaseProperties;
import org.zyyd.base.entity.BasePropertiesExample;
import org.zyyd.base.entity.BasePropertiesGroup;
import org.zyyd.base.entity.BasePropertiesGroupExample;
import org.zyyd.base.entity.BaseRole;
import org.zyyd.base.entity.BaseRoleExample;
import org.zyyd.base.entity.BaseRolePermissionMap;
import org.zyyd.base.entity.BaseRolePermissionMapExample;
import org.zyyd.base.entity.BaseUser;
import org.zyyd.base.entity.BaseUserExample;
import org.zyyd.base.entity.vo.BaseUserVO;
import org.zyyd.base.entity.vo.Message;
import org.zyyd.base.entity.vo.ResultVo;
import org.zyyd.base.service.BasePropertiesService;
import org.zyyd.base.service.RedisService;
import org.zyyd.base.util.BasicService;
import org.zyyd.base.util.MD5;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import shiro.ShiroRealm;

@Service
public class BasePropertiesServiceImpl extends BasicService implements BasePropertiesService {

    @Autowired
    private RedisService redisService;


    @Autowired
    private BasePropertiesMapper basePropertiesMapper;

    @Autowired
    private BasePropertiesGroupMapper basePropertiesGroupMapper;





    @Override
    public List<BasePropertiesGroup> listBasePropertiesGroup() {
        BasePropertiesGroupExample propertyGroupExample = new BasePropertiesGroupExample();
        propertyGroupExample.setOrderByClause(" seq_no ");
        List<BasePropertiesGroup> propertyGroupList = basePropertiesGroupMapper.selectByExample(propertyGroupExample);
        return propertyGroupList;
    }

    @Override
    public List<BaseProperties> listBasePropertiesByPropertyGroupId(String groupKey) {
        BasePropertiesExample propertyExample = new BasePropertiesExample();
        propertyExample.setOrderByClause(" seq_no ");
        propertyExample.createCriteria().andGroupKeyEqualTo(groupKey);
        List<BaseProperties> propertyList = basePropertiesMapper.selectByExample(propertyExample);
        return propertyList;
    }

    @Override
    public Message removeBasePropertiesById(String id) {
        Message message = new Message();
        BaseProperties baseProperties = basePropertiesMapper.selectByPrimaryKey(id);
        Integer success = basePropertiesMapper.deleteByPrimaryKey(id);
        if(success == 1){
            message.setSuccess(true);
            message.setMessage("删除成功");
            // 清楚这个属性的缓存以及拥有这个属性的属性组重载
            redisService.removePermissionOneRedis(baseProperties.getPropertyKey());
            redisService.setPropertiesGroupRedis(baseProperties.getGroupKey());
        }else{
            message.setMessage("删除失败");
        }
        return message;
    }

    @Override
    public Message updateBaseProperties(BaseProperties property) {
        Message message = new Message();
        if(property == null){
            property = new BaseProperties();
        }

        if(StringUtils.isBlank(property.getPropertyKey())){
            message.setMessage("属性key不能为空");
            return message;
        }
        if(StringUtils.isBlank(property.getPropertyValue())){
            message.setMessage("属性value不能为空");
            return message;
        }
        if(StringUtils.isBlank(property.getGroupKey())){
            message.setMessage("属性组key不能为空");
            return message;
        }

        BaseUser baseUser = (BaseUser) SecurityUtils.getSubject().getPrincipal();

        // 先判断propertyKey是否存在，来确认是添加还是修改
        if(StringUtils.isBlank(property.getPid())){
            // 添加
            // 先验证key不能重复
            BasePropertiesExample propertyExample = new BasePropertiesExample();
            propertyExample.createCriteria().andPropertyKeyEqualTo(property.getPropertyKey());
            List<BaseProperties> propertyList = basePropertiesMapper.selectByExample(propertyExample);
            if(propertyList.size() > 0){
                message.setMessage("属性key已经重复");
                return message;
            }
            property.setPid(UUID.randomUUID().toString());
            // 添加时间
            property.setCreTime(new Date());

            property.setCreUser(baseUser.getLoginName());
            Integer success = basePropertiesMapper.insertSelective(property);
            if(success ==1){
                message.setSuccess(true);
                message.setMessage("更新成功");
            }

        }else{
            // 更新
            // 先验证key不能重复
            BasePropertiesExample propertyExample = new BasePropertiesExample();
            propertyExample.createCriteria().andPropertyKeyEqualTo(property.getPropertyKey())
                    .andPidEqualTo(property.getPid());
            List<BaseProperties> propertyList = basePropertiesMapper.selectByExample(propertyExample);
            if(propertyList.size() > 0){
                message.setMessage("属性key已经重复");
                return message;
            }

            // 添加时间
            property.setModTime(new Date());
            property.setModUser(baseUser.getLoginName());
            Integer success = basePropertiesMapper.updateByPrimaryKeySelective(property);
            if(success ==1){
                message.setSuccess(true);
                message.setMessage("更新成功");
            }
        }

        if(message.getSuccess() == true){
            // 更新缓存,属性以及属性组
            redisService.setPropertiesOneRedis(property.getPropertyKey());
            redisService.setPropertiesGroupRedis(property.getGroupKey());
        }

        return message;
    }

    @Override
    public Message removeBasePropertiesGroup(BasePropertiesGroup propertyGroup) {
        Message message = new Message();
        if(StringUtils.isBlank(propertyGroup.getGid())){
            message.setMessage("gid不能为空");
            return message;
        }
        if(StringUtils.isBlank(propertyGroup.getGroupKey())){
            message.setMessage("GroupKey不能为空");
            return message;
        }

        //先判断这个属性组是否有属性，如果有，则不能删除
        BasePropertiesExample propertyExample = new BasePropertiesExample();
        propertyExample.createCriteria()
                .andGroupKeyEqualTo(propertyGroup.getGroupKey());
        List<BaseProperties> propertyList = basePropertiesMapper.selectByExample(propertyExample);

        if(propertyList.size() > 0){
            message.setMessage("该group下还存在property！");
            return message;
        }

        Integer success = basePropertiesGroupMapper.deleteByPrimaryKey(propertyGroup.getGid());
        if(success == 1){
            message.setSuccess(true);
            message.setMessage("删除成功");
            // 删除缓存
            redisService.removePropertiesGroupRedis(propertyGroup.getGroupCode());
        }

        return message;
    }

    @Override
    public Message updateBasePropertiesGroup(BasePropertiesGroup propertyGroup) {
        Message message = new Message();
        if(propertyGroup == null){
            propertyGroup = new BasePropertiesGroup();
        }

        if(StringUtils.isBlank(propertyGroup.getGroupCode())){
            message.setMessage("属性组code不能为空");
            return message;
        }
        if(StringUtils.isBlank(propertyGroup.getParentCode())){
            message.setMessage("属性组parCode不能为空");
            return message;
        }
        if(StringUtils.isBlank(propertyGroup.getGroupKey())){
            message.setMessage("属性组key不能为空");
            return message;
        }
        if(StringUtils.isBlank(propertyGroup.getGroupName())){
            message.setMessage("属性组name不能为空");
            return message;
        }

        BaseUser baseUser = (BaseUser) SecurityUtils.getSubject().getPrincipal();
        // 先判断propertyKey是否存在，来确认是添加还是修改
        if(StringUtils.isBlank(propertyGroup.getGid())){
            // 添加
            // 先验证key不能重复
            BasePropertiesGroupExample propertyGroupExample = new BasePropertiesGroupExample();
            propertyGroupExample.createCriteria().andGroupKeyEqualTo(propertyGroup.getGroupKey());
            List<BasePropertiesGroup> propertyGroupList = basePropertiesGroupMapper.selectByExample(propertyGroupExample);
            if(propertyGroupList.size() > 0){
                message.setMessage("属性组key已经重复");
                return message;
            }
            propertyGroup.setGid(UUID.randomUUID().toString());
            propertyGroup.setSeqNo(999);
            // 添加时间
            propertyGroup.setCreTime(new Date());
            propertyGroup.setCreUser(baseUser.getLoginName());
            Integer success = basePropertiesGroupMapper.insertSelective(propertyGroup);
            if(success ==1){
                message.setSuccess(true);
                message.setMessage("更新成功");
            }

        }else{
            // 更新
            // 先验证key不能重复
            BasePropertiesGroupExample propertyGroupExample = new BasePropertiesGroupExample();
            propertyGroupExample.createCriteria().andGroupKeyEqualTo(propertyGroup.getGroupKey())
                    .andGidNotEqualTo(propertyGroup.getGid());
            List<BasePropertiesGroup> propertyGroupList = basePropertiesGroupMapper.selectByExample(propertyGroupExample);
            if(propertyGroupList.size() > 0){
                message.setMessage("属性key已经重复");
                return message;
            }
            //更新的话不修改归属
            propertyGroup.setParentCode(null);

            // 添加时间
            propertyGroup.setModTime(new Date());
            propertyGroup.setModUser(baseUser.getLoginName());
            Integer success = basePropertiesGroupMapper.updateByPrimaryKeySelective(propertyGroup);
            if(success ==1){
                message.setSuccess(true);
                message.setMessage("更新成功");
            }
        }

        if(message.getSuccess() == true){
            // 更新缓存
            redisService.setPropertiesGroupRedis(propertyGroup.getGroupCode());
        }
        return message;
    }




}

