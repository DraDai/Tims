package com.tims.zc.filter;

import com.alibaba.fastjson2.JSONObject;
import com.teacher.pojo.Result ;

import com.teacher.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/hello")
//@WebFilter("/hello")
public class A_LoginCheckFilter implements Filter {
    //@Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req= (HttpServletRequest)servletRequest;
        HttpServletResponse resp= (HttpServletResponse) servletResponse;

        // url获取
        String url = req.getRequestURI();
        log.info("请求url{}",url);

        //是否是login
        if(url.contains("login")){
            log.info("登录操作，放行");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }


        //获取令牌
        String jwt=req.getHeader("token");

        log.info("token{}",jwt);
        //判断令牌
        if(jwt == null){
            log.info("token为空");
            Result error=Result.error("not login");
            //手动转换json
            String notLogin= JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }

        //
        try{
            JwtUtils.pareseJwt(jwt);
        }catch (Exception e){
            e.printStackTrace();
            log.info("失败");
            Result error=Result.error("not login");
            //手动转换json
            String notLogin= JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }

        log.info("放行");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
