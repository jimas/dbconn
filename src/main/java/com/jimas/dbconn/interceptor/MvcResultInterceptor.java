package com.jimas.dbconn.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jimas.dbconn.api.MenuApi;
import com.jimas.dbconn.cache.redis.api.RedisApi;
import com.jimas.dbconn.constant.Constant;
import com.jimas.dbconn.pojo.rest.Menu;
import com.jimas.dbconn.pojo.rest.MenuPojo;
import com.jimas.dbconn.sourceconfig.ParamsConfig;


/**
 * @Description mvc 拦截器
 * @author weqinjia.liu
 * @Date 2017年3月13日
 */
@Component
public class MvcResultInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger=Logger.getLogger(MvcResultInterceptor.class);
    @Autowired
    private MenuApi menuApi;
    @Autowired
    private RedisApi redisApi;
    @Autowired
    private ParamsConfig paramsConfig;
    /**
     * 预处理回调方法，实现处理器的预处理（如登录检查）。 第三个参数为响应的处理器，即controller。 返回true，表示继续流程，调用下一个拦截器或者处理器。 返回false，表示流程中断，通过response产生响应。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 验证用户是否登陆 TODO
        return true;
    }

    /**
     * 当前请求进行处理之后，也就是Controller 方法调用之后执行， 但是它会在DispatcherServlet 进行视图返回渲染之前被调用。 此时我们可以通过modelAndView对模型数据进行处理或对视图进行处理。
     */
    @SuppressWarnings("unchecked")
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("-------------------postHandle");
        if(handler instanceof HandlerMethod){
            HandlerMethod method =(HandlerMethod) handler;
            MenuModel menuModel = method.getMethodAnnotation(MenuModel.class);
            if(StringUtils.isEmpty(modelAndView)){
                modelAndView=new ModelAndView();
            }
            if(menuModel!=null&&menuModel.value()){
                
                MenuPojo<Menu> redisMenuPojo = (MenuPojo<Menu>) redisApi.get(Constant.MENU_KEY);
                if(StringUtils.isEmpty(redisMenuPojo)){
                    redisMenuPojo= menuApi.queryMenuPojoBySiteSource(paramsConfig.getSiteSource());
                    redisApi.set(Constant.MENU_KEY, redisMenuPojo, Constant.DEFAULTTIMEOUT);
                }
                
                modelAndView.addObject("menuList", redisMenuPojo.getMenuList());
            }
        }
    }

    /**
     * 方法将在整个请求结束之后，也就是在DispatcherServlet 渲染了对应的视图之后执行。 这个方法的主要作用是用于进行资源清理工作的。
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
