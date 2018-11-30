package org.zyyd.base.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zyyd.base.entity.BaseUser;
import org.zyyd.base.entity.vo.Message;
import org.zyyd.base.entity.vo.ResultVo;
import org.zyyd.base.service.TestService;
import org.zyyd.base.util.BasicController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.ApiOperation;
import redis.RedisUtil;

@Controller  //代表controller
@RequestMapping("testC")
public class testController extends BasicController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private TestService testService;



    @RequestMapping("/index")
    @ResponseBody
    public Message index(){
        Message message = new Message();
        redisUtil.set("aaa","test");
        System.out.println(redisUtil.get("aaa"));

       /* Student student = new Student();
        student.setName("a");
        student.setAge(11);
        studentMapper.insertSelective(student);
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andAgeIsNull();
        List<Student> list = studentMapper.selectByExample(studentExample);
        message.setSuccess(true);
        message.setMessage(String.valueOf(list.size()));*/
        return message;
    }



    /*@RequestMapping("/get")
    public Student getStudent(Integer id){
        Student student = new Student();
        student = studentMapper.selectByPrimaryKey(id);

        return  student;
    }*/
    @RequestMapping("/testShiroSession")
    @ResponseBody
    @RequiresPermissions("asdasd")
    public Message testShiroSession(@RequestBody JSONObject json){
        Message message = new Message();
        return message;
    }
    @RequestMapping("/testShiroSession2")
    @ResponseBody
    @RequiresPermissions("properties:list")
    public Message testShiroSession2(@RequestBody JSONObject json){
        Message message = new Message();
        return message;
    }



    @RequestMapping(value="/login",method = RequestMethod.POST)
    @ResponseBody
    public  void login(@RequestParam(value = "name" , required = false) String name, String pwd){
        System.out.println(name);
        System.out.println(pwd);
        System.out.println("ok");
    }

    @RequestMapping(value="/login2",method = RequestMethod.POST)
    @ResponseBody
    public  void login2(@RequestBody JSONObject jsonObject){
        System.out.println("ok");
    }


    /*@RequestMapping(value="/testRedis",method = RequestMethod.POST)
    @ResponseBody
    public  void testRedis(){
        String testRedis = "testRedis";
        Integer testRedis2 = 2;
        redisService.setStr("testRedis",testRedis);
        System.out.println(redisService.getStr(testRedis));
        redisService.setObj(testRedis2,testRedis2);
        System.out.println(redisService.getObj(testRedis2));
    }*/


    @RequestMapping(value="/testShiroP3",method = RequestMethod.POST)
    @RequiresPermissions("p3")
    @ResponseBody
    @ApiOperation(value = "测试shiro")
    public void testShiroP3(){
        System.out.println("p3");
    }

    @RequestMapping(value="/testShiroP1",method = RequestMethod.POST)
    @RequiresPermissions("p1")
    @ResponseBody
    @ApiOperation(value = "测试shirotestShiroP1")
    public void testShiroP1(){
        System.out.println("p1");
    }



    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {
        return "base/admin/index";
    }

    @RequestMapping(value = "/index2", method = RequestMethod.GET)
    public String index2(HttpServletRequest request, Model model) {
        return "base/admin/index2";
    }



    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request, Model model) {
        return "base/admin/login";
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(HttpServletRequest request, Model model) {
        return "base/BaseModule/list";
    }



    @RequestMapping(value="/testPage",method = RequestMethod.POST)
    @ResponseBody
    public  ResultVo testPage(@RequestBody ResultVo resultVo){
        System.out.println("ok");
        resultVo.setCurrentPage(resultVo.getPageNo());
        resultVo.setTotal(300L);
        resultVo.setPages(60);
        return resultVo;
    }
}
