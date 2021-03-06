package org.zyyd.base.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
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
import org.zyyd.base.entity.BasePropertiesGroup;
import org.zyyd.base.entity.BaseRole;
import org.zyyd.base.entity.BaseRolePermissionMap;
import org.zyyd.base.entity.BaseUser;
import org.zyyd.base.entity.vo.BaseUserVO;
import org.zyyd.base.entity.vo.Message;
import org.zyyd.base.entity.vo.ResultVo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import static org.zyyd.base.util.ObjectUtil.isAjaxRequest;
import static org.zyyd.base.util.ObjectUtil.writeJson;

public class BasicController {


    /**
     * 登录认证异常
     */
    @ExceptionHandler({ UnauthenticatedException.class, AuthenticationException.class })
    public String authenticationException(HttpServletRequest request, HttpServletResponse response) {
        if (isAjaxRequest(request)) {
            // 输出JSON
            JSONObject json = new JSONObject();
            json.put("code", "-1");
            json.put("message", "请先登录!");
            json.put("success",false);
            writeJson(json, response);
            return null;
        } else {
            return "redirect:/login.html";
        }
    }


    /**
     * 权限异常处理
     */
//    配置权限异常返回json，否则跳转至在shiro.xml配置的路径
    @ExceptionHandler({ UnauthorizedException.class, AuthorizationException.class })
    public String authorizationException(HttpServletRequest request, HttpServletResponse response) {
        if(isAjaxRequest(request)){
            JSONObject json = new JSONObject();
            json.put("success",false);
            json.put("message","403,权限异常！");
            writeJson(json, response);
            return null;
        }else{
            return "redirect:/403.html";
        }

    }










}
