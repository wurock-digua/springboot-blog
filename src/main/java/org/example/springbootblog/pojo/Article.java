package org.example.springbootblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Article {
	private Integer id;
	private String title;
	private String content;
	private String coverImg;
	private String state;
	private Integer categoryId;
	private Integer createUser;
	private LocalDateTime createTime;
	private LocalDateTime updateTime;
}
