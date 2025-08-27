package org.example.springbootblog.controller;

import jakarta.validation.Valid;
import org.example.springbootblog.pojo.ArticleDTO;
import org.example.springbootblog.pojo.ArticleVO;
import org.example.springbootblog.pojo.PageResult;
import org.example.springbootblog.pojo.Result;
import org.example.springbootblog.service.ArticleService;
import org.example.springbootblog.validation.group.UpdateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@Validated
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	/**
	 * 新增文章
	 * @param articleDTO
	 */
	@PostMapping
	public Result addArticle(@Valid @RequestBody ArticleDTO articleDTO) {
		articleService.addArticle(articleDTO);
		return Result.success();
	}
	
	/**
	 * 获取文章列表(条件分页)
	 * @param pageNum
	 * @param pageSize
	 * @param categoryId
	 * @param state
	 * @return
	 */
	@GetMapping
	public Result<PageResult<ArticleVO>> getArticleList(@RequestParam(defaultValue = "1") Integer pageNum,
														@RequestParam(defaultValue = "5") Integer pageSize,
														@RequestParam(required = false) Integer categoryId,
														@RequestParam(required = false) String state) {
		PageResult<ArticleVO> pageResult = articleService.getArticleList(pageNum, pageSize, categoryId, state);
		return Result.success(pageResult);
	}
	
	/**
	 * 获取文章详情
	 * @param id
	 * @return
	 */
	@GetMapping("/detail")
	public Result getArticleDetail(@RequestParam Integer id) {
		ArticleVO articleVO = articleService.getArticleDetail(id);
		return Result.success(articleVO);
	}
	
	/**
	 * 更新文章信息
	 * @param articleDTO
	 * @return
	 */
	@PutMapping
	public Result updateArticle(@RequestBody @Validated(UpdateGroup.class) ArticleDTO articleDTO) {
		articleService.updateArticle(articleDTO);
		return Result.success();
	}
	
	/**
	 * 删除文章
	 * @param id
	 * @return
	 */
	@DeleteMapping
	public Result deleteArticle(@RequestParam Integer id) {
		articleService.deleteArticle(id);
		return Result.success();
	}
}
