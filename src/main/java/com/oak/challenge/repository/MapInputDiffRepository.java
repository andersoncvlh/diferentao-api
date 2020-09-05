package com.oak.challenge.repository;

import java.util.HashMap;
import java.util.Map;

import com.oak.challenge.model.Input;

public class MapInputDiffRepository implements InputDiffRepository {

	private static InputDiffRepository instance;
    
    private Map<Integer, Input> leftMap = new HashMap<>();
    private Map<Integer, Input> rightMap = new HashMap<>();
    
    private MapInputDiffRepository(){
    }
    
    public static InputDiffRepository getInstance(){
        if(instance == null){
            instance = new MapInputDiffRepository();
        }
        return instance;
    }

	@Override
	public void putLeft(Input input) {
		this.leftMap.put(input.getId(), input);
	}

	@Override
	public void putRight(Input input) {
		this.rightMap.put(input.getId(), input);
	}

	@Override
	public Input getLeft(Integer id) {
		return this.leftMap.get(id);
	}

	@Override
	public Input getRight(Integer id) {
		return this.rightMap.get(id);
	}
	
}
