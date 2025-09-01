package org.example.springbootblog.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.springbootblog.pojo.Article;

@Mapper
public interface ArticleMapper {
	
	/**
	 * 添加文章
	 * @param article
	 */
	@Insert("insert into article(title, content, cover_img, state, category_id, create_user, create_time, update_time) " +
			"values(#{title}, #{content}, #{coverImg}, #{state}, #{categoryId}, #{createUser}, #{createTime}, #{updateTime})")
	void addArticle(Article article);
	
	/**
	 * 查询文章列表(条件分页)
	 * @param page
	 * @param categoryId
	 * @param state
	 * @return
	 */
	Page<Article> getArticleListPage(Page<Article> page, Integer categoryId, String state);
	
	/**
	 * 根据id查询文章
	 * @param id
	 * @return
	 */
	@Select("select * from article where id = #{id}")
	Article getArticleById(Integer id);
	
	/**
	 * 根据id更新文章
	 * @param article
	 */
	void updateArticleById(Article article);
	
	/**
	 * 根据id删除文章
	 * @param id
	 */
	@Delete("delete from article where id = #{id}")
	void deleteArticleById(Integer id);
}