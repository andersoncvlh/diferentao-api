package com.oak.challenge.exception;

import com.oak.challenge.model.dictionary.WhichDiff;

import lombok.Getter;

/**
 * This exception is thrown when the entered identifier is not valid
 * 
 * @author anderson
 *
 */
public class InputNotFoudException extends RuntimeException { 
    
	private static final long serialVersionUID = 1L;
	
	@Getter 
	private Integer id;
	@Getter 
	private WhichDiff whichDiff;
	
	public InputNotFoudException(String errorMessage, Integer id, WhichDiff whichDiff) {
        super(errorMessage);
        this.id = id;
        this.whichDiff = whichDiff;
    }
	

}
