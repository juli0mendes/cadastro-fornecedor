package br.com.juliomendes90.cadastrofornecedor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.juliomendes90.cadastrofornecedor.models.Fornecedor;
import br.com.juliomendes90.cadastrofornecedor.repository.FornecedorRepository;

@Controller
public class FornecedorController {

	@Autowired
	private FornecedorRepository repository; 
	
	@RequestMapping(value = "/cadastrarFornecedor", method = RequestMethod.GET)
	public String form() {
		return "fornecedor/formFornecedor";
	}
	
	@RequestMapping(value = "/cadastrarFornecedor", method = RequestMethod.POST)
	public String form(Fornecedor fornecedor) {
		
		this.repository.save(fornecedor);
		
		return "redirect:/cadastrarFornecedor";
	}
	
	@RequestMapping(value = "/listarFornecedores")
	public ModelAndView listarFornecedores() {
		ModelAndView mv = new ModelAndView("/index");
		Iterable<Fornecedor> listaFornecedores = this.repository.findAll();
		mv.addObject("fornecedores", listaFornecedores);
		
		return mv;
	}
}
