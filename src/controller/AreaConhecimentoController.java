package controller;

import java.io.IOException;

import br.edu.fateczl.Fila;
import br.edu.fateczl.Lista;
import model.dto.AreaConhecimento;
import service.AreaConhecimentoService;

public class AreaConhecimentoController implements IGenericController<AreaConhecimento, Integer> {

	private final AreaConhecimentoService service; 
	
	public AreaConhecimentoController() throws IOException {
		this.service = new AreaConhecimentoService();
	}
	
	@Override
	public void salvar(AreaConhecimento entidade) throws Exception {

		service.salvar(entidade);

	}

	@Override
	public Fila<AreaConhecimento> buscarTodos() throws Exception {

		return service.buscarTodos();

	}

	@Override
	public void atualizar(AreaConhecimento entidade) throws Exception {

		service.atualizar(entidade);

	}

	@Override
	public void excluir(AreaConhecimento entidade) throws Exception {
		service.excluir(entidade.getId());
	}

	@Override
	public AreaConhecimento buscarPorID(Integer id) throws Exception {
		return service.buscarPorID(id);
	}

	@Override
	public AreaConhecimento criarEntidade(AreaConhecimento entidade, Lista<String> dadosInput) throws Exception {
		Integer id = Integer.parseInt(dadosInput.get(0));
		String nome = dadosInput.get(1);

		AreaConhecimento area = new AreaConhecimento(id, nome);
		return area;
	}


}
