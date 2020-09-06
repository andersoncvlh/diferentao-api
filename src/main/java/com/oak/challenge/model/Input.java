package com.oak.challenge.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents the data that clients request to compare.
 * 
 * @author anderson
 *
 */
@Getter @Setter 
@NoArgsConstructor @AllArgsConstructor
public class Input implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
     * Number used to identify pairs of data to compare.
     */
	@NotNull
	private Integer id;
	
	/**
     * The value in base64 encoding that will be compared.
     */
	@NotBlank
	private String value;
	
}
