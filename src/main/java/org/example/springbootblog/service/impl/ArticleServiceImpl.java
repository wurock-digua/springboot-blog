package org.example.springbootblog.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.springbootblog.mapper.ArticleMapper;
import org.example.springbootblog.pojo.Article;
import org.example.springbootblog.pojo.ArticleDTO;
import org.example.springbootblog.pojo.ArticleVO;
import org.example.springbootblog.pojo.PageResult;
import org.example.springbootblog.service.ArticleService;
import org.example.springbootblog.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleMapper articleMapper;
	
	/**
	 * 添加文章
	 * @param articleDTO
	 */
	@Override
	public void addArticle(ArticleDTO articleDTO) {
		Map map = (Map) ThreadLocalUtil.get();
		Integer userId = (Integer) map.get("id");
		Article article = Article.builder()
				.title(articleDTO.getTitle())
				.content(articleDTO.getContent())
				.coverImg(articleDTO.getCoverImg())
				.state(articleDTO.getState())
				.categoryId(articleDTO.getCategoryId())
				.createUser(userId)
				.createTime(LocalDateTime.now())
				.updateTime(LocalDateTime.now())
				.build();
		articleMapper.addArticle(article);
	}
	
	/**
	 * 获取文章列表(条件分页)
	 * @param pageNum
	 * @param pageSize
	 * @param categoryId
	 * @param state
	 * @return
	 */
	@Override
	public PageResult<ArticleVO> getArticleList(Integer pageNum, Integer pageSize,
											 Integer categoryId, String state) {
		// 创建分页对象
		Page<Article> page = new Page<>(pageNum, pageSize);
		// 查询
		Page<Article> articlePage = articleMapper.getArticleListPage(page, categoryId, state);
		// 将查询结果转换为 ArticleVO
		List<ArticleVO> articleVOList = new ArrayList<>();
		for (Article article : articlePage.getRecords()) {
			ArticleVO articleVO = ArticleVO.builder()
					.id(article.getId())
					.title(article.getTitle())
					.content(article.getContent())
					.coverImg(article.getCoverImg())
					.state(article.getState())
					.categoryId(article.getCategoryId())
					.createTime(article.getCreateTime())
					.updateTime(article.getUpdateTime())
					.build();
			articleVOList.add(articleVO);
		}
		
		PageResult<ArticleVO> pageResult = PageResult.<ArticleVO>builder()
				.total(articlePage.getTotal())
				.items(articleVOList)
				.build();
		return pageResult;
	}
	
	/**
	 * 获取文章详情
	 * @param id
	 * @return
	 */
	@Override
	public ArticleVO getArticleDetail(Integer id) {
		Article article = articleMapper.getArticleById(id);
		ArticleVO articleVO = ArticleVO.builder()
				.id(article.getId())
				.title(article.getTitle())
				.content(article.getContent())
				.coverImg(article.getCoverImg())
				.state(article.getState())
				.categoryId(article.getCategoryId())
				.createTime(article.getCreateTime())
				.updateTime(article.getUpdateTime())
				.build();
		return articleVO;
	}
	
	/**
	 * 更新文章
	 * @param articleDTO
	 */
	@Override
	public void updateArticle(ArticleDTO articleDTO) {
		
		Article article = Article.builder()
				.id(articleDTO.getId())
				.title(articleDTO.getTitle())
				.content(articleDTO.getContent())
				.coverImg(articleDTO.getCoverImg())
				.state(articleDTO.getState())
				.categoryId(articleDTO.getCategoryId())
				.updateTime(LocalDateTime.now())
				.build();
		articleMapper.updateArticleById(article);
	}
	
	/**
	 * 删除文章
	 * @param id
	 */
	@Override
	public void deleteArticle(Integer id) {
		articleMapper.deleteArticleById(id);
	}
	
}
