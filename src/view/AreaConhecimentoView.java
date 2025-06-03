package view;

import javax.swing.SwingUtilities;

import controller.AreaConhecimentoController;
import model.dto.AreaConhecimento;

public class AreaConhecimentoView extends GenericCrudView<AreaConhecimento, Integer, AreaConhecimentoController> {

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
		// TODO Auto-generated method stub
	}

	@Override
	protected Object[] extrairLinha(AreaConhecimento entidade) throws Exception {
		return new Object[] { entidade.getId(), entidade.getNome() };
	}

	@Override
	protected void exibirDetalhesDialog(Integer id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'exibirDetalhesDialog'");
	}

	@Override
	protected String getLabelTextEntidadeSelecionada(Integer id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getLabelTextEntidadeSelecionada'");
	}


}
