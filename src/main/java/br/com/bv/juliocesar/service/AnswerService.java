package br.com.bv.juliocesar.service;

import org.springframework.web.multipart.MultipartFile;

import br.com.bv.juliocesar.entity.Answer;

public interface AnswerService {

	public String save(Answer answer);
	public Answer load();
	public Answer decode(Answer answer);
	public Answer encryptAnswer(Answer answer);
	public String upload(MultipartFile multipartFile);
	
}
