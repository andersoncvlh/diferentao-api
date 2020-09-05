package com.oak.challenge.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.oak.challenge.event.ResourceCreatedEvent;

public abstract class AbstractResource {

	@Autowired
	private ApplicationEventPublisher publisher;
	
	public ApplicationEventPublisher getPublisher() {
		return publisher;
	}
	
	protected ResponseEntity<Void> responseCreated(Number id, HttpServletResponse response) {
		getPublisher().publishEvent(new ResourceCreatedEvent(this, response, id.longValue()));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
