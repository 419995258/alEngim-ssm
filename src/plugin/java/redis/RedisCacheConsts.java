package redis;

/**
 * Redis缓存常量<br/>
 * 命名规则：类名_业务名
 * @author wangzhen
 *
 */
public class RedisCacheConsts {
	/**
	 * SQL缓存名称
	 */
	public final static String BASE_QUERY_CACHE_NAME="base_BaseQuery_queryName_";
	


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

}
