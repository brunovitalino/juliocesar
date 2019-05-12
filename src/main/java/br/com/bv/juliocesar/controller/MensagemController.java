package br.com.bv.juliocesar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/mensagem")
public class MensagemController {

	@RequestMapping(method=RequestMethod.GET)
	public String mensagem() {
		
		return "mensagem";
	}
}
