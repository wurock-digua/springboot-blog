package org.example.springbootblog.anno;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.springbootblog.validation.group.StateValidator;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StateValidator.class)
public @interface State {
	
	// 校验错误提示信息
	String message() default "state参数的值只能是已发布或者草稿";
	// 分组
	Class<?>[] groups() default {};
	// //负载  获取到State注解的附加信息
	Class<? extends Payload>[] payload() default {};
}
