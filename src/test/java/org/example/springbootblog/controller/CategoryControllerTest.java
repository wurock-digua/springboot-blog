package org.example.springbootblog.controller;

import org.example.springbootblog.pojo.CategoryDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CategoryControllerTest {
	
	@Autowired
	private CategoryController categoryController;
	@Test
	void addArticleCategoryTest() {
		CategoryDTO categoryDTO =new CategoryDTO(null,"测试分类","测试别名");
		categoryController.addArticleCategory(categoryDTO);
	}
	
//	@Test
	void getArticleCategoryList() {
	}
	
//	@Test
	void getArticleCategoryDetail() {
	}
	
//	@Test
	void updateArticleCategory() {
	}
	
//	@Test
	void deleteArticleCategory() {
	}
}