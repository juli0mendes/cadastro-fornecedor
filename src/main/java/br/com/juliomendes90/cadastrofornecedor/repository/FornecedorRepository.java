package br.com.juliomendes90.cadastrofornecedor.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.juliomendes90.cadastrofornecedor.models.Fornecedor;

public interface FornecedorRepository extends CrudRepository<Fornecedor, String> {
	
	Fornecedor findByCodigo(long codigo);
}
