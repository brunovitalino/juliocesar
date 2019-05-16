package br.com.bv.juliocesar.utils;

import org.springframework.web.servlet.ModelAndView;

import br.com.bv.juliocesar.entity.Answer;

public class CustomModelAndView extends ModelAndView {

	public CustomModelAndView(String viewName) {
		super(viewName);
	}

	public void setMensagemAttributes(Answer msg) {
		this.addObject("numero_casas", msg.getNumero_casas());
		this.addObject("token", msg.getToken());
		this.addObject("cifrado", msg.getCifrado());
		this.addObject("decifrado", msg.getDecifrado());
		this.addObject("resumo_criptografico", msg.getResumo_criptografico());
	}
	
	

}
