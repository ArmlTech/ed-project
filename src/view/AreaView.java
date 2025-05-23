package view;

import br.edu.fateczl.pilha.Pilha;
import controller.AreaConhecimentoController;
import model.dto.AreaConhecimento;

public class AreaView extends EntityMainView<AreaConhecimento, AreaConhecimentoController> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AreaView(String titulo, String[] colunas, AreaConhecimentoController controller) {
		super(titulo, colunas, controller);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void abrirTelaCadastro() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Pilha<AreaConhecimento> buscarEntidades() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object[] extrairLinha(AreaConhecimento entidade) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
