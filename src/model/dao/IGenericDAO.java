package model.dao;

import br.edu.fateczl.pilha.Pilha;

public interface IGenericDAO<T, ID> {
	void salvar(T entidade) throws Exception;
	Pilha<T> buscarTodos() throws Exception;
	void atualizar(T entidade) throws Exception;
	void excluir(ID id) throws Exception;
	T buscarPorID(ID id) throws Exception;
	String toCSV(T entidade);
}
