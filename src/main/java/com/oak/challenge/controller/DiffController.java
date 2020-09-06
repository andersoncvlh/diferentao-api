package com.oak.challenge.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.oak.challenge.business.DiffBusiness;
import com.oak.challenge.config.V1RestController;
import com.oak.challenge.model.Input;
import com.oak.challenge.model.Output;

/**
 * provides all services in / v1 / diff
 * 
 * @author anderson
 *
 */
@V1RestController
public class DiffController extends AbstractResource {

	@Autowired
	private DiffBusiness business;
	
	/**
	 * Left endpoint
	 * 
	 * @param input Data input
	 * @return {@link HttpStatus} CREATED
	 */
	@PostMapping(path = "diff/left", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> leftDiff(@Valid @RequestBody(required = true) Input input, HttpServletResponse response) {
		business.leftDiff(input);
		return responseCreated(input.getId(), response);
	}
	
	/**
	 * Right endpoint
	 * 
	 * @param input Data input
	 * @return {@link HttpStatus} CREATED
	 */
	@PostMapping(path = "diff/right", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> rigthDiff(@Valid @RequestBody(required = true) Input input, HttpServletResponse response) {
		business.rigthDiff(input);
		return responseCreated(input.getId(), response);
	}

	/**
	 * 
	 * @param id
	 * @return {@link HttpStatus} 406 - NOT_ACCEPTABLE if the operation id doesn't exist
	 * or has just only one side information (just left or right).
	 * {@link  HttpStatus} 200 - OK for success. {@link Output} 
	 */
	@GetMapping(path = "diff/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Output> comparisonResult(@PathVariable Integer id) {
		Output output = business.comparisonResult(id);
		return ResponseEntity.ok(output);
	}
	
}
