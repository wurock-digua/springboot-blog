package org.example.springbootblog.pojo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
	private Integer id;
	@Pattern(regexp = "^\\S{5,16}$", message = "用户名必须是5-16位的非空字符")
	private String username;
	@NotEmpty
	@Pattern(regexp = "^\\S{1,10}$", message = "密码必须是1-10位的非空字符")
	private String nickname;
	@NotEmpty
	@Email
	private String email;
}
