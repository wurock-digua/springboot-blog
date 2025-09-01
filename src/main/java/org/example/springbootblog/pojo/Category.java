package org.example.springbootblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String categoryName;
	private String categoryAlias;
	private Integer createUser;
	private LocalDateTime createTime;
	private LocalDateTime updateTime;
}
