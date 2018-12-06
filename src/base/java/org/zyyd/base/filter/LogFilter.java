package org.zyyd.base.filter;

import com.alibaba.fastjson.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zyyd.base.entity.BaseUser;
import org.zyyd.base.util.LogUtil;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: LogFilter
 * @Description:
 * @author pengbin <pengbin>
 * @date  2018/12/3 18:13
 */
public class LogFilter implements Filter{


    private static Logger logger = LoggerFactory.getLogger(LogFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse theResponse=((HttpServletResponse) response);
        HttpServletRequest theRequest=((HttpServletRequest) request);





        Map<String,Object> logMessage = new HashMap<String,Object>();

        String path=theRequest.getServletPath();

        String ext="";
        if(path.lastIndexOf(".")==-1){

        }else{
            ext=path.substring(path.lastIndexOf("."), path.length());
        }

        if(".js".equals(ext) || ".css".equals(ext)){
            chain.doFilter(request, response);
            return;
        }

        logMessage.put("path",path);


        try {
            BaseUser user = (BaseUser) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
            if(user.getUserId() != null){
                logMessage.put("hasLogin",true);
                logMessage.put("userId",user.getUserId());
                logMessage.put("loginName",user.getLoginName());
                logMessage.put("roleCodes",user.getRoleCodes());
            }else{
                logMessage.put("hasLogin",false);
            }
        } catch (Exception e) {
//            e.printStackTrace();
            logMessage.put("hasLogin",false);
        }

        LogUtil.info(logMessage);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
