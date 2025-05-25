package view;

import javax.swing.SwingUtilities;

import controller.AreaConhecimentoController;
import model.dto.AreaConhecimento;

public class AreaConhecimentoView extends EntityMainView<AreaConhecimento, Integer, AreaConhecimentoController> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AreaConhecimentoView(String titulo, String[] colunas, AreaConhecimentoController controller) {
		super(titulo, colunas, controller);
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {

			AreaConhecimentoController controller = new AreaConhecimentoController();

			String titulo = "Área de Conhecimento";
			String[] colunas = { "ID", "Nome" };

			AreaConhecimentoView janela = new AreaConhecimentoView(titulo, colunas, controller);

			janela.setVisible(true);

		});

	}

	@Override
	protected void abrirTelaCadastro() {

	}

	@Override
	protected Object[] extrairLinha(AreaConhecimento entidade) throws Exception {
		return new Object[] { entidade.getAreaID(), entidade.getNome() };
	}

}
