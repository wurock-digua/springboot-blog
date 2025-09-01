package org.example.springbootblog.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {
	
	@Autowired
	private UserController userController;
	@Test
	void register() {
		userController.register("admin", "123456");
	}
	
	@Test
	void login() {
	}
	
	@Test
	void getUserInfo() {
	}
	
	@Test
	void updateUserInfo() {
	}
	
	@Test
	void updateUserPic() {
	}
	
	@Test
	void updateUserPwd() {
	}
}