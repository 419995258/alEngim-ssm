package org.zyyd.base.service;


import com.alibaba.fastjson.JSONObject;

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
import java.util.Map;
import java.util.Set;

/**  管理员基本的一些方法
 * ClassName: AdminService
 * @Description: 
 * @author pengbin <pengbin>
 * @date  2018/11/16 15:43
 */
public interface AdminService {



    /**
     *   分页查询basePropertyGroup
     * @Description:   
     * @param    resultVo
     * @return resultVo
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/16 16:24
     */
    ResultVo listBasePropertiesGroup(ResultVo resultVo);


    /**
     *   分页查询baseProperty
     * @Description:   
     * @param    resultVo
     * @return resultVo
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/16 16:24
     */
    ResultVo listBaseProperties(ResultVo resultVo);




    /**
     *  查询所有的属性组
     * @Description: 
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/7 15:01
     */
    List<BasePropertiesGroup> listBasePropertiesGroup();

    /**
     *  通过属性组id查询属性
     * @Description: 
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/7 15:01
     */
    List<BaseProperties> listBasePropertiesByPropertyGroupId(String groupKey);

    /**
     * 删除属性通过id
     * @Description: 
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/7 15:01
     */
    Message removeBasePropertiesById(String id);

    /**
     * 添加或者更新属性
     * @Description: 
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/7 15:02
     */
    Message updateBaseProperties(BaseProperties property);


    /**
     * 通过属性组id删除属性组
     * @Description: 
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/7 17:40
     */
    Message removeBasePropertiesGroup(BasePropertiesGroup propertyGroup);

    /**
     * 添加或者更新属性组
     * @Description: 
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/7 15:02
     */
    Message updateBasePropertiesGroup(BasePropertiesGroup propertyGroup);






    /**
     *   查询角色List
     * @Description:   
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/19 17:15
     */
    List<BaseRole> listBaseRole();

    /**
     * 添加或者更新角色
     * @Description: 
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/7 15:02
     */
    Message updateBaseRole(BaseRole baseRole);


    /**
     *   通过id删除角色
     * @Description:   
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/19 19:44
     */
    Message removeBaseRoleById(String roleId);


    /**
     *   查询权限
     * @Description:   
     * @param basePermission，id为空则全查
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/20 11:48
     */
    List<BasePermission> listBasePermission(BasePermission basePermission);

    /**
     *   通过role_id查询某个角色选中的权限
     * @Description:   
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/21 16:10
     */
    Set<String> listBasePermissionChecked(BaseRole baseRole);


    /**
     *   通过id,code删除某个权限
     * @Description:   
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/21 11:10
     */
    Message removeBasePermissionById(BasePermission basePermission);

    /**
     *   添加或修改权限
     * @Description:   
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/21 11:23
     */
    Message updateBasePermission(BasePermission basePermission);



    /**
     *   更新角色的权限，其中，baseRolePermissionMap的pewrmissionId可以为某个id，也可以为多个id的str，以，
     *   分割
     * @Description:   
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/22 14:23
     */
    Message updateBasePermissionChecked(BaseRolePermissionMap baseRolePermissionMap);


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
