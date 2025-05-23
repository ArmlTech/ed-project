package controller;

import br.edu.fateczl.pilha.Pilha;

public interface CrudController<T> {
	
	Pilha<T> listar() throws Exception;
	void salvar(T entidade) throws Exception;
	void excluir(T entidade) throws Exception;
}
