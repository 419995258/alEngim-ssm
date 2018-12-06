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

/**  属性管理基本的一些方法
 * ClassName: BasePropertiesService
 * @Description: 
 * @author pengbin <pengbin>
 * @date  2018/11/16 15:43
 */
public interface BasePropertiesService {







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







}
