package br.com.bv.juliocesar.service;

import br.com.bv.juliocesar.entity.Answer;

public interface AnswerService {

	public void save(Answer answer);
	public void load();
	public void decode(Answer answer);
	
}
