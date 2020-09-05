package com.oak.challenge.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter 
@NoArgsConstructor @AllArgsConstructor
@ToString @EqualsAndHashCode
public class Diff implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private Integer offset;
    private Integer length;
}
