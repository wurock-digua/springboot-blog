package org.example.springbootblog.service;

import org.example.springbootblog.pojo.User;
import org.example.springbootblog.pojo.UserDTO;
import org.example.springbootblog.pojo.UserVO;

public interface UserService {
	
	/**
	 * 根据用户名查询用户
	 * @param username
	 * @return
	 */
	User getUserByUsername(String username);
	
	/**
	 * 注册用户
	 * @param username
	 * @param password
	 */
	void register(String username, String password);
	
	/**
	 * 获取登录用户详细信息
	 * @return
	 */
	UserVO getUserInfo();
	
	/**
	 * 更新登录用户基本信息
	 * @param userDTO
	 */
	void updateUserInfo(UserDTO userDTO);
	
	/**
	 * 更新登录用户头像
	 * @param userPic
	 */
	void updateUserPic(String userPic);
	
	/**
	 * 更新登录用户密码
	 * @param id
	 * @param password
	 */
	void updateUserPwd(Integer id, String password);
}
