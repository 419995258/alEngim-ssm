package org.zyyd.base.service;


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

/**  角色以及权限基本的一些方法
 * ClassName: BaseRoleService
 * @Description: 
 * @author pengbin <pengbin>
 * @date  2018/11/16 15:43
 */
public interface BaseRoleService {












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



}
