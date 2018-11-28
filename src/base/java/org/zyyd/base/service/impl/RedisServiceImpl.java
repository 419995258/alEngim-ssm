package org.zyyd.base.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zyyd.base.dao.BasePermissionMapper;
import org.zyyd.base.dao.BasePropertiesGroupMapper;
import org.zyyd.base.dao.BasePropertiesMapper;
import org.zyyd.base.dao.BaseRoleMapper;
import org.zyyd.base.dao.BaseUserMapper;
import org.zyyd.base.dao.vo.UserMapperExt;
import org.zyyd.base.entity.BasePermission;
import org.zyyd.base.entity.BasePermissionExample;
import org.zyyd.base.entity.BaseProperties;
import org.zyyd.base.entity.BasePropertiesExample;
import org.zyyd.base.entity.BasePropertiesGroup;
import org.zyyd.base.entity.BasePropertiesGroupExample;
import org.zyyd.base.entity.BaseRole;
import org.zyyd.base.entity.BaseRoleExample;
import org.zyyd.base.entity.BaseRolePermissionMap;
import org.zyyd.base.entity.BaseUser;
import org.zyyd.base.entity.BaseUserExample;
import org.zyyd.base.service.RedisService;
import org.zyyd.base.service.UserService;
import org.zyyd.base.util.BasicService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import redis.RedisUtil;

import static redis.RedisCacheConsts.BASE_PERMISSION_CACHE_CODE;
import static redis.RedisCacheConsts.BASE_PROPERTIES_CACHE_CODE;
import static redis.RedisCacheConsts.BASE_PROPERTIES_GROUP_CACHE_CODE;
import static redis.RedisCacheConsts.BASE_ROLE_CACHE_CODE;
import static redis.RedisCacheConsts.BASE_ROLE_ONE_CACHE_CODE;


/**
 * ClassName: RedisServiceImpl
 * @Description:
 * @author pengbin <pengbin>
 * @date  2018/11/25 16:03
 */

@Service
public class RedisServiceImpl extends BasicService implements RedisService {


    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private BasePropertiesGroupMapper basePropertiesGroupMapper;

    @Autowired
    private BasePropertiesMapper basePropertiesMapper;

    @Autowired
    private BaseRoleMapper baseRoleMapper;

    @Autowired
    private BasePermissionMapper basePermissionMapper;

    @Autowired
    private UserMapperExt userMapperExt;


    @Override
    public void setPropertiesGroupRedis() {

        BasePropertiesGroupExample propertyGroupExample = new BasePropertiesGroupExample();
        List<BasePropertiesGroup> propertyGroupList = basePropertiesGroupMapper.selectByExample(propertyGroupExample);
        if(propertyGroupList.size() > 0){
            BasePropertiesExample propertyExample = new BasePropertiesExample();
            propertyExample.setOrderByClause(" seq_no desc");
            BasePropertiesExample.Criteria cr = propertyExample.createCriteria();
            List<BaseProperties> propertyList = new ArrayList<>();
            for (Iterator<BasePropertiesGroup> iterator = propertyGroupList.iterator(); iterator.hasNext(); ) {
                BasePropertiesGroup propertyGroup = iterator.next();
                cr = propertyExample.createCriteria();
                cr.andGroupKeyEqualTo(propertyGroup.getGroupKey());
                propertyList = basePropertiesMapper.selectByExample(propertyExample);
                //放置于redis
                redisUtil.set(BASE_PROPERTIES_GROUP_CACHE_CODE + propertyGroup.getGroupKey(),propertyList);
            }
        }
    }

    @Override
    public void setPropertiesGroupRedis(String groupCode) {
        BasePropertiesGroupExample propertyGroupExample = new BasePropertiesGroupExample();
        propertyGroupExample.createCriteria().andGroupCodeEqualTo(groupCode);
        List<BasePropertiesGroup> propertyGroupList = basePropertiesGroupMapper.selectByExample(propertyGroupExample);
        if(propertyGroupList.size() > 0){
            BasePropertiesExample propertyExample = new BasePropertiesExample();
            propertyExample.setOrderByClause(" seq_no desc");
            BasePropertiesExample.Criteria cr = propertyExample.createCriteria();
            List<BaseProperties> propertyList = new ArrayList<>();
            for (Iterator<BasePropertiesGroup> iterator = propertyGroupList.iterator(); iterator.hasNext(); ) {
                BasePropertiesGroup propertyGroup = iterator.next();
                cr = propertyExample.createCriteria();
                cr.andGroupKeyEqualTo(propertyGroup.getGroupKey());
                propertyList = basePropertiesMapper.selectByExample(propertyExample);
                //放置于redis
                redisUtil.set(BASE_PROPERTIES_GROUP_CACHE_CODE + groupCode,propertyList);
            }
        }
    }

    @Override
    public List<BaseProperties> listPropertiesGroupRedis(String groupCode) {
        List<BaseProperties> list = new ArrayList<>();
        list = (List<BaseProperties>) redisUtil.get(BASE_PROPERTIES_GROUP_CACHE_CODE + groupCode);
        if(list == null || list.size() < 1){
            this.setPropertiesGroupRedis(groupCode);
            list = (List<BaseProperties>) redisUtil.get(BASE_PROPERTIES_GROUP_CACHE_CODE + groupCode);
        }
        return list;
    }

    @Override
    public void removePropertiesGroupRedis(String groupCode) {
        Object o=redisUtil.get(BASE_PROPERTIES_GROUP_CACHE_CODE + groupCode);
        if(o!=null){
            redisUtil.removeHashValue(BASE_PROPERTIES_GROUP_CACHE_CODE + groupCode);
        }
    }

    @Override
    public void setPropertiesOneRedis() {
        BasePropertiesExample basePropertiesExample = new BasePropertiesExample();
        List<BaseProperties> basePropertiesList = basePropertiesMapper.selectByExampleWithBLOBs(basePropertiesExample);
        if(basePropertiesList.size() > 0){
            for (Iterator<BaseProperties> iterator = basePropertiesList.iterator(); iterator.hasNext(); ) {
                BaseProperties baseProperties = iterator.next();
                redisUtil.set(BASE_PROPERTIES_CACHE_CODE + baseProperties.getPropertyKey(),baseProperties);
            }
        }
    }

    @Override
    public void setPropertiesOneRedis(String key) {
        BasePropertiesExample basePropertiesExample = new BasePropertiesExample();
        basePropertiesExample.createCriteria().andPropertyKeyEqualTo(key);
        List<BaseProperties> basePropertiesList = basePropertiesMapper.selectByExampleWithBLOBs(basePropertiesExample);
        if(basePropertiesList.size() > 0){
            BaseProperties baseProperties = basePropertiesList.get(0);
            redisUtil.set(BASE_PROPERTIES_CACHE_CODE + baseProperties.getPropertyKey(),baseProperties);
        }
    }

    @Override
    public BaseProperties getPropertiesOneRedis(String key) {
        BaseProperties baseProperties = new BaseProperties();
        baseProperties = (BaseProperties) redisUtil.get(BASE_PROPERTIES_CACHE_CODE + key);
        if(baseProperties == null){
            this.setPropertiesOneRedis(key);
            baseProperties = (BaseProperties) redisUtil.get(BASE_PROPERTIES_CACHE_CODE + key);
        }
        return baseProperties;
    }

    @Override
    public void removePropertiesOneRedis(String key) {
        BaseProperties baseProperties = this.getPropertiesOneRedis(key);
        if(baseProperties != null){
            redisUtil.removeHashValue(BASE_PROPERTIES_CACHE_CODE + key);
        }
    }

    @Override
    public void setRoleRedis() {
        BaseRoleExample baseRoleExample = new BaseRoleExample();
        List<BaseRole> baseRoleList = new ArrayList<>();
        List<BasePermission> basePermissionList = new ArrayList<>();

        // 循环遍历角色
        baseRoleList = baseRoleMapper.selectByExampleWithBLOBs(baseRoleExample);
        if(baseRoleList.size() > 0){
            for (Iterator<BaseRole> iterator = baseRoleList.iterator(); iterator.hasNext(); ) {
                BaseRole next = iterator.next();
                // 通过角色获取权限
                basePermissionList = userMapperExt.selectRolePermissionByRoleCode(next.getRoleCode());

                //放置于redis
                redisUtil.set(BASE_ROLE_CACHE_CODE + next.getRoleCode(),basePermissionList);
            }


        }

    }


    @Override
    public void setRoleRedis(String roleCode) {
        List<BasePermission> basePermissionList = new ArrayList<>();
        // 通过角色获取权限
        basePermissionList = userMapperExt.selectRolePermissionByRoleCode(roleCode);

        //放置于redis
        redisUtil.set(BASE_ROLE_CACHE_CODE + roleCode,basePermissionList);
    }

    @Override
    public List<BasePermission> listPermissionRedis(String roleCode) {
        List<BasePermission> basePermissionList = new ArrayList<>();
        basePermissionList = (List<BasePermission>)redisUtil.get(BASE_ROLE_CACHE_CODE + roleCode);

        if(basePermissionList == null || basePermissionList.size() < 1){
            this.setRoleRedis(roleCode);
            basePermissionList = (List<BasePermission>)redisUtil.get(BASE_ROLE_CACHE_CODE + roleCode);
        }

        return basePermissionList;
    }

    @Override
    public void removeRoleRedis(String roleCode) {
        Object o=redisUtil.get(BASE_ROLE_CACHE_CODE + roleCode);
        if(o!=null){
            redisUtil.removeHashValue(BASE_ROLE_CACHE_CODE + roleCode);
        }
    }

    @Override
    public void setRoleOneRedis() {
        //BASE_ROLE_ONE_CACHE_CODE
        BaseRoleExample baseRoleExample = new BaseRoleExample();
        List<BaseRole> baseRoleList = new ArrayList<>();
        baseRoleList = baseRoleMapper.selectByExampleWithBLOBs(baseRoleExample);
        if(baseRoleList.size() > 0){
            for (Iterator<BaseRole> iterator = baseRoleList.iterator(); iterator.hasNext(); ) {
                BaseRole baseRole = iterator.next();
                redisUtil.set(BASE_ROLE_ONE_CACHE_CODE + baseRole.getRoleCode(),baseRole);
            }
        }

    }

    @Override
    public void setRoleOneRedis(String roleCode) {
        BaseRoleExample baseRoleExample = new BaseRoleExample();
        baseRoleExample.createCriteria().andRoleCodeEqualTo(roleCode);
        List<BaseRole> baseRoleList = new ArrayList<>();
        baseRoleList = baseRoleMapper.selectByExampleWithBLOBs(baseRoleExample);
        if(baseRoleList.size() > 0){
            BaseRole baseRole = new BaseRole();
            baseRole = baseRoleList.get(0);
            redisUtil.set(BASE_ROLE_ONE_CACHE_CODE + baseRole.getRoleCode(),baseRole);
        }
    }

    @Override
    public BaseRole getRoleOneRedis(String roleCode) {
        BaseRole baseRole = new BaseRole();
        baseRole = (BaseRole) redisUtil.get(BASE_ROLE_ONE_CACHE_CODE + roleCode);
        if(baseRole == null){
            this.setRoleOneRedis(roleCode);
            baseRole = (BaseRole) redisUtil.get(BASE_ROLE_ONE_CACHE_CODE + roleCode);
        }
        return baseRole;
    }

    @Override
    public void removeRoleOneRedis(String roleCode) {
        Object o=redisUtil.get(BASE_ROLE_ONE_CACHE_CODE + roleCode);
        if(o!=null){
            redisUtil.removeHashValue(BASE_ROLE_ONE_CACHE_CODE + roleCode);
        }
    }

    @Override
    public void setPermissionOneRedis() {
        BasePermissionExample basePermissionExample = new BasePermissionExample();
        basePermissionExample.createCriteria().andDeleteFlagEqualTo(0);
        List<BasePermission> basePermissionList = basePermissionMapper.selectByExample(basePermissionExample);
        for (Iterator<BasePermission> iterator = basePermissionList.iterator(); iterator.hasNext(); ) {
            BasePermission basePermission = iterator.next();
            redisUtil.set(BASE_PERMISSION_CACHE_CODE + basePermission.getCode(),basePermission);
        }

    }

    @Override
    public void setPermissionOneRedis(String code) {
        BasePermissionExample basePermissionExample = new BasePermissionExample();
        basePermissionExample.createCriteria().andDeleteFlagEqualTo(0).andCodeEqualTo(code);
        List<BasePermission> basePermissionList = basePermissionMapper.selectByExample(basePermissionExample);
        if(basePermissionList.size() > 0){
            BasePermission basePermission = basePermissionList.get(0);
            redisUtil.set(BASE_PERMISSION_CACHE_CODE + code,basePermission);
        }
    }

    @Override
    public BasePermission getPermissionOneRedis(String code) {
        BasePermission basePermission = new BasePermission();
        basePermission = (BasePermission) redisUtil.get(BASE_PERMISSION_CACHE_CODE + code);
        if(basePermission == null){
            this.setPermissionOneRedis(code);
            basePermission = (BasePermission) redisUtil.get(BASE_PERMISSION_CACHE_CODE + code);
        }
        return basePermission;
    }

    @Override
    public void removePermissionOneRedis(String code) {
        Object o=redisUtil.get(BASE_PERMISSION_CACHE_CODE + code);
        if(o!=null){
            redisUtil.removeHashValue(BASE_PERMISSION_CACHE_CODE + code);
        }
    }
}
