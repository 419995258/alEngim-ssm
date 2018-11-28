package org.zyyd.base.dao.vo;




import org.springframework.stereotype.Component;
import org.zyyd.base.entity.BasePermission;
import org.zyyd.base.entity.BaseUser;

import java.util.List;
import java.util.Map;

@Component("UserMapperExt")
public interface UserMapperExt {




    /**
     *   通过角色id查询权限
     * @Description:
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/19 11:58
     */
    List<BasePermission> selectRolePermissionByRoleCode(String roleCode);


}