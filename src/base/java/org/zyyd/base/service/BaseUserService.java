package org.zyyd.base.service;


import com.alibaba.fastjson.JSONArray;

import org.zyyd.base.entity.BasePermission;
import org.zyyd.base.entity.BaseProperties;
import org.zyyd.base.entity.BasePropertiesGroup;
import org.zyyd.base.entity.BaseRole;
import org.zyyd.base.entity.BaseRolePermissionMap;
import org.zyyd.base.entity.BaseUser;
import org.zyyd.base.entity.vo.BaseUserVO;
import org.zyyd.base.entity.vo.Message;
import org.zyyd.base.entity.vo.ResultVo;

import java.util.List;
import java.util.Set;

/**  用户基本的一些方法
 * ClassName: BaseUserService
 * @Description: 
 * @author pengbin <pengbin>
 * @date  2018/11/16 15:43
 */
public interface BaseUserService {




    /**
     * 通过user条件查询list
     * @Description:
     * @param baseUser
     * @return userList
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/15 16:24
     */
    List<BaseUser> getUserList(BaseUser baseUser);

    /**
     * 查询某个user
     * @Description:
     * @param baseUser
     * @return user
     * @throws
     * @author pengbin <pengbin>
     * 2018/10/31 14:15
     */
    BaseUser getUser(BaseUser baseUser) throws Exception;

    /**
     * 获取user通过username
     * @Description:
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/10/31 14:26
     */
    BaseUser getUserByUserName(String userName);

    JSONArray getActivityUser();





    /**
     *   查询用户list
     * @Description:   
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/22 16:12
     */
    ResultVo listBaseUserList(BaseUserVO baseUserVO);



    /**
     *   删除用户
     * @Description:   
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/23 14:15
     */
    Message removeUser(BaseUser baseUser);

    /**
     *   修改用户状态
     * @Description:   
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/23 14:15
     */
    Message updateUserStatus(BaseUserVO baseUserVO);

    /**
     *   添加或更新用户
     * @Description:
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/25 9:41
     */
    Message updateBaseUser(BaseUserVO baseUserVO);


    /**
     *   清楚权限shiro缓存信息
     * @Description:
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/28 11:03
     */
    void clearShiro();
}
