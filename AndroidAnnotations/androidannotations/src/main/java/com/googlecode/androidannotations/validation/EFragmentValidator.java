/**
 * Copyright (C) 2010-2012 eBusiness Information, Excilys Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed To in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.googlecode.androidannotations.validation;

import java.lang.annotation.Annotation;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;

import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.helper.IdAnnotationHelper;
import com.googlecode.androidannotations.helper.IdValidatorHelper;
import com.googlecode.androidannotations.helper.IdValidatorHelper.FallbackStrategy;
import com.googlecode.androidannotations.model.AnnotationElements;
import com.googlecode.androidannotations.rclass.IRClass;
import com.googlecode.androidannotations.rclass.IRClass.Res;

public class EFragmentValidator implements ElementValidator {

	private final IdValidatorHelper validatorHelper;

	public EFragmentValidator(ProcessingEnvironment processingEnv, IRClass rClass) {
		IdAnnotationHelper annotationHelper = new IdAnnotationHelper(processingEnv, getTarget(), rClass);
		validatorHelper = new IdValidatorHelper(annotationHelper);
	}

	@Override
	public Class<? extends Annotation> getTarget() {
		return EFragment.class;
	}

	@Override
	public boolean validate(Element element, AnnotationElements validatedElements) {

		IsValid valid = new IsValid();

		validatorHelper.isNotFinal(element, valid);

		validatorHelper.resIdsExist(element, Res.LAYOUT, FallbackStrategy.ALLOW_NO_RES_ID, valid);

		validatorHelper.isNotPrivate(element, valid);

		validatorHelper.hasEmptyConstructor(element, valid);

		validatorHelper.extendsFragment(element, valid);

		return valid.isValid();
	}

}
