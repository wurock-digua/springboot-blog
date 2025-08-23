package org.example.springbootblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
	private Integer code; // 业务状态吗 0:成功 1:失败
	private String message; // 提示信息
	private T data; // 响应数据
	
	public static <T> Result<T> success() {
		return new Result<>(0, "操作成功", null);
	}
	public static <T> Result<T> success(T data) {
		return new Result<>(0, "操作成功", data);
	}
	public static <T> Result<T> error(String message) {
		return new Result<>(1, message, null);
	}
}
