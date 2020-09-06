package com.oak.challenge.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.MediaType;

import com.oak.challenge.model.Input;
import com.oak.challenge.model.Output;
import com.oak.challenge.model.dictionary.StatusDiff;

/**
 * 
 * Provides integration tests
 * 
 * @author anderson
 *
 */
@TestMethodOrder(OrderAnnotation.class)
public class DiffControllerTest extends AbstractTesteIntegracao {
	
	@Test @Order(1)
	public void shouldNotAccetableGetMethod() throws Exception {
		Integer id = 20 ;
		mockMvc.perform(get(String.format("/v1/diff/%d", id))
    			.contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
    	.andExpect(status().isNotAcceptable())
    	.andDo(payloadExtractor)
    	.andReturn();
		
		Output output = payloadExtractor.as(Output.class);
		
		output.getDiffList();
		
	}
	
	@Test @Order(2)
	public void shouldInsertLeft() throws Exception {
		Input left = new Input(1, "dGVzdGFuZG8xMjM=");
		String json = this.json(left);
		
		simpleLeftRequest(json);
	}

	@Test @Order(3)
	public void shouldInsertRight() throws Exception {
		Input right = new Input(1, "dGVzdGFuZG8xMjM=");
		String json = this.json(right);
		
		simpleRightRequest(json);
	}

	@Test @Order(4)
	public void shouldSuccessGetMethod() throws Exception {
		Input input = new Input(1, "dGVzdGFuZG8xMjM=");
		String json = this.json(input);
		simpleLeftRequest(json);
		simpleRightRequest(json);
		
		Integer id = 1 ;
		mockMvc.perform(get(String.format("/v1/diff/%d", id))
    			.contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
    	.andExpect(status().isOk())
    	.andDo(payloadExtractor)
    	.andReturn();
		
		Output output = payloadExtractor.as(Output.class);
		output.getResult();
		Assert.assertEquals(output.getResult(), StatusDiff.EQUAL);
	}
	
	@Test @Order(5)
	public void shouldSuccessGetMethodDiffSize() throws Exception {
		Input input = new Input(1, "dGVzdGFuZG8xMjM=");
		String json = this.json(input);
		simpleLeftRequest(json);
		
		Input inputRight = new Input(1, "dGVzdGFuZG8xMjM0NQ==");
		String jsonRight = this.json(inputRight);
		simpleRightRequest(jsonRight);
		
		Integer id = 1 ;
		mockMvc.perform(get(String.format("/v1/diff/%d", id))
    			.contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
    	.andExpect(status().isOk())
    	.andDo(payloadExtractor)
    	.andReturn();
		
		Output output = payloadExtractor.as(Output.class);
		output.getResult();
		Assert.assertEquals(output.getResult(), StatusDiff.DIFF_SIZE);
	}
	
	@Test @Order(6)
	public void shouldSuccessGetMethodDiffContent() throws Exception {
		Input input = new Input(1, "dGVzdGFuZG8xMjM=");
		String json = this.json(input);
		simpleLeftRequest(json);
		
		Input inputRight = new Input(1, "pGVzdGFuZG8xMjM=");
		String jsonRight = this.json(inputRight);
		simpleRightRequest(jsonRight);
		
		Integer id = 1 ;
		mockMvc.perform(get(String.format("/v1/diff/%d", id))
    			.contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
    	.andExpect(status().isOk())
    	.andDo(payloadExtractor)
    	.andReturn();
		
		Output output = payloadExtractor.as(Output.class);
		output.getResult();
		Assert.assertEquals(output.getResult(), StatusDiff.DIFF_CONTENT);
	}
	
	@Test @Order(7)
	public void shouldFailValidation() throws Exception {
		Input right = new Input();
		String json = this.json(right);
		
		mockMvc.perform(post("/v1/diff/left")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(json))
		.andDo(print())
    	.andExpect(status().isBadRequest())
    	.andReturn();
	}
	
	@Test @Order(8)
	public void shouldMessageNotReadable() throws Exception {
		String json = this.json("qualquer");
		
		mockMvc.perform(post("/v1/diff/left")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(json))
		.andDo(print())
    	.andExpect(status().isBadRequest())
    	.andReturn();
	}
	
	@Test @Order(9)
	public void shouldMediaTypeNotSupported() throws Exception {
		String json = this.json("qualquer");
		
		mockMvc.perform(post("/v1/diff/left")
    			.contentType(MediaType.APPLICATION_PDF_VALUE)
    			.content(json))
		.andDo(print())
    	.andExpect(status().isUnsupportedMediaType())
    	.andReturn();
	}

	private void simpleLeftRequest(String json) throws Exception {
		mockMvc.perform(post("/v1/diff/left")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(json))
		.andDo(print())
    	.andExpect(status().isCreated())
    	.andReturn();
	}
	
	private void simpleRightRequest(String json) throws Exception {
		mockMvc.perform(post("/v1/diff/right")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(json))
		.andDo(print())
    	.andExpect(status().isCreated())
    	.andReturn();
	}
	
}
