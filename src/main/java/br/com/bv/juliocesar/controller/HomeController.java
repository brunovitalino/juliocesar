package br.com.bv.juliocesar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.bv.juliocesar.entity.Answer;
import br.com.bv.juliocesar.service.AnswerService;

@Controller
public class HomeController {
		
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private AnswerService answerService;

	
	@RequestMapping("/")
	public ModelAndView raiz() {
		return new ModelAndView("redirect:home");
	}
	
	@GetMapping("/home")
	public ModelAndView home(Answer answer) {
		
		return new ModelAndView("home_page");
	}
	
	@GetMapping("/newAnswer")
	public ModelAndView newAnswer() {
		return new ModelAndView("redirect:home");
	}
	
	@GetMapping("/loadAnswer")
	public ModelAndView loadAnswerToFields(RedirectAttributes redirectAttributes) {
//		String jsonUrl = "http://codenation/token";
		String jsonUrl = "http://localhost:8080/juliocesar/answer.json";
		
		Answer answer = restTemplate.getForObject(jsonUrl, Answer.class);
		
		redirectAttributes.addFlashAttribute("answer", answer);
		
		return new ModelAndView("redirect:home");
	}
	
	@PostMapping("/decodeAnswer")
	public ModelAndView decodeAnswer(Answer answer, RedirectAttributes redirectAttributes) {
		
		answer = answerService.decode(answer);
		
		redirectAttributes.addFlashAttribute("answer", answer);
		
		return new ModelAndView("redirect:home");
	}
	
	@PostMapping("/encryptAnswer")
	public ModelAndView encryptAnswer(Answer answer, RedirectAttributes redirectAttributes) {
		String status = "";
		
		if (answer.getDecifrado()==null || answer.getDecifrado().isEmpty())
			status = "Falha ao criptografar. Campo decifrado vazio.";
		else
			answer = answerService.encryptAnswer(answer);
		
		redirectAttributes.addFlashAttribute("answer", answer);
		redirectAttributes.addFlashAttribute("status", status);
		
		return new ModelAndView("redirect:home");
	}
	
	@PostMapping("/saveAnswer")
	public ModelAndView saveAnswer(Answer answer, RedirectAttributes redirectAttributes) {

		String status = answerService.save(answer);
		redirectAttributes.addFlashAttribute("status", status);
		
		return new ModelAndView("redirect:home");
	}
	
}
