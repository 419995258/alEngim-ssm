package org.zyyd.base.service;



import org.zyyd.base.entity.BasePermission;
import org.zyyd.base.entity.BaseProperties;
import org.zyyd.base.entity.BaseRole;
import org.zyyd.base.entity.BaseUser;

import java.util.List;
import java.util.Map;

/** 缓存service
 * ClassName: RedisService
 * @Description:
 * @author pengbin <pengbin>
 * @date  2018/11/25 16:02
 */
public interface RedisService {


    /**
     *   存放properties的缓存，全部
     * @Description:
     * @param
     * @return List<BaseProperty>
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/25 16:14
     */
    void setPropertiesGroupRedis();

    /**
     *   存放properties的缓存，key是group的key
     * @Description:
     * @param
     * @return List<BaseProperty>
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/25 16:14
     */
    void setPropertiesGroupRedis(String groupCode);

    /**
     *   获取properties的缓存，key是group的key
     * @Description:
     * @param
     * @return List<BaseProperty>
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/25 16:14
     */
    List<BaseProperties> listPropertiesGroupRedis(String groupCode);

    /**
     *   删除properties的缓存，key是group的key
     * @Description:
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/25 16:14
     */
    void removePropertiesGroupRedis(String groupCode);

    /**
     *   存储属性单个
     * @Description:
     * @param
     * @return BaseProperties
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/28 20:28
     */
    void setPropertiesOneRedis();

    /**
     *   存储属性单个
     * @Description:
     * @param key
     * @return BaseProperties
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/28 20:28
     */
    void setPropertiesOneRedis(String key);

    /**
     *   获取某个属性
     * @Description:
     * @param key
     * @return BaseProperties
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/28 20:28
     */
    BaseProperties getPropertiesOneRedis(String key);

    /**
     *   删除某个属性的缓存
     * @Description:
     * @param    key
     * @return baseProteries
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/28 20:29
     */
    void removePropertiesOneRedis(String key);


    /**
     *   存储所有的角色的所有权限
     * @Description:
     * @param
     * @return List<BasePermission>
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/25 17:39
     */
    void setRoleRedis();

    /**
     *   存储某个的角色，roleCode
     * @Description:
     * @param
     * @return List<BasePermission>
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/25 17:39
     */
    void setRoleRedis(String roleCode);

    /**
     *   通过rolecode 获取 权限的集合
     * @Description:
     * @param
     * @return List<BasePermission>
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/25 18:51
     */
    List<BasePermission> listPermissionRedis(String roleCode);


    /**
     *   删除某个角色的缓存
     * @Description:
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/26 9:50
     */
    void removeRoleRedis(String roleCode);

    /**
     *   存储角色,单个
     * @Description:
     * @param
     * @return baseRole
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/27 16:22
     */
    void setRoleOneRedis();

    /**
     *   存储角色,单个
     * @Description:
     * @param
     * @return baseRole
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/27 16:22
     */
    void setRoleOneRedis(String roleCode);

    /**
     *   获取某个角色
     * @Description:
     * @param
     * @return baseRole
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/27 16:24
     */
    BaseRole getRoleOneRedis(String roleCode);

    /**
     *   删除某个角色
     * @Description:
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/27 16:25
     */
    void removeRoleOneRedis(String roleCode);

    /**
     *   将所有的权限存入redis
     * @Description:
     * @param
     * @return  basePermission
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/26 14:58
     */
    void setPermissionOneRedis();

    /**
     *   通过code存储某个权限存入redis
     * @Description:
     * @param
     * @return basePermission
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/26 14:58
     */
    void setPermissionOneRedis(String code);

    /**
     *   通过code获取某个权限
     * @Description:
     * @param
     * @return basePermission
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/26 14:59
     */
    BasePermission getPermissionOneRedis(String code);

    /**
     *   删除某个权限
     * @Description:
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/26 15:00
     */
    void removePermissionOneRedis(String code);

}
