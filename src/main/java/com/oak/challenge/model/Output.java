package com.oak.challenge.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.oak.challenge.model.dictionary.StatusDiff;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents the result of a comparison.
 * 
 * @author anderson
 *
 */
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Output implements Serializable {

	private static final long serialVersionUID = 1L;
    
	/**
     * Id of compared data.
     */
	private Integer id;
	/**
     * The title of comparison's result. 
     */
	private StatusDiff result;
    
	/**
     * The list of differences between the data was compared.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Diff> diffList;
}
