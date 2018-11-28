package org.zyyd.base.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
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
import org.zyyd.base.entity.BasePropertiesExample;
import org.zyyd.base.entity.BasePropertiesGroup;
import org.zyyd.base.entity.BasePropertiesGroupExample;
import org.zyyd.base.entity.BaseRole;
import org.zyyd.base.entity.BaseRolePermissionMap;
import org.zyyd.base.entity.BaseUser;
import org.zyyd.base.entity.vo.BaseUserVO;
import org.zyyd.base.entity.vo.Message;
import org.zyyd.base.entity.vo.ResultVo;
import org.zyyd.base.service.AdminService;
import org.zyyd.base.service.RedisService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import redis.RedisUtil;

//@ApiIgnore  //  忽略扫描该类，api
@Controller  //RestController代表controller,但是是和ResponseBody结合
@RequestMapping("redisC")
@Api(value = "缓存的处理")
public class RedisController {


    @Autowired
    private RedisService redisService;


    /**
     *   通过key重新加载某个缓存
     * @Description:
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/25 16:04
     */
    @RequestMapping(value="/setRedisByKey",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "重置redis缓存")
    @ApiImplicitParam(paramType = "query",name= "key" ,value = "那个功能模块更新缓存",dataType = "string")
    public Message setRedisByKey(@RequestParam String key){
        Message message = new Message();
        //message = redisService.setRedis(key);
        try {
            switch (key){
                case "property":
                    redisService.setPropertiesGroupRedis();
                    message.setSuccess(true);
                    message.setMessage("正在执行！");
                    break;
                case "role":
                    redisService.setRoleRedis();
                    message.setSuccess(true);
                    message.setMessage("正在执行！");
                    break;
                case "permission":
                    redisService.setPermissionOneRedis();
                    message.setSuccess(true);
                    message.setMessage("正在执行！");
                    break;
                default:
                    message.setMessage("请正确输入你想要重新加载缓存的key");
                    break;
            }
            message.setSuccess(true);
            message.setMessage("正在执行！");
        } catch (Exception e) {
            e.printStackTrace();
            message.setSuccess(false);
            message.setMessage("发现错误！");
        }

        return message;

    }


}
