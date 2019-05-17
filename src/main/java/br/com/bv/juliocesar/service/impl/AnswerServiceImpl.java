package br.com.bv.juliocesar.service.impl;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.bv.juliocesar.entity.Answer;
import br.com.bv.juliocesar.service.AnswerService;
import br.com.bv.juliocesar.utils.AnswerUtil;

@Service
public class AnswerServiceImpl implements AnswerService {
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	HttpServletRequest request;

	@Override
	public void load() {
		
	}

	@Override
	public String save(Answer answer) {
		String filesPath = "resources/files/";
		String fullFilesPath = request.getServletContext().getRealPath(filesPath);
		String finalPath = fullFilesPath + "answer.json";		
		
		String status = "Salvo com sucesso.";
		
		try {
			objectMapper.writeValue(new File(finalPath), answer);
			
		} catch (IOException e) {
			//System.err.println(e.getMessage());
			System.out.println(e.getMessage().toUpperCase());
			status = "Falha ao salvar.";
		}
		
		return status;
	}

	@Override
	public void decode(Answer answer) {
		String decifrado = "";
		
		if (answer!=null)
			decifrado = AnswerUtil.decode(answer.getCifrado(), Integer.parseInt(answer.getNumero_casas()));
		
		answer.setDecifrado(decifrado);
	}
		
	
	
}
