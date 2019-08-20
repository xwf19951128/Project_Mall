package com.cskaoyan.config;

import com.cskaoyan.annotation.SystemLog;
import com.cskaoyan.bean.admin.system.Log;
import com.cskaoyan.service.admin.system.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Author: XiaoLei
 * @Date Created in 16:55 2019/8/18
 */
@Aspect
@Component
public class LogAspect {

    @Autowired
    LogService logService;

    //自定义注解的全类名，用来匹配与指定注解所标注的方法进行切入，所以我们需要
    //在自己要操作的方法上加个自定义注解以及相关的描述。
    @Pointcut("@annotation(com.cskaoyan.annotation.SystemLog)")
    public void pointcut(){}

    @AfterReturning("pointcut()")
    public void afterReturn(JoinPoint joinPoint){
        Log log = new Log();
        //获取连接点的签名对象
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //方法如果存在这样的注解，则返回指定类型元素的注释，否则，则返回null
        SystemLog annotation = method.getAnnotation(SystemLog.class);

        //判断登录状态
        if(annotation!=null){
            String desc = annotation.desc();
            log.setAction(desc);
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        log.setIp(request.getRemoteAddr());
        log.setStatus(true);

        log.setAddTime(new Date());
        log.setUpdateTime(new Date());
        log.setType(0);
        int i = logService.insertLog(log);
    }
}
