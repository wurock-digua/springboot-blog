package org.example.springbootblog.controller;

import org.example.springbootblog.pojo.CategoryDTO;
import org.example.springbootblog.pojo.CategoryVO;
import org.example.springbootblog.pojo.Result;
import org.example.springbootblog.service.CategoryService;
import org.example.springbootblog.validation.group.UpdateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	/**
	 * 新增文章分类
	 * param
	 * @return
	 */
	@PostMapping
	public Result addArticleCategory(@Validated @RequestBody CategoryDTO categoryDTO) {
		categoryService.addArticleCategory(categoryDTO);
		return Result.success();
	}
	
	/**
	 * 获取已登录用户创建的文章分类列表
	 * param
	 * @return
	 */
	@GetMapping
	public Result getArticleCategoryList() {
		List<CategoryVO> categoryVOList = categoryService.getArticleCategoryList();
		return Result.success(categoryVOList);
	}
	
	/**
	 * 获取文章分类详情
	 * param
	 * @return
	 */
	@GetMapping("/detail")
	public Result getArticleCategoryDetail(@RequestParam Integer id) {
		CategoryVO categoryVO = categoryService.getArticleCategoryDetail(id);
		return Result.success(categoryVO);
	}
	
	/**
	 * 更新文章分类
	 * param
	 * @return
	 */
	@PutMapping
	public Result updateArticleCategory(@Validated(UpdateGroup.class)
										@RequestBody CategoryDTO categoryDTO) {
		categoryService.updateArticleCategory(categoryDTO);
		return Result.success();
	}
	
	/**
	 * 删除文章分类
	 * param
	 * @return
	 */
	@DeleteMapping
	public Result deleteArticleCategory(Integer id) {
		categoryService.deleteArticleCategory(id);
		return Result.success();
	}
}
