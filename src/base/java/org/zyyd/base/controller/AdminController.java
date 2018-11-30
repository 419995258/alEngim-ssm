package org.zyyd.base.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zyyd.base.entity.BasePermission;
import org.zyyd.base.entity.BaseProperties;
import org.zyyd.base.entity.BasePropertiesGroup;
import org.zyyd.base.entity.BaseRole;
import org.zyyd.base.entity.BaseRolePermissionMap;
import org.zyyd.base.entity.BaseUser;
import org.zyyd.base.entity.vo.BaseUserVO;
import org.zyyd.base.entity.vo.Message;
import org.zyyd.base.entity.vo.ResultVo;
import org.zyyd.base.service.AdminService;
import org.zyyd.base.util.BasicController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import redis.RedisUtil;
import springfox.documentation.annotations.ApiIgnore;

//@ApiIgnore  //  忽略扫描该类，api
@Controller  //RestController代表controller,但是是和ResponseBody结合
@RequestMapping("adminC")
@Api(value = "adminC", tags = "admin后台的管理接口", description = "后台的管理接口相关")
public class AdminController extends BasicController {



    @Autowired
    private AdminService adminService;


    /**
     * 查询项目属性组
     * @Description: 
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/6 14:48
     */
    @RequestMapping(value="/queryPropertyGroup",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询项目属性组")
    @RequiresPermissions("properties:list")
    //@ApiImplicitParam(paramType = "query",name= "username" ,value = "用户名",dataType = "string")
    /*@ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name= "username" ,value = "用户名",dataType = "string"),
            @ApiImplicitParam(paramType = "query",name= "password" ,value = "密码",dataType = "string")
    })*/
    /*public  void userLogin(@RequestParam(value = "username" , required = false) String username,
                           @RequestParam(value = "password" , required = false) String password)*/
    public Message queryPropertyGroup(){
        Message message = new Message();
        List<BasePropertiesGroup> propertyGroupList = adminService.listBasePropertiesGroup();
        message.setSuccess(true);
        message.setObj(propertyGroupList);
        return message;

    }


    /**
     *  通过属性组id查询属性
     * @Description: 
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/6 14:51
     */
    @RequestMapping(value="/queryProperty",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询属性")
    @ApiImplicitParam(paramType = "query",name= "groupKey" ,value = "属性组groupKey",dataType = "string")
    @RequiresPermissions("properties:list")
    public Message queryProperty(@RequestParam(value = "groupKey" , required = true) String groupKey){
        Message message = new Message();
        if(StringUtils.isNoneBlank(groupKey)){

            List<BaseProperties> propertyList = adminService.listBasePropertiesByPropertyGroupId(groupKey);
            message.setSuccess(true);
            message.setObj(propertyList);

        }else{
            message.setMessage("groupKey不能为空");
        }
        return message;

    }

    /**
     * 通过属性id删除某个属性
     * @Description: 
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/7 10:50
     */
    @RequestMapping(value="/delProperty",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "删除属性")
    @ApiImplicitParam(paramType = "query",name= "gid" ,value = "属性id",dataType = "string")
    @RequiresPermissions("properties:update")
    public Message delProperty(@RequestParam(value = "gid" , required = true) String gid){
        Message message = new Message();
        if(StringUtils.isNoneBlank(gid)){

            message = adminService.removeBasePropertiesById(gid);


        }else{
            message.setMessage("id不能为空");
        }
        return message;

    }


    /**
     * 添加或修改属性
     * @Description: 
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/7 17:39
     */
    @RequestMapping(value="/addorUpdateProperty",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加或修改属性")
    @RequiresPermissions("properties:update")
    public Message addorUpdateProperty(@RequestBody BaseProperties property){
        Message message = new Message();
        message = adminService.updateBaseProperties(property);
        return message;

    }



    /**
     * 删除属性组
     * @Description: 
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/7 17:40
     */
    @RequestMapping(value="/delPropertyGroup",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "删除属性组")
    @RequiresPermissions("properties:update")
    public Message delPropertyGroup(@RequestBody BasePropertiesGroup PropertyGroup){
        Message message = new Message();
        message = adminService.removeBasePropertiesGroup(PropertyGroup);
        return message;

    }


    /**
     * 添加或修改属性组
     * @Description: 
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/7 17:39
     */
    @RequestMapping(value="/addorUpdatePropertyGroup",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加或修改属性组")
    @RequiresPermissions("properties:update")
    public Message addorUpdatePropertyGroup(@RequestBody BasePropertiesGroup propertyGroup){
        Message message = new Message();
        message = adminService.updateBasePropertiesGroup(propertyGroup);
        return message;

    }





    /**
     *   查询所有的角色
     * @Description:   
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/20 11:43
     */
    @RequestMapping(value="/queryBaseRole",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询角色")
    @RequiresPermissions("role:list")
    public Message queryBaseRole(){
        Message message = new Message();
        List<BaseRole> baseRoleList = adminService.listBaseRole();
        message.setSuccess(true);
        message.setObj(baseRoleList);
        return message;

    }

    /**
     *   添加或者修改角色
     * @Description:   
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/20 11:44
     */
    @RequestMapping(value="/addorUpdateBaseRole",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加或修改角色")
    @RequiresPermissions("role:update")
    public Message addorUpdateBaseRole(@RequestBody BaseRole baseRole){
        Message message = new Message();
        message = adminService.updateBaseRole(baseRole);
        return message;
    }

    @RequestMapping(value="/updateBasePermissionChecked",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改角色拥有的权限")
    @RequiresPermissions("role:update")
    public Message updateBasePermissionChecked(@RequestBody BaseRolePermissionMap baseRolePermissionMap){
        Message message = new Message();

        if(StringUtils.isBlank(baseRolePermissionMap.getRoleCode()) ){
            message.setMessage("发生错误！");
        }else{
            message = adminService.updateBasePermissionChecked(baseRolePermissionMap);

        }

        return message;
    }


    /**
     *   删除角色，真删除
     * @Description:   
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/20 11:44
     */
    @RequestMapping(value="/delBaseRole",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "删除角色")
    @RequiresPermissions("role:update")
    @ApiImplicitParam(paramType = "query",name= "roleId" ,value = "角色id",dataType = "string")
    public Message delBaseRole(@RequestParam(value = "roleId" , required = true) String roleId){
        Message message = new Message();
        if(StringUtils.isNoneBlank(roleId)){

            message = adminService.removeBaseRoleById(roleId);


        }else{
            message.setMessage("id不能为空");
        }
        return message;

    }

    /**
     *   查询权限，如果id为空，则全查，否则为查询单个
     * @Description:   
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/21 11:18
     */
    @RequestMapping(value="/queryBasePermission",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询权限")
    @RequiresPermissions("permission:list")
    public Message queryBasePermission(@RequestBody BasePermission basePermission){
        Message message = new Message();

        List<BasePermission> basePermissionList = adminService.listBasePermission(basePermission);

        if(StringUtils.isBlank(basePermission.getPermissionId())){
            String jsonStr = JSONObject.toJSONString(basePermissionList);
            JSONArray jsonArray = JSON.parseArray(jsonStr);
            for (Iterator<Object> iterator = jsonArray.iterator(); iterator.hasNext(); ) {
                JSONObject json = (JSONObject) iterator.next();
                String code = json.getString("code");
                if("-1".equals(code)){
                    // 数根节点
                    json.put("isParent","true");
                }
                // 设置数都默认展开
                json.put("open","true");
            }
            message.setObj(jsonArray);
            message.setSuccess(true);
        }else{
            if(basePermissionList.size() > 0){
                message.setObj(basePermissionList.get(0));
            }
            message.setSuccess(true);
        }

        return message;
    }


    /**
     *   查询选中的权限
     * @Description:   
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/21 16:08
     */
    @RequestMapping(value="/queryBasePermissionChecked",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询选中的权限")
    @RequiresPermissions("permission:list")
    public Message queryBasePermissionChecked(@RequestBody BaseRole baseRole){
        Message message = new Message();
        BasePermission basePermission = new BasePermission();

        List<BasePermission> basePermissionList = adminService.listBasePermission(basePermission);
        Set<String> pCodeSet = adminService.listBasePermissionChecked(baseRole);

        if(StringUtils.isBlank(basePermission.getPermissionId())){
            String jsonStr = JSONObject.toJSONString(basePermissionList);
            JSONArray jsonArray = JSON.parseArray(jsonStr);
            for (Iterator<Object> iterator = jsonArray.iterator(); iterator.hasNext(); ) {
                JSONObject json = (JSONObject) iterator.next();
                String code = json.getString("code");
                //String permissionCode = json.getString("permissionCode");
                // 数根节点
                if("-1".equals(code)){
                    json.put("isParent","true");
                }
                // 设置树都默认展开
                json.put("open","true");

                // 判断是否被选中了
                if(pCodeSet.contains(code)){
                    json.put("checked","true");
                }
            }
            message.setObj(jsonArray);
            message.setSuccess(true);
        }else{
            if(basePermissionList.size() > 0){
                message.setObj(basePermissionList.get(0));
            }
            message.setSuccess(true);
        }

        return message;
    }

    /**
     *   删除权限，通过ID删除，并且删除该权限的所有子权限
     * @Description:   
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/21 11:18
     */
    @RequestMapping(value="/delPermission",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "删除权限")
    @RequiresPermissions("permission:update")
    @ApiImplicitParam(paramType = "query",name= "ID" ,value = "权限ID",dataType = "string")
    public Message delPermission(@RequestBody BasePermission basePermission){
        Message message = new Message();
        if(StringUtils.isNoneBlank(basePermission.getPermissionId()) || StringUtils.isNoneBlank(basePermission.getCode())){

            message = adminService.removeBasePermissionById(basePermission);

        }else{
            message.setMessage("id不能为空");
        }
        return message;

    }


    /**
     *   添加或修改权限，通过有没有basePermission的id来判断添加或者修改
     * @Description:   
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/22 16:09
     */
    @RequestMapping(value="/addorUpdateBasePermission",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加或修改权限")
    @RequiresPermissions("permission:update")
    public Message addorUpdateBasePermission(@RequestBody BasePermission basePermission){
        Message message = new Message();
        message = adminService.updateBasePermission(basePermission);
        return message;
    }

    /**
     *   查询用户list-分页
     * @Description:   
     * @param    baseUserVO
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/23 14:23
     */
    @RequestMapping(value="/queryUserList",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询用户list")
    @RequiresPermissions("user:list")
    public ResultVo queryUserList(@RequestBody BaseUserVO baseUserVO){
        ResultVo resultVo = new ResultVo();
        resultVo = adminService.listBaseUserList(baseUserVO);
        return resultVo;
    }



    /**
     *   改变用户状态
     * @Description:   
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/23 14:23
     */
    @RequestMapping(value="/changeUserStatus",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "改变用户状态")
    @RequiresPermissions("user:update")
    public Message changeUserStatus(@RequestBody BaseUserVO baseUserVO){
        Message message = new Message();
        if(StringUtils.isNoneBlank(baseUserVO.getUserId()) ){

            message = adminService.updateUserStatus(baseUserVO);

        }else{
            message.setMessage("id不能为空");
        }
        return message;

    }

    /**
     *   删除用户-物理删除
     * @Description:   
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/23 14:23
     */
    @RequestMapping(value="/delUser",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "删除用户")
    @RequiresPermissions("user:update")
    public Message delUser(@RequestBody BaseUser baseUser){
        Message message = new Message();
        if(StringUtils.isNoneBlank(baseUser.getUserId()) ){
            message = adminService.removeUser(baseUser);

        }else{
            message.setMessage("id不能为空");
        }
        return message;

    }

    @RequestMapping(value="/updateUser",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "更新用户")
    @RequiresPermissions("user:update")
    public Message updateUser(@RequestBody BaseUserVO baseUserVO){
        Message message = new Message();

        message = adminService.updateBaseUser(baseUserVO);

        return message;

    }



    /*@RequestMapping(value="/clearShiro",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "测试clearShiro")
    public void clearShiro(){
        Subject subject = SecurityUtils.getSubject();
        RealmSecurityManager securityManager =
                (RealmSecurityManager) SecurityUtils.getSecurityManager();
        ShiroRealm shiroRealm = (ShiroRealm)securityManager.getRealms().iterator().next();
        //删除登陆人
        shiroRealm.getAuthorizationCache().remove(subject.getPrincipal());
        //删除登陆人对应的缓存
        shiroRealm.getAuthorizationCache().remove(subject.getPrincipals());
        //重新加载subject
        subject.releaseRunAs();
    }*/






}
