package br.com.bruno.javaavancado.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.bruno.javaavancado.model.Pessoa;
import br.com.bruno.javaavancado.repository.PessoaRepository;

@Controller
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/cadastropessoa")
	public String inicio() {
		return "redirect:/listarpessoas";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/salvarpessoa")
	public String salvar(Pessoa pessoa) {
		pessoaRepository.save(pessoa);
		return "redirect:/listarpessoas";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/listarpessoas")
	public ModelAndView pessoas() {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		List<Pessoa> pessoasList = pessoaRepository.findAll();
		modelAndView.addObject("pessoas", pessoasList);
		modelAndView.addObject("pessoaObj", new Pessoa());
		return modelAndView;
	}
	
	@GetMapping("/editarpessoa/{idpessoa}")
	public ModelAndView editar(@PathVariable("idpessoa") Long idpessoa) {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);
		modelAndView.addObject("pessoaObj", pessoa);
		return modelAndView;
	}
	
	@GetMapping("/removerpessoa/{idpessoa}")
	public String remover(@PathVariable("idpessoa") Long idpessoa) {
		pessoaRepository.deleteById(idpessoa);
		return "redirect:/listarpessoas";
	}
	
	@PostMapping("/pesquisarpessoa")
	public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa) {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		modelAndView.addObject("pessoas", pessoaRepository.findPessoaByName(nomepesquisa));
		modelAndView.addObject("pessoaObj", new Pessoa());
		return modelAndView;
	}
	
	@GetMapping("/telefones/{idpessoa}")
	public ModelAndView telefones(@PathVariable("idpessoa") Long idpessoa, ModelMap model) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);
		model.addAttribute("pessoaObj", pessoa.get());
		return new ModelAndView("cadastro/telefones", model);
	}
	
	@PostMapping("/addfone/{pessoaid}")
	public ModelAndView addfone(Pessoa pessoaUpdate, @PathVariable("pessoaid") Long pessoaid, ModelMap model) {
		Pessoa pessoa = pessoaRepository.findById(pessoaid).get();
		pessoa.getTelefones().addAll(pessoaUpdate.getTelefones());
		pessoaRepository.save(pessoa);
		model.addAttribute("pessoaObj", pessoa);
		return new ModelAndView("cadastro/telefones", model);
	}
}
