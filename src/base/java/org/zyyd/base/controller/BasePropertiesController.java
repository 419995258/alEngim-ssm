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
import org.zyyd.base.service.BasePropertiesService;
import org.zyyd.base.util.BasicController;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

//@ApiIgnore  //  忽略扫描该类，api
@Controller  //RestController代表controller,但是是和ResponseBody结合
@RequestMapping("base/basePropertiesC")
@Api(value = "base/basePropertiesC", tags = "属性", description = "属性")
public class BasePropertiesController extends BasicController {



    @Autowired
    private BasePropertiesService basePropertiesService;


    /**
     * 查询项目属性组
     * @Description: 
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/6 14:48
     */
    @RequestMapping(value="/listPropertyGroup",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询项目属性组")
    @RequiresPermissions("properties:list")
    public Message listPropertyGroup(){
        Message message = new Message();
        List<BasePropertiesGroup> propertyGroupList = basePropertiesService.listBasePropertiesGroup();

        String jsonStr = JSONObject.toJSONString(propertyGroupList);
        JSONArray jsonArray = JSON.parseArray(jsonStr);
        for (Iterator<Object> iterator = jsonArray.iterator(); iterator.hasNext(); ) {
            JSONObject json = (JSONObject) iterator.next();
            String code = json.getString("groupCode");
            if("-1".equals(code)){
                // 数根节点
                json.put("isParent","true");
            }
            // 设置数都默认展开
            json.put("open","true");
        }
        message.setObj(jsonArray);
        message.setSuccess(true);

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
    @RequestMapping(value="/listProperty",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询属性")
    @ApiImplicitParam(paramType = "query",name= "groupKey" ,value = "属性组groupKey",dataType = "string")
    @RequiresPermissions("properties:list")
    public Message listProperty(@RequestParam(value = "groupKey" , required = true) String groupKey){
        Message message = new Message();
        if(StringUtils.isNoneBlank(groupKey)){

            List<BaseProperties> propertyList = basePropertiesService.listBasePropertiesByPropertyGroupId(groupKey);
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
    @RequestMapping(value="/deleteProperty",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "删除属性")
    @ApiImplicitParam(paramType = "query",name= "gid" ,value = "属性id",dataType = "string")
    @RequiresPermissions("properties:update")
    public Message deleteProperty(@RequestParam(value = "gid" , required = true) String gid){
        Message message = new Message();
        if(StringUtils.isNoneBlank(gid)){

            message = basePropertiesService.removeBasePropertiesById(gid);


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
    @RequestMapping(value="/updateProperty",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加或修改属性")
    @RequiresPermissions("properties:update")
    public Message updateProperty(@RequestBody BaseProperties property){
        Message message = new Message();
        message = basePropertiesService.updateBaseProperties(property);
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
    @RequestMapping(value="/deletePropertyGroup",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "删除属性组")
    @RequiresPermissions("properties:update")
    public Message deletePropertyGroup(@RequestBody BasePropertiesGroup PropertyGroup){
        Message message = new Message();
        message = basePropertiesService.removeBasePropertiesGroup(PropertyGroup);
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
    @RequestMapping(value="/updatePropertyGroup",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加或修改属性组")
    @RequiresPermissions("properties:update")
    public Message updatePropertyGroup(@RequestBody BasePropertiesGroup propertyGroup){
        Message message = new Message();
        message = basePropertiesService.updateBasePropertiesGroup(propertyGroup);
        return message;

    }









}
