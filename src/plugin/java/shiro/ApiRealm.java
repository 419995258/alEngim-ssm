package shiro;


import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.zyyd.base.entity.BasePermission;
import org.zyyd.base.entity.BaseUser;
import org.zyyd.base.service.RedisService;
import org.zyyd.base.service.UserService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ClassName: ShiroRealm
 * @Description:
 * @author pengbin <pengbin>
 * @date  2018/11/19 13:55
 */
public class ApiRealm extends AuthorizingRealm {
    //private Logger logger =  LoggerFactory.getLogger(this.getClass());

    public boolean supports(AuthenticationToken token) {
        //仅支持StatelessToken类型的Token
        return token instanceof ApiToken;
    }

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;




    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //logger.info("doGetAuthorizationInfo+"+principalCollection.toString());

        //username:principalCollection.getPrimaryPrincipal()
        //BaseUser user = userService.getUserByUserName((String) principalCollection.getPrimaryPrincipal());
        BaseUser user = (BaseUser) principalCollection.getPrimaryPrincipal();

        //List<Map<String,Object>> test = userMapperExt.test();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 获取role
        //List<Map<String,Object>> roleList = new ArrayList<>();
        String roleCodesStr = user.getRoleCodes();
        List<BasePermission> permissionList = new ArrayList<>();
        if(StringUtils.isNotBlank(roleCodesStr)){
            String[] roleCodes = roleCodesStr.split("#");
            if(roleCodes.length > 0){
                for (int i = 0; i < roleCodes.length; i++) {
                    String roleCode = roleCodes[i];
                    if(StringUtils.isNotBlank(roleCode)){
                        info.addRole(roleCode);
                        // 获取permission
                        permissionList = redisService.listPermissionRedis(roleCode);
                        if(permissionList.size() > 0){
                            for (Iterator<BasePermission> iterator = permissionList.iterator(); iterator.hasNext(); ) {
                                BasePermission next =  iterator.next();
                                if(StringUtils.isNoneBlank(next.getCode())){
                                    info.addStringPermission(next.getCode());
                                }
                            }
                        }

                    }
                }
            }
        }
        //roleList = roleService.getRolesByUser(user.getId());

        //获取permission
        //List<Map<String,Object>> permissionList = new ArrayList<>();
//        permissionList = userService.getRolesPermissionByUser(user.getUserId());
//
        //把principals放session中 key=userId value=principals
       // SecurityUtils.getSubject().getSession().setAttribute(String.valueOf(user.getUserId()),SecurityUtils.getSubject().getPrincipals());


        //赋予角色
        //Set<String> roleNameSet = new HashSet<>();
        /*if(roleList.size() > 0){
            for (Iterator<Map<String, Object>> iterator = roleList.iterator(); iterator.hasNext(); ) {
                Map<String, Object> next = iterator.next();
                String roleName = (String) next.get("rname");
                if(StringUtils.isNoneBlank(roleName)){
                    info.addRole(roleName);
                }
            }
        }*/

        //赋予权限和角色
        /*if(permissionList.size() > 0){
            for (Iterator<Map<String, Object>> iterator = permissionList.iterator(); iterator.hasNext(); ) {
                Map<String, Object> next = iterator.next();
                String rcode = (String) next.get("rcode");
                String pcode = (String) next.get("pcode");
                if(StringUtils.isNoneBlank(rcode)){
                    info.addRole(rcode);
                }
                if(StringUtils.isNoneBlank(pcode)){
                    info.addStringPermission(pcode);
                }
            }
        }*/
       /* for(Permission permission:permissionService.getByUserId(user.getId())){
//            if(StringUtils.isNotBlank(permission.getPermCode()))
                info.addStringPermission(permission.getName());
        }*/

        //设置登录次数、时间
//        userService.updateUserLogin(user);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //logger.info("doGetAuthenticationInfo +"  + authenticationToken.toString());

       // logger.info(userName+token.getPassword());

        if(authenticationToken instanceof ApiToken){
            ApiToken apiToken = (ApiToken)authenticationToken;
            BaseUser baseUser = userService.getUserByUserName(apiToken.getClientKey());
            if(StringUtils.isBlank(baseUser.getUserId())){
                return null;
            }
            // 此处可以添加查询数据库检查账号是否存在、是否被锁定、是否被禁用等等逻辑
            return new SimpleAuthenticationInfo(baseUser,Boolean.TRUE,getName());

        }else{
            UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
            String userName=token.getUsername();
            BaseUser user = userService.getUserByUserName(token.getUsername());
            if (user.getUserId() != null) {
//            byte[] salt = Encodes.decodeHex(user.getSalt());
//            ShiroUser shiroUser=new ShiroUser(user.getId(), user.getLoginName(), user.getName());
                //设置用户session
                Session session = SecurityUtils.getSubject().getSession();
                session.setAttribute("user", user);
                return new SimpleAuthenticationInfo(user,user.getPassWord(),getName());
            } else {
                return null;
            }


        }


//        return null;
    }



}
