package com.sobercoding.loopauth.interceptor;

import com.sobercoding.loopauth.util.PermissionUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 注解式鉴权 - 拦截器
 * 
 * @author Yun
 */
public class LoopAuthAnnotationInterceptor implements HandlerInterceptor {

	/**
	 * 构建： 注解式鉴权 - 拦截器
	 */
	public LoopAuthAnnotationInterceptor() {
	}

	/**
	 * 每次请求之前触发的方法
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 获取处理method
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		Method method = ((HandlerMethod) handler).getMethod();
		// 进行验证
		PermissionUtil.checkMethodAnnotation.accept(method);
		// 通过验证
		return true;
	}

}
