package org.neos.batch.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AcceptedValidator implements ConstraintValidator<AcceptedValues, String> {

	private List<String> valueList;

	@Override
	public void initialize(AcceptedValues constraintAnnotation) {
		valueList = new ArrayList<String>();
		for (String val : constraintAnnotation.acceptValues()) {
			valueList.add(val.toLowerCase());
		}
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (!valueList.contains(value.toLowerCase())) {
			return false;
		}
		return true;
	}

}
