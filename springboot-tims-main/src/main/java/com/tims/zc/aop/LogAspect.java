package com.tims.zc.aop;

import com.alibaba.fastjson2.JSONObject;
import com.tims.zc.mapper.OperateLogMapper;
import com.teacher.pojo.OperateLog ;
import com.teacher.utils.JwtUtils ;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect {
    private final OperateLogMapper operateLogMapper;

    private final HttpServletRequest request;

    @Autowired
    public LogAspect(OperateLogMapper operateLogMapper, HttpServletRequest request) {
        this.operateLogMapper = operateLogMapper;
        this.request = request;
    }



    @Around("@annotation(com.tims.zc.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable{
        //操作人id 当前登录员工id
        //获取请求头中jwt令牌
        String jwt=request.getHeader("token");
        Claims claims = JwtUtils.pareseJwt(jwt);
        //Integer operateUser = (Integer)claims.get("id");
        String tem= (String) claims.get("id");
        Integer operateUser = Integer.valueOf(tem) ;
        //操作时间
        LocalDateTime operateTime = LocalDateTime.now();
        //操作类名
        String className = joinPoint.getTarget().getClass().getName();
        //操作方法名
        String methodName = joinPoint.getSignature().getName();
        //操作方法参数
        Object[] args= joinPoint.getArgs();
        String methodPrams= Arrays.toString(args);

        Long begin=System.currentTimeMillis();
        //调用方法运行
        Object result=joinPoint.proceed();
        Long end=System.currentTimeMillis();

        //方法返回值
        String returnValue = JSONObject.toJSONString(result);

        //操作耗时
        Long costTime=end-begin;
        OperateLog operateLog=new OperateLog(null,operateUser,operateTime,className,methodName,methodPrams,returnValue,costTime);
        operateLogMapper.insert(operateLog);

        log.info("aop操作日志");

        return result;
    }
}
