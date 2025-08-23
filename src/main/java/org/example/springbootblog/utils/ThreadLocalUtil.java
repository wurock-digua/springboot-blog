package org.example.springbootblog.utils;

/**
 * ThreadLocal工具类
 */
@SuppressWarnings("all")
public class ThreadLocalUtil {
	// 提供ThreadLocal 对象
	private static final ThreadLocal THREAD_LOCAL = new ThreadLocal();
	
	// 根据键获取值
	public static <T> T get() {
		return (T) THREAD_LOCAL.get();
	}
	
	// 存储键值对
	public static void set(Object obj) {
		THREAD_LOCAL.set(obj);
	}
	
	// 清除ThreadLocal防止内存泄露
	public static void remove() {
		THREAD_LOCAL.remove();
	}
}
