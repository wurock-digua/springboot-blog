package org.example.springbootblog.validation.group;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.springbootblog.anno.State;

public class StateValidator implements ConstraintValidator<State, String> {
	@Override
	public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
		// 提供校验规则
		if (value == null) {
			return false;
		}
		if (value.equals("已发布") || value.equals("草稿")) {
			return true;
		}
		return false;
	}
}
