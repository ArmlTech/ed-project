package controller;

import br.edu.fateczl.Lista;
import br.edu.fateczl.pilha.Pilha;

public interface IGenericController<T, ID> {
	
	void salvar(T entidade) throws Exception;
	Pilha<T> buscarTodos() throws Exception;
	void atualizar(T entidade) throws Exception;
	void excluir(T entidade) throws Exception;
	T buscarPorID(ID id) throws Exception;
	T criarEntidade(Lista<String> dadosInput) throws Exception;
}
