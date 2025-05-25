package controller;

import br.edu.fateczl.pilha.Pilha;
import model.dto.AreaConhecimento;
import service.AreaConhecimentoService;

public class AreaConhecimentoController implements IGenericController<AreaConhecimento, Integer> {

	private final AreaConhecimentoService service; 
	
	public AreaConhecimentoController() {
		this.service = new AreaConhecimentoService();
	}
	
	@Override
	public void salvar(AreaConhecimento entidade) throws Exception {

		service.salvar(entidade);

	}

	@Override
	public Pilha<AreaConhecimento> buscarTodos() throws Exception {

		return service.buscarTodos();

	}

	@Override
	public void atualizar(AreaConhecimento entidade) throws Exception {

		service.atualizar(entidade);

	}

	@Override
	public void excluir(Integer id) throws Exception {
		service.excluir(id);
	}

	@Override
	public AreaConhecimento buscarPorID(Integer id) throws Exception {
		return service.buscarPorID(id);
	}


}
