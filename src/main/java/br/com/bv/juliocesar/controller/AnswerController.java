package br.com.bv.juliocesar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.com.bv.juliocesar.entity.Answer;
import br.com.bv.juliocesar.service.AnswerService;
import br.com.bv.juliocesar.utils.CustomModelAndView;
import br.com.bv.juliocesar.utils.AnswerUtil;

@RestController
@RequestMapping("/answer")
public class AnswerController {
		
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private AnswerService answerService;
	

	@GetMapping("/codenation")
	public ModelAndView loadCodenation() {
		String answer = restTemplate.getForObject("https://api.codenation.dev/v1/challenge/dev-ps/generate-data?token=473ea6e3edbecd2f4ba2b9ffdf1fe2522eeb920a", String.class);
		
		ModelAndView modelAndView = new ModelAndView("home_page");
		modelAndView.addObject("answer", answer);
		
		return modelAndView;
	}
	
	@GetMapping()
	public ModelAndView load() {
		Answer answer = new Answer();
		AnswerUtil.setDefaultAttributesValues(answer);
		
		CustomModelAndView customModelAndView = new CustomModelAndView("home_page");
		customModelAndView.setMensagemAttributes(answer);
		
		return customModelAndView;
	}
	
}
