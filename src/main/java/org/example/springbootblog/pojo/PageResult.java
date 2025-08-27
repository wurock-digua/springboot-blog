package org.example.springbootblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResult<T> {
	// 总记录数
	private Long total;
	// 当前页数据
	private List<T> items;
}
