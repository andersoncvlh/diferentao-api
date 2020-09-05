package com.oak.challenge.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

public class ResourceCreatedEvent extends ApplicationEvent {

	private static final long serialVersionUID = -3838375729020089572L;
	
	@Getter
	private HttpServletResponse response;
	@Getter
	private Long code;
	
	public ResourceCreatedEvent(Object source, HttpServletResponse response, Long id) {
		super(source);
		this.response = response;
		this.code = id;
	}

}
