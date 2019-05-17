package br.com.bv.juliocesar.service;

import br.com.bv.juliocesar.entity.Answer;

public interface AnswerService {

	public String save(Answer answer);
	public Answer load();
	public Answer decode(Answer answer);
	public Answer encryptAnswer(Answer answer);
	
}
