package org.zyyd.base.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zyyd.base.entity.BaseUser;
import org.zyyd.base.entity.vo.Message;
import org.zyyd.base.service.UserService;
import org.zyyd.base.servlet.ShiroRealm;
import org.zyyd.base.util.MD5;

import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.ApiOperation;
import redis.RedisUtil;


@Controller
@RequestMapping("/userC")
public class UserController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private RedisUtil redisUtil;

	@RequestMapping(value="/userLogin",method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "用户进行登录")
	//@ApiImplicitParam(paramType = "query",name= "username" ,value = "用户名",dataType = "string")
    /*public  void userLogin(@RequestParam(value = "username" , required = false) String username,
                           @RequestParam(value = "password" , required = false) String password)*/
	public Message userLogin(@RequestBody JSONObject json, HttpServletRequest request){
		Message message = new Message();
		BaseUser baseUser = new BaseUser();




            try {
                Subject subject = SecurityUtils.getSubject();
                UsernamePasswordToken shiroToken = new UsernamePasswordToken(json.getString("loginName"), MD5.getMd5(json.getString("passWord")));
                // Boolean rememberMe = true;   //是否记住
                shiroToken.setRememberMe(true);
                subject.login(shiroToken);
                System.out.println(subject.getPrincipal().toString());
                baseUser = (BaseUser) subject.getPrincipal();
				// Object name = SecurityUtils.getSubject().getPrincipals();
				// System.out.println(name);
				// 获取role,并且存储session
				//List<Map<String,Object>> roleList = new ArrayList<>();
				//roleList = userService.getRolesByUser(baseUser.getUserId());
				//request.getSession().setAttribute("roleList",roleList);


                message.setSuccess(true);
                message.setMessage("登录成功");

			} catch (AuthenticationException e) {
				e.printStackTrace();
                message.setMessage("账号或密码错误");
//            rediect.addFlashAttribute("errorText", "您的账号或密码输入错误!");
	    	}

		return message;

	}


    @RequestMapping(value="/logout",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "用户进行登出")
    //@ApiImplicitParam(paramType = "query",name= "username" ,value = "用户名",dataType = "string")
    /*public  void userLogin(@RequestParam(value = "username" , required = false) String username,
                           @RequestParam(value = "password" , required = false) String password)*/
    public Message logout(@RequestBody JSONObject json, HttpServletRequest request){
        Message message = new Message();
        try{
            Subject subject = SecurityUtils.getSubject();
            //退出
            subject.logout();
            RealmSecurityManager securityManager =
                    (RealmSecurityManager) SecurityUtils.getSecurityManager();
            ShiroRealm shiroRealm = (ShiroRealm)securityManager.getRealms().iterator().next();
            //删除登陆人
            shiroRealm.getAuthorizationCache().remove(subject.getPrincipal());
            //删除登陆人对应的缓存
            shiroRealm.getAuthorizationCache().remove(subject.getPrincipals());
        }catch (Exception e){

        }

        return message;
    }
}
