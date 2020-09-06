package com.oak.challenge.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 
 * objects hold values of differences.
 * 
 * @author anderson
 *
 */
@Getter @Setter 
@NoArgsConstructor @AllArgsConstructor
public class Diff implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
     * The index where the difference comes.
     */
    private Integer offset;
    /**
     * The quantity of different elements starting from offset.
     */
    private Integer length;
}
