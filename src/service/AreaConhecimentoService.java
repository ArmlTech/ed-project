package service;

import br.edu.fateczl.pilha.Pilha;
import model.dao.AreaConhecimentoDAO;
import model.dto.AreaConhecimento;

public class AreaConhecimentoService implements IGenericService<AreaConhecimento, Integer> {

	private final AreaConhecimentoDAO dao;

	public AreaConhecimentoService() {

		this.dao = new AreaConhecimentoDAO();

	}

	@Override
	public void salvar(AreaConhecimento entidade) throws Exception {

		dao.salvar(entidade);

	}

	@Override
	public Pilha<AreaConhecimento> buscarTodos() throws Exception {

		return dao.buscarTodos();

	}

	@Override
	public void atualizar(AreaConhecimento entidade) throws Exception {

		dao.atualizar(entidade);

	}

	@Override
	public void excluir(Integer id) throws Exception {
		dao.excluir(id);
	}

	@Override
	public AreaConhecimento buscarPorID(Integer id) throws Exception {
		return dao.buscarPorID(id);
	}

}
