package org.example.springbootblog.service;

import org.example.springbootblog.pojo.ArticleDTO;
import org.example.springbootblog.pojo.ArticleVO;
import org.example.springbootblog.pojo.PageResult;

public interface ArticleService {
	
	/**
	 * 添加文章
	 * @param articleDTO
	 */
	void addArticle(ArticleDTO articleDTO);
	
	/**
	 * 获取文章列表(条件分页)
	 * @param pageNum
	 * @param pageSize
	 * @param categoryId
	 * @param state
	 * @return
	 */
	PageResult<ArticleVO> getArticleList(Integer pageNum, Integer pageSize, Integer categoryId, String state);
	
	/**
	 * 获取文章详情
	 * @param id
	 * @return
	 */
	ArticleVO getArticleDetail(Integer id);
	
	/**
	 * 更新文章
	 * @param articleDTO
	 */
	void updateArticle(ArticleDTO articleDTO);
	
	/**
	 * 删除文章
	 * @param id
	 */
	void deleteArticle(Integer id);
}
