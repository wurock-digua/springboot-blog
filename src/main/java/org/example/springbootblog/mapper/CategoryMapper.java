package org.example.springbootblog.mapper;

import org.apache.ibatis.annotations.*;
import org.example.springbootblog.pojo.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {
	
	/**
	 * 添加文章分类
	 * @param category
	 */
	@Insert("insert into category (category_name, category_alias, create_user, create_time, update_time) " +
			"values(#{categoryName}, #{categoryAlias}, #{createUser}, #{createTime}, #{updateTime})")
	void addArticleCategory(Category category);
	
	/**
	 * 根据create_user查询文章分类
	 * @param userId
	 * @return
	 */
	@Select("select * from category where create_user = #{userId}")
	List<Category> getArticleCategoryListByCreateUser(Integer userId);
	
	/**
	 * 根据id查询文章分类
	 * @param id
	 * @return
	 */
	@Select("select * from category where id = #{id}")
	Category getArticleCategoryById(Integer id);
	
	/**
	 * 根据id更新文章分类
	 * @param category
	 */
	@Update("update category set category_name = #{categoryName}, category_alias = #{categoryAlias}, " +
			"update_time = #{updateTime} where id = #{id}")
	void updateArticleCategoryById(Category category);
	
	/**
	 * 根据id删除文章分类
	 * @param id
	 */
	@Delete("delete from category where id = #{id}")
	void deleteArticleCategoryById(Integer id);
}
