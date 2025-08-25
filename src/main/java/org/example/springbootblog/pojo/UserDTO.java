package org.example.springbootblog.pojo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import org.example.springbootblog.validation.group.UpdateGroup;

@Data
@Builder
public class UserDTO {
	@NotNull(message = "id不能为空", groups = UpdateGroup.class)
	private Integer id;
	@Pattern(regexp = "^\\S{5,16}$", message = "用户名必须是5-16位的非空字符")
	private String username;
	@NotEmpty
	@Pattern(regexp = "^\\S{1,10}$", message = "密码必须是1-10位的非空字符")
	private String nickname;
	@NotEmpty
	@Email(message = "邮箱格式错误", groups = UpdateGroup.class)
	private String email;
}
