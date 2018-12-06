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
import org.zyyd.base.service.BaseRoleService;
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
public class BaseRoleServiceImpl extends BasicService implements BaseRoleService {

    @Autowired
    private RedisService redisService;


    @Autowired
    private BaseRoleMapper baseRoleMapper;

    @Autowired
    private BasePermissionMapper basePermissionMapper;

    @Autowired
    private BaseRolePermissionMapMapper baseRolePermissionMapMapper;

    @Autowired
    private BaseUserRoleMapMapper baseUserRoleMapMapper;






    @Override
    public List<BaseRole> listBaseRole() {
        List<BaseRole> baseRoleList = new ArrayList<>();
        baseRoleList = baseRoleMapper.selectByExampleWithBLOBs(new BaseRoleExample());
        return baseRoleList;
    }

    @Override
    public Message updateBaseRole(BaseRole baseRole) {
        Message message = new Message();
        if(baseRole == null){
            baseRole = new BaseRole();
        }

        if(StringUtils.isBlank(baseRole.getRoleName())){
            message.setMessage("角色名不能为空");
            return message;
        }
        if(StringUtils.isBlank(baseRole.getRoleCode())){
            message.setMessage("角色code不能为空");
            return message;
        }

        BaseUser baseUser = (BaseUser) SecurityUtils.getSubject().getPrincipal();

        // 先判断propertyKey是否存在，来确认是添加还是修改
        if(StringUtils.isBlank(baseRole.getRoleId())){
            // 添加
            // 先验证key不能重复
            BaseRoleExample baseRoleExample = new BaseRoleExample();
            BaseRoleExample.Criteria criteria = baseRoleExample.createCriteria();
            criteria.andRoleCodeEqualTo(baseRole.getRoleCode());
            BaseRoleExample.Criteria criteria2 = baseRoleExample.createCriteria();
            criteria2.andRoleNameEqualTo(baseRole.getRoleName());
            baseRoleExample.or(criteria2);
            List<BaseRole> baseRoleList = baseRoleMapper.selectByExample(baseRoleExample);
            if(baseRoleList.size() > 0){
                message.setMessage("角色名或角色code已经重复");
                return message;
            }
            baseRole.setRoleId(UUID.randomUUID().toString());
            // 添加时间
            baseRole.setCreTime(new Date());

            baseRole.setCreUser(baseUser.getLoginName());
            Integer success = baseRoleMapper.insertSelective(baseRole);
            if(success ==1){
                message.setSuccess(true);
                message.setMessage("更新成功");
            }

        }else{
            // 更新
            // 先验证key不能重复
            BaseRoleExample baseRoleExample = new BaseRoleExample();
            BaseRoleExample.Criteria criteria = baseRoleExample.createCriteria();
            criteria.andRoleCodeEqualTo(baseRole.getRoleCode()).andRoleIdNotEqualTo(baseRole.getRoleId());
            BaseRoleExample.Criteria criteria2 = baseRoleExample.createCriteria();
            criteria2.andRoleNameEqualTo(baseRole.getRoleName()).andRoleIdNotEqualTo(baseRole.getRoleId());
            baseRoleExample.or(criteria2);
            List<BaseRole> baseRoleList = baseRoleMapper.selectByExample(baseRoleExample);
            if(baseRoleList.size() > 0){
                message.setMessage("角色名或角色code已经重复");
                return message;
            }

            // 添加时间
            baseRole.setModTime(new Date());
            baseRole.setModUser(baseUser.getLoginName());
            Integer success = baseRoleMapper.updateByPrimaryKeySelective(baseRole);
            if(success ==1){
                message.setSuccess(true);
                message.setMessage("更新成功");
            }
        }
        if(message.getSuccess() == true){
            // 更新缓存
            redisService.setRoleOneRedis(baseRole.getRoleCode());
            redisService.setRoleRedis(baseRole.getRoleCode());
        }
        return message;
    }


    @Override
    public Message removeBaseRoleById(String roleId) {
        Message message = new Message();
        BaseRoleExample baseRoleExample = new BaseRoleExample();
        baseRoleExample.createCriteria().andRoleIdEqualTo(roleId);
        BaseRole baseRole = new BaseRole();
        baseRole = baseRoleMapper.selectByPrimaryKey(roleId);
        Integer success = baseRoleMapper.deleteByPrimaryKey(roleId);
        if(success > 0){
            message.setSuccess(true);
            message.setMessage("删除成功");
            // 删除缓存
            redisService.removeRoleOneRedis(baseRole.getRoleCode());
            redisService.removeRoleRedis(baseRole.getRoleCode());
        }
        return message;
    }


    @Override
    public List<BasePermission> listBasePermission(BasePermission basePermission) {
        BasePermissionExample basePermissionExample = new BasePermissionExample();
        if(StringUtils.isBlank(basePermission.getPermissionId())){
            basePermissionExample.createCriteria().andDeleteFlagEqualTo(0);
        }else{
            basePermissionExample.createCriteria().andPermissionIdEqualTo(basePermission.getPermissionId()).andDeleteFlagEqualTo(0);
        }
        List<BasePermission> basePermissionList = new ArrayList<>();
        basePermissionList = basePermissionMapper.selectByExample(basePermissionExample);
        return basePermissionList;
    }

    @Override
    public Set<String> listBasePermissionChecked(BaseRole baseRole) {
        Set<String> pCodeSet = new HashSet<>();
        if(StringUtils.isNotBlank(baseRole.getRoleId())){
            BaseRolePermissionMapExample baseRolePermissionMapExample = new BaseRolePermissionMapExample();
            baseRolePermissionMapExample.createCriteria().andRoleCodeEqualTo(baseRole.getRoleCode());
            List<BaseRolePermissionMap> baseRolePermissionMaps = baseRolePermissionMapMapper.selectByExample(baseRolePermissionMapExample);
            if(baseRolePermissionMaps.size() > 0){
                for (Iterator<BaseRolePermissionMap> iterator = baseRolePermissionMaps.iterator(); iterator.hasNext(); ) {
                    BaseRolePermissionMap baseRolePermissionMap = iterator.next();
                    pCodeSet.add(baseRolePermissionMap.getPermissionCode());
                }
            }
        }
        return pCodeSet;
    }

    @Override
    public Message removeBasePermissionById(BasePermission basePermission) {

        Message message = new Message();

        // 删除权限
        BasePermissionExample basePermissionExample = new BasePermissionExample();
        //先查询该id是否有子权限，如果有，不允许删除
        basePermissionExample.createCriteria().andParentCodeEqualTo(basePermission.getCode())
                .andDeleteFlagEqualTo(0);
        List<BasePermission> basePermissionList = basePermissionMapper.selectByExample(basePermissionExample);
        if(basePermissionList.size() > 0){
            message.setMessage("该权限下有子权限，请先删除子权限");
        }else{
            basePermissionExample.createCriteria().andPermissionIdEqualTo(basePermission.getPermissionId());
            basePermission.setDeleteFlag(1);
            Integer success = basePermissionMapper.updateByPrimaryKeySelective(basePermission);
            message.setSuccess(true);
            message.setMessage("删除成功");
            // 更新缓存,重跑所有的角色缓存，删除这个权限的缓存
            redisService.removePermissionOneRedis(basePermission.getCode());
            redisService.setRoleRedis();
        }

        return message;
    }


    @Override
    public Message updateBasePermission(BasePermission basePermission) {
        Message message = new Message();
        if(basePermission == null){
            basePermission = new BasePermission();
        }

        if(StringUtils.isBlank(basePermission.getCode())){
            message.setMessage("权限code不能为空");
            return message;
        }
        if(StringUtils.isBlank(basePermission.getPermissionName())){
            message.setMessage("权限name不能为空");
            return message;
        }

        BaseUser baseUser = (BaseUser) SecurityUtils.getSubject().getPrincipal();
        // 先判断propertyKey是否存在，来确认是添加还是修改
        if(StringUtils.isBlank(basePermission.getPermissionId())){
            // 添加
            // 先验证key不能重复
            BasePermissionExample basePermissionExample = new BasePermissionExample();
            basePermissionExample.createCriteria().andCodeEqualTo(basePermission.getCode()).andDeleteFlagEqualTo(0);
            List<BasePermission> basePermissionList = basePermissionMapper.selectByExample(basePermissionExample);
            if(basePermissionList.size() > 0){
                message.setMessage("权限code已经重复");
                return message;
            }
            basePermission.setPermissionId(UUID.randomUUID().toString());
            // 添加时间
            basePermission.setCreTime(new Date());
            basePermission.setCreUser(baseUser.getLoginName());
            Integer success = basePermissionMapper.insertSelective(basePermission);
            if(success ==1){
                message.setSuccess(true);
                message.setMessage("保存成功");
            }

        }else{
            // 更新
            // 先验证key不能重复
            BasePermissionExample basePermissionExample = new BasePermissionExample();
            basePermissionExample.createCriteria().andCodeEqualTo(basePermission.getCode())
                    .andPermissionIdNotEqualTo(basePermission.getPermissionId()).andDeleteFlagEqualTo(0);
            List<BasePermission> basePermissionList = basePermissionMapper.selectByExample(basePermissionExample);
            if(basePermissionList.size() > 0){
                message.setMessage("权限code已经重复");
                return message;
            }
            // 添加时间
            basePermission.setModTime(new Date());
            basePermission.setModUser(baseUser.getLoginName());
            Integer success = basePermissionMapper.updateByPrimaryKeySelective(basePermission);
            if(success ==1){
                message.setSuccess(true);
                message.setMessage("保存成功");
            }
        }

        if(message.getSuccess() == true){
            // 更新缓存,重跑所有的角色缓存
            redisService.setPermissionOneRedis(basePermission.getParentCode());
            redisService.setRoleRedis();
        }
        return message;
    }

    @Override
    public Message updateBasePermissionChecked(BaseRolePermissionMap baseRolePermissionMap) {

        Message message = new Message();

        String roleCode = baseRolePermissionMap.getRoleCode();
        String permissionCodes = baseRolePermissionMap.getPermissionCode();
        // 先删除该role的所有权限，再重新赋值
        BaseRolePermissionMapExample baseRolePermissionMapExample = new BaseRolePermissionMapExample();
        baseRolePermissionMapExample.createCriteria().andRoleCodeEqualTo(roleCode);
        baseRolePermissionMapMapper.deleteByExample(baseRolePermissionMapExample);

        if(StringUtils.isBlank(permissionCodes)){
            // 因为是空的，所以只用删除
        }else{
            // 不为空，先分割再循环添加
            String pids[] = permissionCodes.split(",");
            baseRolePermissionMap = new BaseRolePermissionMap();
            baseRolePermissionMap.setRoleCode(roleCode);

            for (int i = 0; i < pids.length; i++) {
                baseRolePermissionMap.setMapId(UUID.randomUUID().toString());
                baseRolePermissionMap.setPermissionCode(pids[i]);
                baseRolePermissionMapMapper.insertSelective(baseRolePermissionMap);
            }

        }
        message.setSuccess(true);
        message.setMessage("权限更新成功");

        // 更新缓存
        redisService.setRoleRedis(roleCode);

        // 更新shiro的权限缓存
        //this.clearShiro();

        return message;
    }



}

