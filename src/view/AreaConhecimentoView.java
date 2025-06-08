package view;
import javax.swing.JFrame;
import controller.AreaConhecimentoController;
import model.dto.AreaConhecimento;
import util.Alerta;
import view.GenericFormDialog.FormMode;

public class AreaConhecimentoView extends GenericCrudView<AreaConhecimento, Integer, AreaConhecimentoController> {

	private static final long serialVersionUID = 1L;
	
	private final static String titulo = "Gerenciar Áreas de Conhecimento";
	private final static String[] colunas = { "ID", "Nome" };
	private final static AreaConhecimentoController controller = new AreaConhecimentoController();
	
	public AreaConhecimentoView(JFrame parent) {
		super(parent, titulo, colunas, controller);
	}

	@Override
	protected void abrirTelaCadastro() {
		AreaConhecimentoFormDialog dialog = new AreaConhecimentoFormDialog(
				"Gerenciar Área de Conhecimento",
				controller,
				this,
				() -> carregarTabela(),
				FormMode.CREATE,
				null);
		dialog.setVisible(true);
	}

	@Override
	protected Object[] extrairLinha(AreaConhecimento entidade) throws Exception {
		return new Object[] { entidade.getId(), entidade.getNome() };
	}

	@Override
	protected void exibirDetalhesDialog(Integer id) {
		try {
			AreaConhecimentoFormDialog dialog = new AreaConhecimentoFormDialog(
					"Gerenciar Área de Conhecimento",
					controller,
					this,
					() -> carregarTabela(),
					FormMode.VIEW,
					controller.buscarPorID(id));
			dialog.setVisible(true);
		} catch (Exception e) {
			Alerta.erro(this, e);
		}
	}

	@Override
	protected String getLabelTextEntidadeSelecionada(Integer id) {
		try {
			AreaConhecimento area = controller.buscarPorID(id);
			return "<html><html><div style='width:150px;'>ID: " + area.getId() + "<br>" +
					"Nome: " + area.getNome() + "</html></div>";
		} catch (Exception e) {
			return "Erro: " + e.getMessage();
		}
	}


}
