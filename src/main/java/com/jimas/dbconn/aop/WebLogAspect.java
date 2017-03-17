package com.jimas.dbconn.aop;

import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.jimas.common.util.IpAddressUtil;
import com.jimas.dbconn.api.LogApi;
import com.jimas.dbconn.pojo.rest.LogPojo;
import com.jimas.dbconn.sourceconfig.ParamsConfig;

/**
 * 实现Web层的日志切面 在切入点前的操作，按order的值由小到大执行 在切入点后的操作，按order的值由大到小执行 在实际中order值可以设置为负值，确保是第一个进行执行的
 * 
 * @author weqinjia.liu
 * @version v.0.1
 */
@Aspect
@Component
@Order(-5)
public class WebLogAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LogApi webLogApi;
    @Autowired
    private ParamsConfig paramsConfig;

    ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    /**
     * 定义一个切入点. 解释下： ~ 第一个 * 代表任意修饰符及任意返回值. ~ 第二个 * 任意包名 ~ 第三个 * 代表任意方法. ~ 第四个 * 定义在web包或者子包 ~ 第五个 * 任意方法 ~ ..
     * 匹配任意数量的参数.
     */
    @Pointcut("execution(public * com.jimas.dbconn.controller..*.*(..))")
    public void webLog() {

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        logger.info("WebLogAspect.doBefore()");
        insertLog(joinPoint);
    }

    @AfterReturning("webLog()")
    public void doAfterReturning(JoinPoint joinPoint) {
        // 处理完请求，返回内容
        logger.info("WebLogAspect.doAfterReturning()");
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        logger.info(classMethod + " 耗时: " + (System.currentTimeMillis() - startTime.get()) + " 毫秒");
    }

    /* =================================================== private method ============================================== */
    @Async
    private void insertLog(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("request url-->" + request.getRequestURL().toString());
        LogPojo logPojo = new LogPojo();
        logPojo.setArgs(Arrays.toString(joinPoint.getArgs()));
        logPojo.setClassMethod(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logPojo.setHttpMethod(request.getMethod());
        Map<String, String> parameters = new HashMap<String, String>();
        // 获取所有参数方法一：
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();
            parameters.put(paraName, request.getParameter(paraName));
        }
        logPojo.setParameters(parameters);
        logPojo.setRemoteAddr(IpAddressUtil.getIp2(request));
        logPojo.setUrl(request.getRequestURL().toString());
        logPojo.setOperateDate(new Date());
        logPojo.setSiteSource(paramsConfig.getSiteSource());

        webLogApi.insertLog(logPojo);

    }

}