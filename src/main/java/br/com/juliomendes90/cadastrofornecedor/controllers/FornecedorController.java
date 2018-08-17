package br.com.juliomendes90.cadastrofornecedor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.juliomendes90.cadastrofornecedor.models.Fornecedor;
import br.com.juliomendes90.cadastrofornecedor.models.Produto;
import br.com.juliomendes90.cadastrofornecedor.repository.FornecedorRepository;
import br.com.juliomendes90.cadastrofornecedor.repository.ProdutoRepository;

@Controller
public class FornecedorController {

	@Autowired
	private FornecedorRepository fornecedorRepository; 
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@RequestMapping(value = "/cadastrarFornecedor", method = RequestMethod.GET)
	public String form() {
		return "fornecedor/formFornecedor";
	}
	
	@RequestMapping(value = "/cadastrarFornecedor", method = RequestMethod.POST)
	public String form(Fornecedor fornecedor) {
		
		this.fornecedorRepository.save(fornecedor);
		
		return "redirect:/cadastrarFornecedor";
	}
	
	@RequestMapping(value = "/listarFornecedores")
	public ModelAndView listarFornecedores() {
		Iterable<Fornecedor> listaFornecedores = this.fornecedorRepository.findAll();
		
		ModelAndView mv = new ModelAndView("/index");
		mv.addObject("fornecedores", listaFornecedores);
		
		return mv;
	}
	
	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	public ModelAndView detalhesFornecedor(@PathVariable("codigo") long codigo) {
		Fornecedor fornecedor = this.fornecedorRepository.findByCodigo(codigo);
		
		ModelAndView mv = new ModelAndView("fornecedor/detalhesFornecedor");
		mv.addObject("fornecedor", fornecedor);
		
		return mv;
	}
	
	@RequestMapping(value = "/{codigo}", method = RequestMethod.POST)
	public String detalhesFornecedorPost(@PathVariable("codigo") long codigo, Produto produto) {
		Fornecedor fornecedor = this.fornecedorRepository.findByCodigo(codigo);
		
		produto.setFornecedor(fornecedor);
		
		this.produtoRepository.save(produto);
		
		return "redirect:/{codigo}";
	}
}
