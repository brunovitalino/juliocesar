package br.com.bv.juliocesar.service.impl;

import org.springframework.stereotype.Service;

import br.com.bv.juliocesar.entity.Answer;
import br.com.bv.juliocesar.service.AnswerService;
import br.com.bv.juliocesar.utils.AnswerUtil;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Override
	public void load() {
		
	}

	@Override
	public void save(Answer answer) {
		System.out.println("Answer salvo!");
	}

	@Override
	public void decode(Answer answer) {
		String decifrado = "";
		
		if (answer!=null)
			decifrado = AnswerUtil.decode(answer.getCifrado(), Integer.parseInt(answer.getNumero_casas()));
		
		answer.setDecifrado(decifrado);
	}
		
	
	
}
