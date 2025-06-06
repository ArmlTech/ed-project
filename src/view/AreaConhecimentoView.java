package view;
import javax.swing.JFrame;
import controller.AreaConhecimentoController;
import model.dto.AreaConhecimento;

public class AreaConhecimentoView extends GenericCrudView<AreaConhecimento, Integer, AreaConhecimentoController> {

	private static final long serialVersionUID = 1L;
	public AreaConhecimentoView(JFrame parent, String titulo, String[] colunas, AreaConhecimentoController controller) {
		super(parent, titulo, colunas, controller);
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
