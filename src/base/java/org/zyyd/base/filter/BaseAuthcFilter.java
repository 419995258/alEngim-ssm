package org.zyyd.base.filter;

import com.alibaba.fastjson.JSONObject;

import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


    /**
     *   是否是Ajax请求
     * @Description:
     * @param
     * @return
     * @throws
     * @author pengbin <pengbin>
     * 2018/11/26 10:58
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("x-requested-with");
        if (requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest")) {
            return true;
        } else {
            return false;
        }
    }



}
