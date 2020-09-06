package com.oak.challenge.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import com.oak.challenge.model.Input;
import com.oak.challenge.repository.MapInputDiffRepository;

/**
 * 
 * Provides unit tests
 * 
 * @author anderson
 *
 */
public class UnitTest {

	@Test 
	public void shouldBeInsertedOnTheLeft() {
		Integer id = 1;
		String original = "YW5kZXJzb24=";
		MapInputDiffRepository.getInstance().putLeft(new Input(id, original));
		Input left = MapInputDiffRepository.getInstance().getLeft(id);
		assertNotNull(id);
		assertEquals(id, left.getId());
		assertEquals(original, left.getValue());
	}
	
	@Test 
	public void shouldBeInsertedOnTheRight() {
		Integer id = 1;
		String original = "YW5kZXJzb24=";
		MapInputDiffRepository.getInstance().putRight(new Input(id, original));
		Input right = MapInputDiffRepository.getInstance().getRight(id);
		assertNotNull(id);
		assertEquals(id, right.getId());
		assertEquals(original, right.getValue());
	}
	
}
