package org.zyyd.base.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import org.zyyd.base.service.BaseRoleService;
import org.zyyd.base.util.BasicController;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

//@ApiIgnore  //  忽略扫描该类，api
@Controller  //RestController代表controller,但是是和ResponseBody结合
@RequestMapping("base/baseRoleC")
@Api(value = "base/baseRoleC", tags = "角色", description = "角色")
public class BaseRoleController extends BasicController {


    @Autowired
    private BaseRoleService baseRoleService;


    /**
     *   查询所有的角色
     * @Description:   
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/20 11:43
     */
    @RequestMapping(value="/listBaseRole",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询角色")
    @RequiresPermissions("role:list")
    public Message listBaseRole(){
        Message message = new Message();
        List<BaseRole> baseRoleList = baseRoleService.listBaseRole();
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
    @RequestMapping(value="/updateBaseRole",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加或修改角色")
    @RequiresPermissions("role:update")
    public Message updateBaseRole(@RequestBody BaseRole baseRole){
        Message message = new Message();
        message = baseRoleService.updateBaseRole(baseRole);
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
            message = baseRoleService.updateBasePermissionChecked(baseRolePermissionMap);

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
    @RequestMapping(value="/deleteBaseRole",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "删除角色")
    @RequiresPermissions("role:update")
    @ApiImplicitParam(paramType = "query",name= "roleId" ,value = "角色id",dataType = "string")
    public Message deleteBaseRole(@RequestParam(value = "roleId" , required = true) String roleId){
        Message message = new Message();
        if(StringUtils.isNoneBlank(roleId)){

            message = baseRoleService.removeBaseRoleById(roleId);


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
    @RequestMapping(value="/listBasePermission",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询权限")
    @RequiresPermissions("permission:list")
    public Message listBasePermission(@RequestBody BasePermission basePermission){
        Message message = new Message();

        List<BasePermission> basePermissionList = baseRoleService.listBasePermission(basePermission);

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
    @RequestMapping(value="/listBasePermissionChecked",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询选中的权限")
    @RequiresPermissions("permission:list")
    public Message listBasePermissionChecked(@RequestBody BaseRole baseRole){
        Message message = new Message();
        BasePermission basePermission = new BasePermission();

        List<BasePermission> basePermissionList = baseRoleService.listBasePermission(basePermission);
        Set<String> pCodeSet = baseRoleService.listBasePermissionChecked(baseRole);

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
    @RequestMapping(value="/deletePermission",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "删除权限")
    @RequiresPermissions("permission:update")
    @ApiImplicitParam(paramType = "query",name= "ID" ,value = "权限ID",dataType = "string")
    public Message deletePermission(@RequestBody BasePermission basePermission){
        Message message = new Message();
        if(StringUtils.isNoneBlank(basePermission.getPermissionId()) || StringUtils.isNoneBlank(basePermission.getCode())){

            message = baseRoleService.removeBasePermissionById(basePermission);

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
    @RequestMapping(value="/updateBasePermission",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加或修改权限")
    @RequiresPermissions("permission:update")
    public Message updateBasePermission(@RequestBody BasePermission basePermission){
        Message message = new Message();
        message = baseRoleService.updateBasePermission(basePermission);
        return message;
    }








}
