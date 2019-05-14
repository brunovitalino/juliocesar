package br.com.bv.juliocesar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.com.bv.juliocesar.entity.Mensagem;
import br.com.bv.juliocesar.utils.CustomModelAndView;
import br.com.bv.juliocesar.utils.ObjectUtil;

@Controller
@RequestMapping("/mensagem")
public class MensagemController {
		
	@Autowired
	private RestTemplate restTemplate;
		
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView mensagem() {
		
		return new ModelAndView("mensagem_page");
	}

	@RequestMapping(value="/remota", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView mensagemRemota() {
		String answer = restTemplate.getForObject("https://api.codenation.dev/v1/challenge/dev-ps/generate-data?token=473ea6e3edbecd2f4ba2b9ffdf1fe2522eeb920a", String.class);
		
		ModelAndView modelAndView = new ModelAndView("mensagem_page");
		modelAndView.addObject("answer", answer);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/local", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView mensagemLocal() {
		Mensagem msg = new Mensagem();
		ObjectUtil.setDefaultAttributesValues(msg);
		
		CustomModelAndView customModelAndView = new CustomModelAndView("mensagem_page");
		customModelAndView.setMensagemAttributes(msg);
		
		return customModelAndView;
	}
	
}
