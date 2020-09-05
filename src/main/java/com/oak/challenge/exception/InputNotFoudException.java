package com.oak.challenge.exception;

import com.oak.challenge.model.dictionary.WhichDiff;

import lombok.Getter;

public class InputNotFoudException extends Exception { 
    
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
	
	public InputNotFoudException(Throwable e, Integer id, WhichDiff whichDiff) {
        super(e);
        this.id = id;
        this.whichDiff = whichDiff;
    }
	
	public InputNotFoudException(String errorMessage, Throwable throwable, Integer id, WhichDiff whichDiff) {
        super(errorMessage, throwable);
        this.id = id;
        this.whichDiff = whichDiff;
    }

}
