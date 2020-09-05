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
import com.oak.challenge.exception.InputNotFoudException;
import com.oak.challenge.model.Input;
import com.oak.challenge.model.Output;

@V1RestController
public class DiffController extends AbstractResource {

	@Autowired
	private DiffBusiness business;
	
	@PostMapping(path = "diff/{id}/left", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> leftDiff(@PathVariable Integer id, @Valid @RequestBody(required = true) Input input, HttpServletResponse response) {
		business.leftDiff(id, input);
		return responseCreated(id, response);
	}
	
	@PostMapping(path = "diff/{id}/right", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> rigthDiff(@PathVariable Integer id, @Valid @RequestBody(required = true) Input input, HttpServletResponse response) {
		business.rigthDiff(id, input);
		return responseCreated(id, response);
	}

	@GetMapping("diff/{id}")
	public ResponseEntity<?> comparisonResult(@PathVariable Integer id) {
		Output output = null;
		try {
			output = business.comparisonResult(id);
			return ResponseEntity.ok(output);
		} catch (InputNotFoudException e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
		}
	}
	
}
