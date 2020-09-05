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

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Output implements Serializable {

	private static final long serialVersionUID = 1L;
    
	private Integer id;
	private StatusDiff result;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Diff> diffList;
}
