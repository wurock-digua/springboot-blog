package org.example.springbootblog.service.impl;

import org.example.springbootblog.mapper.CategoryMapper;
import org.example.springbootblog.pojo.Category;
import org.example.springbootblog.pojo.CategoryDTO;
import org.example.springbootblog.pojo.CategoryVO;
import org.example.springbootblog.service.CategoryService;
import org.example.springbootblog.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;
	
	/**
	 * 新增文章分类
	 * @param categoryDTO
	 */
	@Override
	public void addArticleCategory(CategoryDTO categoryDTO) {
		Map map = (Map) ThreadLocalUtil.get();
		Integer userId = (Integer) map.get("id");
		Category category = Category.builder()
				.categoryName(categoryDTO.getCategoryName())
				.categoryAlias(categoryDTO.getCategoryAlias())
				.createUser(userId)
				.createTime(LocalDateTime.now())
				.updateTime(LocalDateTime.now())
				.build();
		categoryMapper.addArticleCategory(category);
	}
	
	/**
	 * 获取文章分类列表
	 * @return
	 */
	@Override
	public List<CategoryVO> getArticleCategoryList() {
		Map map = (Map) ThreadLocalUtil.get();
		Integer userId = (Integer) map.get("id");
		
		List<Category> categoryList = categoryMapper.getArticleCategoryListByCreateUser(userId);
		List<CategoryVO> categoryVOList = new ArrayList<>();
		
		for (Category category : categoryList) {
			CategoryVO categoryVO = CategoryVO.builder()
					.id(category.getId())
					.categoryName(category.getCategoryName())
					.categoryAlias(category.getCategoryAlias())
					.createTime(category.getCreateTime())
					.updateTime(category.getUpdateTime())
					.build();
			categoryVOList.add(categoryVO);
		}
		return categoryVOList;
	}
	
	/**
	 * 获取文章分类详情
	 * @param id
	 * @return
	 */
	@Override
	public CategoryVO getArticleCategoryDetail(Integer id) {
		Category category = categoryMapper.getArticleCategoryById(id);
		CategoryVO categoryVO = CategoryVO.builder()
				.id(category.getId())
				.categoryName(category.getCategoryName())
				.categoryAlias(category.getCategoryAlias())
				.createTime(category.getCreateTime())
				.updateTime(category.getUpdateTime())
				.build();
		return categoryVO;
	}
	
	/**
	 * 更新文章分类
	 * @param categoryDTO
	 */
	@Override
	public void updateArticleCategory(CategoryDTO categoryDTO) {
		Category category = Category
				.builder()
				.id(categoryDTO.getId())
				.categoryName(categoryDTO.getCategoryName())
				.categoryAlias(categoryDTO.getCategoryAlias())
				.updateTime(LocalDateTime.now())
				.build();
		categoryMapper.updateArticleCategoryById(category);
	}
	
	/**
	 * 删除文章分类
	 * @param id
	 */
	@Override
	public void deleteArticleCategory(Integer id) {
		categoryMapper.deleteArticleCategoryById(id);
	}
}