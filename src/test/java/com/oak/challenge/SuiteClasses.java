package com.oak.challenge;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

import com.oak.challenge.integration.DiffControllerTest;
import com.oak.challenge.unit.DiffBusinessTest;

@RunWith(JUnitPlatform.class)
@SelectClasses({
	DiffControllerTest.class,
	DiffBusinessTest.class
})
public class SuiteClasses {

}
