package redis;

/**
 * Redis缓存常量<br/>
 * 命名规则：类名_业务名
 * @author wangzhen
 *
 */
public class RedisCacheConsts {
	/**
	 * api用的shiro账户
	 */
	public final static String BASE_USER_SHIRO_CACHE_LOGIN_NAME="base_BaseUser_shiro_loginName_";
	


	/**
	 * 属性组缓存CODE
	 */
    public final static String BASE_PROPERTIES_GROUP_CACHE_CODE="base_BasePropertiesGroup_propertiesGroupCode_";

	/**
	 * 属性缓存CODE
	 */
	public final static String BASE_PROPERTIES_CACHE_CODE="base_BaseProperties_propertiesCode_";

	/**
	 * 权限缓存CODE
	 */
	public final static String BASE_PERMISSION_CACHE_CODE="base_BasePermission_permissionCode_";

	/**
	 * 角色缓存CODE
	 */
	public final static String BASE_ROLE_CACHE_CODE="base_BaseRole_roleCode_";

	/**
	 * 角色缓存CODE，存储单个baseRole
	 */
	public final static String BASE_ROLE_ONE_CACHE_CODE="base_BaseRole_roleCode_one_";

	/**
	 * areaCode存储baseArea
	 */
	public final static String BASE_AREA_CACHE_CODE="base_BaseArea_areaCode_one_";

	/**
	 * areaId存储baseArea
	 */
	public final static String BASE_AREA_CACHE_ID="base_BaseArea_areaId_one_";

	/**
	 * areaCode存储baseArea的下级地区
	 */
	public final static String BASE_AREA_GROUP_CACHE_CODE="base_BaseArea_areaCode_group_";

	/**
	 * areaId存储baseArea的下级地区
	 */
	public final static String BASE_AREA_GROUP_CACHE_ID="base_BaseArea_areaId_group_";

}
