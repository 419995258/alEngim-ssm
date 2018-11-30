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
import org.zyyd.base.service.AdminService;
import org.zyyd.base.service.RedisService;
import shiro.ShiroRealm;
import org.zyyd.base.util.BasicService;
import org.zyyd.base.util.MD5;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class AdminServiceImpl extends BasicService implements AdminService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private BaseUserMapper baseUserMapper;

    @Autowired
    private BasePropertiesMapper basePropertiesMapper;

    @Autowired
    private BaseRoleMapper baseRoleMapper;

    @Autowired
    private BasePropertiesGroupMapper basePropertiesGroupMapper;

    @Autowired
    private BasePermissionMapper basePermissionMapper;

    @Autowired
    private BaseRolePermissionMapMapper baseRolePermissionMapMapper;

    @Autowired
    private BaseUserRoleMapMapper baseUserRoleMapMapper;



    @Override
    public List<BasePropertiesGroup> listBasePropertiesGroup() {
        BasePropertiesGroupExample propertyGroupExample = new BasePropertiesGroupExample();
        propertyGroupExample.setOrderByClause(" seq_no desc");
        List<BasePropertiesGroup> propertyGroupList = basePropertiesGroupMapper.selectByExample(propertyGroupExample);
        return propertyGroupList;
    }

    @Override
    public List<BaseProperties> listBasePropertiesByPropertyGroupId(String groupKey) {
        BasePropertiesExample propertyExample = new BasePropertiesExample();
        propertyExample.setOrderByClause(" seq_no desc");
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


    @Override
    public ResultVo listBaseUserList(BaseUserVO baseUserVO) {
        ResultVo resultVo = new ResultVo();
        try {
            this.setPageInfo(baseUserVO.getPageNo());

            BaseUserExample baseUserExample = new BaseUserExample();
            List<BaseUser> userList = new ArrayList<>();
            userList = baseUserMapper.selectByExample(baseUserExample);
            this.setReturnPageInfo(baseUserVO.getPageNo(),userList,resultVo);

            List<BaseUserVO> userVOList = new ArrayList<>();
            BaseUserVO bVO = new BaseUserVO();
            List<String> roleCodeList = new ArrayList<>();
            for (Iterator<BaseUser> iterator = userList.iterator(); iterator.hasNext(); ) {
                BaseUser next = iterator.next();
                bVO = new BaseUserVO();
                roleCodeList = new ArrayList<>();
                ConvertUtils.register(new DateConverter(null), java.util.Date.class);
                BeanUtils.copyProperties(bVO,next);

                String[] roleCodes = new String[]{};
                String roleCodesStr = "";
                if(StringUtils.isNotBlank(next.getRoleCodes())){
                    roleCodes = next.getRoleCodes().split("#");
                    // 通过拆分roleCodes从redis获取名字
                    if(roleCodes.length > 0){
                        for (int i = 0; i < roleCodes.length; i++) {
                            BaseRole baseRole = redisService.getRoleOneRedis(roleCodes[i]);
                            // 如果是空，跳过这个
                            if(baseRole == null){
                                continue;
                            }
                            String roleName = baseRole.getRoleName();
                            roleCodesStr = roleCodesStr + roleName + ",";
                        }
                    }

                }

                // 截取最后的,
                if(StringUtils.isNotBlank(roleCodesStr)){
                    roleCodesStr = roleCodesStr.substring(0,roleCodesStr.length()-1);
                }
                bVO.setRoleCodesList(roleCodes);
                bVO.setRoleCodes(roleCodesStr);
                bVO.setPassWord("");
                userVOList.add(bVO);
            }
            resultVo.setRows(userVOList);


        } catch (Exception e) {
            e.printStackTrace();
        }


        return resultVo;
    }


    @Override
    public Message removeUser(BaseUser baseUser) {
        Message message = new Message();
        Integer success = baseUserMapper.deleteByPrimaryKey(baseUser.getUserId());
        if(success > 0){
            message.setSuccess(true);
        }else{
            message.setMessage("更新失败");
        }
        return message;
    }

    @Override
    public Message updateUserStatus(BaseUserVO baseUserVO) {
        BaseUser user = (BaseUser) SecurityUtils.getSubject().getPrincipal();
        Message message = new Message();
        BaseUser baseUser = new BaseUser();
        baseUser.setUserId(baseUserVO.getUserId());
        baseUser.setUserStatus(baseUserVO.getUserStatus());
        baseUser.setModTime(new Date());
        baseUser.setModUser(user.getLoginName());
        Integer success = baseUserMapper.updateByPrimaryKeySelective(baseUser);
        if(success > 0){
            message.setSuccess(true);
        }else{
            message.setMessage("更新失败");
        }
        return message;
    }

    @Override
    public Message updateBaseUser(BaseUserVO baseUserVO) {
        BaseUser baseUser = new BaseUser();
        Message message = new Message();
        if(baseUserVO == null){
            baseUserVO = new BaseUserVO();
        }

        if(StringUtils.isBlank(baseUserVO.getLoginName())){
            message.setMessage("loginName不能为空");
            return message;
        }

        /*try {
            BeanUtils.copyProperties(baseUser, baseUserVO);
        } catch (Exception e) {
            message.setMessage("发生错误！");
            e.printStackTrace();
            return message;
        }*/

//        List<String> roleCodesList = baseUserVO.getRoleCodesList();
        String[] roleCodesList = baseUserVO.getRoleCodesList();
        String roleCodes = "";
        /*if(roleCodesList != null ){
            for (Iterator<String> iterator = roleCodesList.iterator(); iterator.hasNext(); ) {
                String next = iterator.next();
                roleCodes = roleCodes + next + "#";
            }
        }*/
        if(roleCodesList.length > 0){
            for (int i = 0; i < roleCodesList.length; i++) {
                roleCodes = roleCodes + roleCodesList[i] + "#";
            }
        }

        baseUser.setLoginName(baseUserVO.getLoginName());
        baseUser.setRealname(baseUserVO.getRealname());
        baseUser.setUserStatus(baseUserVO.getUserStatus());
        // 密码更新的机制
        if(StringUtils.isBlank(baseUserVO.getPassWord())){
            // 是空，不更改
            //baseUser.setPassWord(MD5.getMd5("123456"));
        }else{
            baseUser.setPassWord(MD5.getMd5(baseUserVO.getPassWord()));
        }
        baseUser.setRoleCodes(roleCodes);

        BaseUser user = (BaseUser) SecurityUtils.getSubject().getPrincipal();
        // 先判断propertyKey是否存在，来确认是添加还是修改
        if(StringUtils.isBlank(baseUserVO.getUserId())){
            // 添加
            // 先验证key不能重复
            BaseUserExample baseUserExample = new BaseUserExample();
            baseUserExample.createCriteria().andLoginNameEqualTo(baseUser.getLoginName());
            List<BaseUser> baseUserList = baseUserMapper.selectByExample(baseUserExample);
            if(baseUserList.size() > 0){
                message.setMessage("loginName已经重复");
                return message;
            }
            baseUser.setUserId(UUID.randomUUID().toString());
            // 添加时间
            baseUser.setCreTime(new Date());
            baseUser.setCreUser(user.getLoginName());
            Integer success = baseUserMapper.insertSelective(baseUser);
            if(success ==1){
                message.setSuccess(true);
                message.setMessage("更新成功");
            }else{
                message.setMessage("更新失败");
            }

        }else{
            // 更新
            // 先验证key不能重复
            baseUser.setUserId(baseUserVO.getUserId());

            BaseUserExample baseUserExample = new BaseUserExample();
            baseUserExample.createCriteria().andLoginNameEqualTo(baseUser.getLoginName())
                    .andUserIdNotEqualTo(baseUser.getUserId());
            List<BaseUser> baseUserList = baseUserMapper.selectByExample(baseUserExample);
            if(baseUserList.size() > 0){
                message.setMessage("loginName已经重复");
                return message;
            }

            // 添加时间
            baseUser.setModTime(new Date());
            baseUser.setModUser(user.getLoginName());
            Integer success = baseUserMapper.updateByPrimaryKeySelective(baseUser);
            if(success > 0){
                message.setSuccess(true);
                message.setMessage("更新成功");
            }else{
                message.setMessage("更新失败");
            }

        }

        return message;
    }

    @Override
    public void clearShiro() {
        Subject subject = SecurityUtils.getSubject();
        RealmSecurityManager securityManager =
                (RealmSecurityManager) SecurityUtils.getSecurityManager();
        ShiroRealm shiroRealm = (ShiroRealm) securityManager.getRealms().iterator().next();
        //删除登陆人
        shiroRealm.getAuthorizationCache().remove(subject.getPrincipal());
        //删除登陆人对应的缓存
        shiroRealm.getAuthorizationCache().remove(subject.getPrincipals());
        //重新加载subject
        subject.releaseRunAs();
    }
}

