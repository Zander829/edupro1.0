package pers.zander.edu.interceptor;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import pers.zander.edu.user.domain.SysUser;
import pers.zander.edu.user.service.SysUserService;
import pers.zander.edu.user.service.SysUserServiceImpl;
import pers.zander.edu.user.web.model.SysUserModel;

/**
 * 自定义拦截器1
 * @author zhaozhao
 * @time 2016-3-23 下午5:16:14
 */
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
////        System.out.println(">>>MyInterceptor1>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");
//        String username = (String) request.getSession().getAttribute("name");
//        String password = (String) request.getSession().getAttribute("tel");
////      System.out.println("interceptor>>>"+username+"----"+password);
////      HandlerMethod handler2=(HandlerMethod) handler; 
//        String type = request.getHeader("X-Requested-With");//请求类型
////      System.out.println("type>>>"+type);
//        //查询是否为合法用户
//        if(username!=null&&password!=null){
//        	return true ;
//        }else{
//        	System.out.println("登陆验证失败...");
//        	if ("XMLHttpRequest".equalsIgnoreCase(type)) {//ajax请求
//        		PrintWriter out = response.getWriter();  
//        		out.print("900");  
//        	}else{
//	        	response.sendRedirect("/edupro/system/login.html");
//        	}
//        }
//        return false;// 只有返回true才会继续向下执行，返回false取消当前请求
    	return true ;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
//        System.out.println(">>>MyInterceptor1>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
//        System.out.println(">>>MyInterceptor1>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }

}
