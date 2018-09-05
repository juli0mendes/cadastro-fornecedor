package br.com.juliomendes90.cadastrofornecedor.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String form(@Valid Fornecedor fornecedor, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");

			return "redirect:/cadastrarFornecedor";
		}

		this.fornecedorRepository.save(fornecedor);

		attributes.addFlashAttribute("mensagem", "Fornecedor cadastrado!");

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

		Iterable<Produto> produtos = this.produtoRepository.findByFornecedor(fornecedor);

		mv.addObject("produtos", produtos);

		return mv;
	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.POST)
	public String detalhesFornecedorPost(@PathVariable("codigo") long codigo, @Valid Produto produto,
			BindingResult result, RedirectAttributes attributes) {
		Fornecedor fornecedor = this.fornecedorRepository.findByCodigo(codigo);

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/{codigo}";
		}

		produto.setFornecedor(fornecedor);

		this.produtoRepository.save(produto);

		attributes.addFlashAttribute("mensagem", "Produto cadastrado!");

		return "redirect:/{codigo}";
	}
}
