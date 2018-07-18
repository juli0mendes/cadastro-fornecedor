package br.com.juliomendes90.cadastrofornecedor.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author Júlio Mendes
 *
 */
@Controller
public class FornecedorController {

	@RequestMapping("/cadastrarFornecedor")
	public String form() {
		return "fornecedor/formFornecedor";
	}
}
