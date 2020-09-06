package com.oak.challenge.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.oak.challenge.business.DiffBusiness;
import com.oak.challenge.model.Input;
import com.oak.challenge.model.Output;
import com.oak.challenge.model.dictionary.StatusDiff;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class DiffBusinessTest {

	@Autowired
	private DiffBusiness diffBusiness;
	
	@Test @Order(1)
	public void shouldBeInsertedOnTheLeft() {
		Integer id = 1;
		String original = "YW5kZXJzb24=";
		
		diffBusiness.leftDiff(new Input(id, original));
		Input left = diffBusiness.getLeft(id);
		assertNotNull(left);
		assertEquals(id, left.getId());
		assertEquals(original, left.getValue());
	}
	
	@Test @Order(2)
	public void shouldBeInsertedOnTheRight() {
		Integer id = 1;
		String original = "YW5kZXJzb24=";
		diffBusiness.rigthDiff(new Input(id, original));
		Input right = diffBusiness.getRight(id);
		assertNotNull(right);
		assertEquals(id, right.getId());
		assertEquals(original, right.getValue());
	}
	
	@Test @Order(3)
	public void shouldCompareSuccessfully() {
		Integer id = 1;
		Output output = diffBusiness.comparisonResult(id);
		
		assertNotNull(output);
		assertEquals(output.getResult(), StatusDiff.EQUAL);
	}
	
	@Test @Order(4)
	public void shouldReplaceLeftAndDiffSize() {
		Integer id = 1;
		String replaced = "YW5kZXJzb24=123";
		
		diffBusiness.leftDiff(new Input(id, replaced));
		Output output = diffBusiness.comparisonResult(id);
		assertNotNull(output);
		assertEquals(output.getResult(), StatusDiff.DIFF_SIZE);
	}
	
	@Test @Order(5)
	public void shouldReplaceRightAndDiffContent() {
		Integer id = 1;
		String replaced = "YW5kZXJzb24=113";
		
		diffBusiness.rigthDiff(new Input(id, replaced));
		Output output = diffBusiness.comparisonResult(id);
		assertNotNull(output);
		assertEquals(output.getResult(), StatusDiff.DIFF_CONTENT);
	}
	
}
