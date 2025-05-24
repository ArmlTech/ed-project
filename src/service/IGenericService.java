package service;

import br.edu.fateczl.pilha.Pilha;

public interface IGenericService<T, ID> {
	
	void salvar(T entidade) throws Exception;
	Pilha<T> buscarTodos() throws Exception;
	void atualizar(T entidade) throws Exception;
	void excluir(ID id) throws Exception;
	
}
