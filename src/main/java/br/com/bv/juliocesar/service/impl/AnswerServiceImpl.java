package br.com.bv.juliocesar.service.impl;

import org.springframework.stereotype.Service;

import br.com.bv.juliocesar.entity.Answer;
import br.com.bv.juliocesar.service.AnswerService;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Override
	public void save(Answer answer) {
		System.out.println("Answer salvo!");
	}

	@Override
	public void load() {
		
	}
		
	
	
}
