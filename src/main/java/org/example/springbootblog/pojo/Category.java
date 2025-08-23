package org.example.springbootblog.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Category {
	private Integer id;
	private String categoryName;
	private String categoryAlias;
	private Integer createUsr;
	private LocalDateTime createTime;
}
