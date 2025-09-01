package org.example.springbootblog.pojo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springbootblog.validation.group.UpdateGroup;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {
	@NotNull(message = "id不能为空", groups = UpdateGroup.class)
	private Integer id;
	@NotBlank
	private String categoryName;
	@NotBlank
	private String categoryAlias;
	
}
