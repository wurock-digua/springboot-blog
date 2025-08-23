package org.example.springbootblog.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.example.springbootblog.pojo.Result;
import org.example.springbootblog.pojo.User;
import org.example.springbootblog.pojo.UserDTO;
import org.example.springbootblog.pojo.UserVO;
import org.example.springbootblog.service.UserService;
import org.example.springbootblog.utils.JwtUtil;
import org.example.springbootblog.utils.Md5Util;
import org.example.springbootblog.utils.ThreadLocalUtil;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	/**
	 * 注册用户
	 * @param username
	 * @param password
	 * @return
	 */
	@PostMapping("/register")
	public Result register(@RequestParam @NotBlank(message = "用户名不能为空")
							@Pattern(regexp = "^\\S{5,16}$", message = "用户名必须是5-16位的非空字符") String username,
							@RequestParam @NotBlank(message = "密码不能为空")
							@Pattern(regexp = "^\\S{5,16}$", message = "密码必须是5-16位的非空字符") String password) {
		// 判断数据库中是否存在该用户名
		User user = userService.getUserByUsername(username);
		if (user != null) {
			return Result.error("用户名已存在");
		}
		userService.register(username, password);
		return Result.success();
	}
	
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
	@PostMapping("/login")
	public Result login(@RequestParam @NotBlank(message = "用户名不能为空")
						 @Pattern(regexp = "^\\S{5,16}$", message = "用户名必须是5-16位的非空字符") String username,
						 @RequestParam @NotBlank(message = "用户名不能为空")
						 @Pattern(regexp = "^\\S{5,16}$", message = "密码必须是5-16位的非空字符") String password) {
		// 判断数据库中是否存在该用户名
		User user = userService.getUserByUsername(username);
		if (user == null) {
			return Result.error("用户名不存在");
		}
		// 判断密码是否正确
		String md5String = Md5Util.getMD5String(password);
		if (!Md5Util.checkPassword(md5String, user.getPassword())) {
			return Result.error("密码错误");
		}
		
		// 登录成功并生成token
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", user.getId());
		claims.put("username", username);
		String token = JwtUtil.genToken(claims);
		//将token存入redis
		redisTemplate.opsForValue().set(token, token,1, TimeUnit.HOURS);
		return Result.success(token);
	}
	
	/**
	 * 获取登录用户详细信息
	 * @return
	 */
	@GetMapping("/userInfo")
	public Result getUserInfo() {
		UserVO userVO = userService.getUserInfo();
		return Result.success(userVO);
	}
	
	/**
	 * 更新登录用户基本信息
	 * param userDTO
	 * @return
	 */
	@PutMapping("/update")
	public Result updateUserInfo(@Valid @RequestBody UserDTO userDTO) {
		userService.updateUserInfo(userDTO);
		return Result.success();
	}
	
	/**
	 * 更新登录用户头像
	 * param userPic
	 * @return
	 */
	@PatchMapping("/updateAvatar")
	public Result updateUserPic(@URL @RequestParam("avatarUrl") String userPic) {
		userService.updateUserPic(userPic);
		return Result.success();
	}
	
	/**
	 * 更新登录用户密码
	 * param
	 * @return
	 */
	@PatchMapping("/updatePwd")
	public Result updateUserPwd(@RequestBody Map<String,Object> params, @RequestHeader String token) {
		String oldPwd = (String) params.get("old_pwd");
		String newPwd = (String) params.get("new_pwd");
		String rePwd = (String) params.get("re_pwd");
		
		Map map = (Map) ThreadLocalUtil.get();
		Integer id = (Integer) map.get("id");
		String username = (String) map.get("username");
		User loginUser = userService.getUserByUsername(username);
		
		// 验证参数是否存在
		if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
			return Result.error("缺少必要参数");
		}
		// 验证原密码
		if (!Md5Util.checkPassword(oldPwd, loginUser.getPassword())) {
			return Result.error("原密码错误");
		}
		// 验证新密码和重复密码是否一致
		if (!newPwd.equals(rePwd)) {
			return Result.error("新密码和重复密码不一致");
		}
		// 更新密码
		userService.updateUserPwd(id, Md5Util.getMD5String(newPwd));
		return Result.success();
	}
}
