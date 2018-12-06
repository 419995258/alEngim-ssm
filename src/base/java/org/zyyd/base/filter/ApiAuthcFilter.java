package org.zyyd.base.filter;

import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;
import org.zyyd.base.entity.BaseUser;
import org.zyyd.base.util.ObjectUtil;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import shiro.ApiToken;

import static org.zyyd.base.util.ObjectUtil.writeJson;

@Component("ApiAuthc")
public class ApiAuthcFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
       /* boolean accessAllowed = false;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String jwt = httpRequest.getHeader("Authorization");
        if (jwt == null || !jwt.startsWith("Bearer ")) {
            return accessAllowed;
        }
        jwt = jwt.substring(jwt.indexOf(" "));
        String username = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("love431"))
                .parseClaimsJws(jwt).getBody().getSubject();
        BaseUser user = (BaseUser) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        if (username.equals(user.getLoginName())) {
            accessAllowed = true;
        }
        return accessAllowed;*/
        if (null != getSubject(request, response)
                && getSubject(request, response).isAuthenticated()) {
            return true;//已经认证过直接放行
        }
        return false;//转到拒绝访问处理逻辑


    }

    /**
     * 拦截时返回  JSON，而不是跳转到一个loginUrl
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request,
                                     ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;


        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String jwt = httpRequest.getHeader("Authorization");
        if (StringUtils.isBlank(jwt)) {
            return false;
        }
        try {
            String tokenStr = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("alEngin"))
                    .parseClaimsJws(jwt).getBody().getSubject();


            JSONObject json = JSONObject.parseObject(tokenStr);

            //创建令牌
    //        AuthenticationToken token = new ApiToken(username, "", request.getRemoteHost(), "");
            UsernamePasswordToken token = new UsernamePasswordToken(json.getString("loginName"), json.getString("passWord"));

            Subject subject = getSubject(request, response);
            subject.login(token);//认证
            SecurityUtils.getSubject().getSession().stop();
            return true;//认证成功，过滤器链继续
        } catch (AuthenticationException e) {//认证失败，发送401状态并附带异常信息
            e.printStackTrace();
            JSONObject errorJson = new JSONObject();
            errorJson.put("code","-1");
            errorJson.put("message","认证错误！");
            writeJson(errorJson,res);
        } catch (SignatureException e){
            JSONObject errorJson = new JSONObject();
            errorJson.put("code","-1");
            errorJson.put("message","令牌错误！");
            writeJson(errorJson,res);
        }


        return false;
    }






}
