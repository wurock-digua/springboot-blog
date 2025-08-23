package org.example.springbootblog.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.springbootblog.utils.JwtUtil;
import org.example.springbootblog.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 令牌验证
		String token = request.getHeader("Authorization");
		// 验证token
		try {
			// 从redis中获取token
			String redisToken = redisTemplate.opsForValue().get(token);
			if (redisToken == null) {
				// token失效
				throw new RuntimeException();
			}
			// 将业务数据放入ThreadLocal中
			Map<String, Object> claims = JwtUtil.parseToken(redisToken);
			ThreadLocalUtil.set(claims);
			// 放行
			return true;
		} catch (Exception e) {
			// 将http响应状态码设置为401
			response.setStatus(401);
			// 不放行
			return false;
		}
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// 释放ThreadLocal中的数据
		ThreadLocalUtil.remove();
	}
}
