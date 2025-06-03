package controller;

import br.edu.fateczl.Fila;
import br.edu.fateczl.Lista;

public interface IGenericController<T, ID> {
	
	void salvar(T entidade) throws Exception;
	Fila<T> buscarTodos() throws Exception;
	void atualizar(T entidade) throws Exception;
	void excluir(T entidade) throws Exception;
	T buscarPorID(ID id) throws Exception;
	T criarEntidade(T entidade, Lista<String> dadosInput) throws Exception;
}
