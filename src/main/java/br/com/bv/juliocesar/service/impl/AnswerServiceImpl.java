package br.com.bv.juliocesar.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.bv.juliocesar.entity.Answer;
import br.com.bv.juliocesar.service.AnswerService;
import br.com.bv.juliocesar.utils.ABC;
import br.com.bv.juliocesar.utils.AnswerUtil;

@Service
public class AnswerServiceImpl implements AnswerService {
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	HttpServletRequest request;

	@Override
	public Answer load() {
		Answer answer = new Answer();
		answer.setNumero_casas(8);
		answer.setToken("473ea6e3edbecd2f4ba2b9ffdf1fe2522eeb920a");
		answer.setCifrado("rclom i uiv jg pqa ycmabqwva zibpmz bpiv jg pqa ivaemza. dwtbiqzm");
		answer.setDecifrado("");
		
		return answer;
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
	public Answer decode(Answer answer) {
		
		if (AnswerUtil.isValid(answer) && !answer.getCifrado().isEmpty()) {
				
			Integer numeroCasas = answer.getNumero_casas();
			String cifrado = answer.getCifrado();
			StringBuffer decifrado = new StringBuffer();
			ABC abc = new ABC();
			
			for (int i=0; i<cifrado.length(); i++) {
				char letter = cifrado.charAt(i);
					
				if ( !abc.hasLetter(letter) )
					decifrado.append(letter);
				
				else {
					int position = abc.getPositionByLetter(letter);
					int nextPosition = position - numeroCasas;

					if ( nextPosition <= 0 )
						nextPosition = abc.getMaxPosition() + nextPosition;

					char nextLetter = abc.getLetterByPosition(nextPosition);
					decifrado.append(nextLetter);
				}
			}
				
			answer.setDecifrado(decifrado.toString());
			
		}
		
		return answer;
	}

	@Override
	public Answer encryptAnswer(Answer answer) {
		
		if (AnswerUtil.isValid(answer) && !answer.getDecifrado().isEmpty()) {
			
			try {
				byte[] decifrado = answer.getDecifrado().getBytes("UTF-8");
//				String resumo_criptografico = org.springframework.util.DigestUtils.md5DigestAsHex(decifrado);
				String resumo_criptografico = DigestUtils.sha1Hex(decifrado);
				answer.setResumo_criptografico(resumo_criptografico);

			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return answer;
	}

	@Override
	public String upload(MultipartFile multipartFile) {
		
		String status  = "Upload realizado com sucesso.";
		
		// Cria a pasta que hospedara o arquivo, caso nao exista
		String uploadFolderPath = "resources/files/uploads/";
		String fullUploadFolderPath = request.getServletContext().getRealPath(uploadFolderPath);
		String fileName = multipartFile.getOriginalFilename();
		String finalPath = fullUploadFolderPath + fileName;
		Answer answer;
		
		File uploadFolder = new File(fullUploadFolderPath);
		if (!uploadFolder.exists())
			uploadFolder.mkdirs();
		
		// Cria o arquivo dentro da pasta
		try {
			answer = objectMapper.readValue(multipartFile.getInputStream(), Answer.class);
			objectMapper.writeValue(new File(finalPath), answer);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}
	
}
