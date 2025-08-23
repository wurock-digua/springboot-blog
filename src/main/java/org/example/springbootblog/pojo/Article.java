package org.example.springbootblog.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Article {
	private Integer id;
	private String title;
	private String content;
	private String coverImg;
	private String state;
	private Integer categoryId;
	private Integer createUsr;
	private LocalDateTime createTime;
	private LocalDateTime updateTime;
}
