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
import org.zyyd.base.service.BaseUserService;
import org.zyyd.base.util.BasicController;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

//@ApiIgnore  //  忽略扫描该类，api
@Controller  //RestController代表controller,但是是和ResponseBody结合
@RequestMapping("base/baseUserC")
@Api(value = "base/baseUserC", tags = "用户", description = "用户")
public class BaseUserController extends BasicController {


    @Autowired
    private BaseUserService baseUserService;


    /**
     *   查询用户list-分页
     * @Description:   
     * @param    baseUserVO
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/23 14:23
     */
    @RequestMapping(value="/listUserList",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询用户list")
    @RequiresPermissions("user:list")
    public ResultVo listUserList(@RequestBody BaseUserVO baseUserVO){
        ResultVo resultVo = new ResultVo();
        resultVo = baseUserService.listBaseUserList(baseUserVO);
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
    @RequestMapping(value="/updateUserStatus",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "改变用户状态")
    @RequiresPermissions("user:update")
    public Message updateUserStatus(@RequestBody BaseUserVO baseUserVO){
        Message message = new Message();
        if(StringUtils.isNoneBlank(baseUserVO.getUserId()) ){

            message = baseUserService.updateUserStatus(baseUserVO);

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
    @RequestMapping(value="/deleteUser",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "删除用户")
    @RequiresPermissions("user:update")
    public Message deleteUser(@RequestBody BaseUser baseUser){
        Message message = new Message();
        if(StringUtils.isNoneBlank(baseUser.getUserId()) ){
            message = baseUserService.removeUser(baseUser);

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

        message = baseUserService.updateBaseUser(baseUserVO);

        return message;

    }




}
