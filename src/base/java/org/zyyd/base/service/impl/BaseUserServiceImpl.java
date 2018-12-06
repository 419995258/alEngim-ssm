package org.zyyd.base.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zyyd.base.dao.BasePermissionMapper;
import org.zyyd.base.dao.BasePropertiesGroupMapper;
import org.zyyd.base.dao.BasePropertiesMapper;
import org.zyyd.base.dao.BaseRoleMapper;
import org.zyyd.base.dao.BaseRolePermissionMapMapper;
import org.zyyd.base.dao.BaseUserMapper;
import org.zyyd.base.dao.BaseUserRoleMapMapper;
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
import org.zyyd.base.entity.BaseRolePermissionMapExample;
import org.zyyd.base.entity.BaseUser;
import org.zyyd.base.entity.BaseUserExample;
import org.zyyd.base.entity.vo.BaseUserVO;
import org.zyyd.base.entity.vo.Message;
import org.zyyd.base.entity.vo.ResultVo;
import org.zyyd.base.service.BaseUserService;
import org.zyyd.base.service.RedisService;
import org.zyyd.base.util.BasicService;
import org.zyyd.base.util.DateFormater;
import org.zyyd.base.util.MD5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import shiro.ShiroRealm;

@Service
public class BaseUserServiceImpl extends BasicService implements BaseUserService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private BaseUserMapper baseUserMapper;

    @Autowired
    private UserMapperExt userMapperExt;

    @Autowired
    private SessionDAO sessionDAO;



    @Override
    public List<BaseUser> getUserList(BaseUser user) {
        List<BaseUser> userList = new ArrayList<>();
        BaseUserExample userExample = new BaseUserExample();
        userExample.createCriteria().andLoginNameEqualTo(user.getLoginName())
                .andPassWordEqualTo(user.getPassWord());
        userList = baseUserMapper.selectByExample(userExample);

        //test1 = userMapper.test();

        //test1 = userMapper.selectRoleByUserId(1);

        //test1 = userMapper.selectRolePermissionByUserId(1);
        return userList;
    }

    /**
     *
     * @Description:
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/10/31 14:15
     */
    @Override
    public BaseUser getUser(BaseUser user) throws Exception {
        BaseUserExample userExample = new BaseUserExample();
        userExample.createCriteria().andLoginNameEqualTo(user.getLoginName())
                .andPassWordEqualTo(user.getPassWord());

        List<BaseUser> userList = new ArrayList<>();
        userList = baseUserMapper.selectByExample(userExample);

        if(userList.size() > 0){
            user = userList.get(0);
        }else{
            user = new BaseUser();
        }

        return user;
    }



    /**
     * 获取user通过username
     * @Description:
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/10/31 14:26
     */
    @Override
    public BaseUser getUserByUserName(String userName)  {
        BaseUser user = new BaseUser();
        BaseUserExample userExample = new BaseUserExample();
        userExample.createCriteria().andLoginNameEqualTo(userName).andUserStatusEqualTo("1");

        List<BaseUser> userList = new ArrayList<>();
        userList = baseUserMapper.selectByExample(userExample);

        if(userList.size() > 0){
            user = userList.get(0);
        }else{
            user = new BaseUser();
        }

        return user;
    }


    @Override
    public JSONArray getActivityUser() {
        Collection<Session> sessions = sessionDAO.getActiveSessions();

        JSONArray jsonArray = new JSONArray();
        JSONObject json = new JSONObject();
        BaseUser baseUser = new BaseUser();
        for(Session session:sessions){
            json = new JSONObject();
            baseUser = new BaseUser();

            json.put("id",session.getId());
            PrincipalCollection principalCollection = (PrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            baseUser = (BaseUser) principalCollection.getPrimaryPrincipal();
            json.put("loginName",baseUser.getLoginName());
            json.put("realName",baseUser.getRealname());
            json.put("host",session.getHost());


            json.put("lastAccessTime", DateFormater.DateToString(session.getLastAccessTime(),DateFormater.DATE_STYLE5));
            jsonArray.add(json);
        }
        return jsonArray;
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
                ConvertUtils.register(new DateConverter(null), Date.class);
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

