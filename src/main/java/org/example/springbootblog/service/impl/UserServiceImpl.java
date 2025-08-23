package org.example.springbootblog.service.impl;

import org.example.springbootblog.mapper.UserMapper;
import org.example.springbootblog.pojo.User;
import org.example.springbootblog.pojo.UserDTO;
import org.example.springbootblog.pojo.UserVO;
import org.example.springbootblog.service.UserService;
import org.example.springbootblog.utils.Md5Util;
import org.example.springbootblog.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 根据用户名查询用户
	 * @param username
	 * @return
	 */
	@Override
	public User getUserByUsername(String username) {
		return userMapper.getUserByUsername(username);
	}
	
	/**
	 * 注册用户
	 * @param username
	 * @param password
	 */
	@Override
	public void register(String username, String password) {
		// 密码加密
		String md5String = Md5Util.getMD5String(password);
		// 添加用户
		User user = User.builder()
				.username(username)
				.password(md5String)
				.createTime(LocalDateTime.now())
				.updateTime(LocalDateTime.now())
				.build();
		
		userMapper.addUser(user);
	}
	
	/**
	 * 获取登录用户详细信息
	 * @return
	 */
	@Override
	public UserVO getUserInfo() {
		Map map = (Map) ThreadLocalUtil.get();
		Integer id = (Integer) map.get("id");
		User user = userMapper.getUserById(id);
		UserVO userVO = UserVO.builder()
				.id(user.getId())
				.username(user.getUsername())
				.nickname(user.getNickname())
				.email(user.getEmail())
				.userPic(user.getUserPic())
				.createTime(user.getCreateTime())
				.updateTime(user.getUpdateTime())
				.build();
		return userVO;
	}
	
	/**
	 * 更新登录用户基本信息
	 * @param userDTO
	 */
	@Override
	public void updateUserInfo(UserDTO userDTO) {
		User user = User.builder()
				.id(userDTO.getId())
				.username(userDTO.getUsername())
				.nickname(userDTO.getNickname())
				.email(userDTO.getEmail())
				.updateTime(LocalDateTime.now())
				.build();
		userMapper.updateUserById(user);
	}
	
	/**
	 * 更新登录用户头像
	 * @param userPic
	 */
	@Override
	public void updateUserPic(String userPic) {
		Map map = (Map) ThreadLocalUtil.get();
		Integer id = (Integer) map.get("id");
		User user = User.builder()
				.id(id)
				.userPic(userPic)
				.build();
		userMapper.updateUserById(user);
	}
	
	/**
	 * 更新登录用户密码
	 * @param id
	 * @param password
	 */
	@Override
	public void updateUserPwd(Integer id, String password) {
		User user = User.builder()
				.id(id)
				.password(password)
				.build();
		userMapper.updateUserById(user);
	}
}
