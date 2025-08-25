package org.example.springbootblog.service;

import org.example.springbootblog.pojo.CategoryDTO;
import org.example.springbootblog.pojo.CategoryVO;

import java.util.List;

public interface CategoryService {
	
	/**
	 * 新增文章分类
	 * @param categoryDTO
	 */
	void addArticleCategory(CategoryDTO categoryDTO);
	
	/**
	 * 获取文章分类列表
	 * @return
	 */
	List<CategoryVO> getArticleCategoryList();
	
	/**
	 * 获取文章分类详情
	 * @param id
	 * @return
	 */
	CategoryVO getArticleCategoryDetail(Integer id);
	
	/**
	 * 更新文章分类
	 * @param categoryDTO
	 */
	void updateArticleCategory(CategoryDTO categoryDTO);
	
	/**
	 * 删除文章分类
	 * @param id
	 */
	void deleteArticleCategory(Integer id);
}
