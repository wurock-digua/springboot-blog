package org.example.springbootblog.pojo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import org.example.springbootblog.anno.State;
import org.example.springbootblog.validation.group.UpdateGroup;
import org.hibernate.validator.constraints.URL;

@Data
@Builder
public class ArticleDTO {
	
	@NotNull(message = "id不能为空", groups = UpdateGroup.class)
	private Integer id;
	@NotBlank
	@Pattern(regexp = "^\\S{1,10}$", message = "标题必须是5-16位的非空字符")
	private String title;
	@NotBlank
	private String content;
	@NotBlank
	@URL
	private String coverImg;
	@NotBlank
	@State
	private String state;
	@NotNull
	private Integer categoryId;
}
