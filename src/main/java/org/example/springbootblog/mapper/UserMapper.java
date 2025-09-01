package org.example.springbootblog.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.springbootblog.pojo.User;

@Mapper
public interface UserMapper {
	
	/**
	 * 根据用户名查询用户
	 * @param username
	 * @return
	 */
	@Select("select * from user where username = #{username}")
	User getUserByUsername(String username);
	
	/**
	 * 添加用户
	 * @param user
	 */
	@Insert("insert into user(username, password, create_time, update_time) " +
			"values(#{username}, #{password}, #{createTime}, #{updateTime})")
	void addUser(User user);
	
	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	@Select("select * from user where id = #{id}")
	User getUserById(Integer id);
	
	/**
	 * 根据id更新用户
	 * @param user
	 */
	void updateUserById(User user);
}