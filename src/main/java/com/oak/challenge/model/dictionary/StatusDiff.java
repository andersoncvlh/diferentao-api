package com.oak.challenge.model.dictionary;

/**
 * EQUAL - same size and content on the left and right	
 * DIFF_SIZE - different sizes on the left and right
 * DIFF_CONTENT - same size on the left and right, however, the contents are different
 * 
 * @author anderson
 *
 */
public enum StatusDiff {
	EQUAL, 
	DIFF_SIZE,
	DIFF_CONTENT
}
