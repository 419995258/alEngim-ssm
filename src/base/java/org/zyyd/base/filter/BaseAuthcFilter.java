package org.zyyd.base.filter;

import com.alibaba.fastjson.JSONObject;

import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.zyyd.base.util.ObjectUtil.getJson;
import static org.zyyd.base.util.ObjectUtil.isAjaxRequest;
import static org.zyyd.base.util.ObjectUtil.writeJson;

@Component("BaseAuthc")
public class BaseAuthcFilter extends AuthenticationFilter {

    /**
     * 拦截时返回  JSON，而不是跳转到一个loginUrl
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request,
                                     ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        /*String path=req.getServletPath();

        Map<String,Object> logMessage = new HashMap<String,Object>();
        Enumeration<String> ks = ((HttpServletRequest) request).getParameterNames();
        while (ks.hasMoreElements()) {//循环遍历Header中的参数，把遍历出来的参数放入Map中
            String key=ks.nextElement();
            String value=((HttpServletRequest) request).getParameter(key);
            logMessage.put(key, value);
        }*/

        if(isAjaxRequest(req)){
            JSONObject json = new JSONObject();
            json.put("code", "-1");
            json.put("message", "认证错误！");
            writeJson(json,res);
        }else{
            redirectToLogin(request, response);
        }

        return false;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

        return super.isAccessAllowed(request, response, mappedValue);
    }
}
