package com.oak.challenge.repository;

import com.oak.challenge.exception.InputNotFoudException;
import com.oak.challenge.model.Input;

public interface InputDiffRepository {

	void putLeft(Input input);
	void putRight(Input input);
	Input getLeft(Integer id) throws InputNotFoudException;
	Input getRight(Integer id) throws InputNotFoudException;
}
