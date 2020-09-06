package com.oak.challenge.business;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.oak.challenge.exception.InputNotFoudException;
import com.oak.challenge.model.Diff;
import com.oak.challenge.model.Input;
import com.oak.challenge.model.Output;
import com.oak.challenge.model.dictionary.StatusDiff;
import com.oak.challenge.model.dictionary.WhichDiff;
import com.oak.challenge.repository.InputDiffRepository;
import com.oak.challenge.repository.MapInputDiffRepository;

/**
 * Provides business logic
 * 
 * @author anderson
 *
 */
@Service
public class DiffBusiness {

	/**
	 * insert the information from the left 
	 * 
	 * @param input
	 */
	public void leftDiff(Input input) {
		getRepositoryInstance().putLeft(input);
	}
	
	/**
	 * insert the information from the right
	 * 
	 * @param input
	 */
	public void rigthDiff(Input input) {
		getRepositoryInstance().putRight(input);
	}

	/**
	 * Compares and provides the result 
	 * 
	 * @param id Identifier of the data to be compared
	 * @return {@link Output}
	 * @throws InputNotFoudException If the data for the given identifier is not found
	 */
	public Output comparisonResult(Integer id) throws InputNotFoudException {
		Output output = null;
		
		Input left = getLeft(id);
		inputValidate(id, left, WhichDiff.LEFT);
		Input rigth = getRight(id);
		inputValidate(id, rigth, WhichDiff.RIGTH);
		
		output = new Output();
		output.setId(id);
		
		if (left.getValue().equals(rigth.getValue())) {
            output.setResult(StatusDiff.EQUAL);
        } else if (left.getValue().length() != rigth.getValue().length()) {
        	output.setResult(StatusDiff.DIFF_SIZE);
        } else {
        	output.setResult(StatusDiff.DIFF_CONTENT);
        	
        	
        	List<Diff> diffList = new LinkedList<>();
        	int length = 0;
            int offset = -1;
            
            for (int i = 0; i <= left.getValue().length(); i++) {
                if (i < left.getValue().length()
                        && left.getValue().charAt(i) != rigth.getValue().charAt(i)) {
                    length++;
                    if (offset < 0) {
                        offset = i;
                    }
                } else if (offset != -1) {
                    diffList.add(new Diff(offset, length));
                    length = 0;
                    offset = -1;
                }
            }
            
            output.setDiffList(diffList);
        }
		
		return output;
	}

	public Input getRight(Integer id) {
		return getRepositoryInstance().getRight(id);
	}

	public Input getLeft(Integer id) {
		return getRepositoryInstance().getLeft(id);
	}
	
	/**
	 * Check if there is data for informed id
	 * 
	 * @param id
	 * @param input
	 * @param whichDiff
	 * @throws InputNotFoudException
	 */
	private void inputValidate(Integer id, Input input, WhichDiff whichDiff) throws InputNotFoudException {
		if (Objects.isNull(input)) {
			throw new InputNotFoudException("Input not found", id, whichDiff);
		}
	}
	
	/**
	 * @return Singleton instance
	 */
	private InputDiffRepository getRepositoryInstance() {
		return MapInputDiffRepository.getInstance();
	}
}
