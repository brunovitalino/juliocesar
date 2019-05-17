package br.com.bv.juliocesar.service;

import br.com.bv.juliocesar.entity.Answer;

public interface AnswerService {

	public String save(Answer answer);
	public void load();
	public void decode(Answer answer);
	
}
