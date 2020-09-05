package com.oak.challenge.business;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.oak.challenge.exception.InputNotFoudException;
import com.oak.challenge.model.Diff;
import com.oak.challenge.model.Input;
import com.oak.challenge.model.Output;
import com.oak.challenge.model.dictionary.StatusDiff;
import com.oak.challenge.model.dictionary.WhichDiff;
import com.oak.challenge.repository.InputDiffRepository;
import com.oak.challenge.repository.MapInputDiffRepository;

@Service
public class DiffBusiness {

	@Autowired
	private MessageSource messageSource;
	
	public void leftDiff(Integer id, Input input) {
		input.setId(id);
		getRepositoryInstance().putLeft(input);
	}
	
	public void rigthDiff(Integer id, Input input) {
		input.setId(id);
		getRepositoryInstance().putRight(input);
	}

	public Output comparisonResult(Integer id) throws InputNotFoudException {
		Output output = null;
		
		Input left = getRepositoryInstance().getLeft(id);
		inputValidate(id, left, WhichDiff.LEFT);
		Input rigth = getRepositoryInstance().getRight(id);
		inputValidate(id, rigth, WhichDiff.RIGTH);
		
		output = new Output();
		output.setId(id);
		
		if (left.getValue().equals(rigth.getValue())) {
            output.setResult(StatusDiff.EQUAL);
        } else if (left.getValue().length() != rigth.getValue().length()) {
        	output.setResult(StatusDiff.DIFF_SIZE);
        } else {
        	output.setResult(StatusDiff.SAME_SIZE_DIFF);
        	
        	
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
	
	private void inputValidate(Integer id, Input input, WhichDiff whichDiff) throws InputNotFoudException {
		if (Objects.isNull(input)) {
			String[] arr = new String[]{String.valueOf(id)};
			throw new InputNotFoudException(messageSource.getMessage("resource.not-found.id", arr,
					LocaleContextHolder.getLocale()), id, whichDiff);
		}
	}
	
	private InputDiffRepository getRepositoryInstance() {
		return MapInputDiffRepository.getInstance();
	}
}
