package view;

import controller.*;w

import javax.swing.JFrame;

import controller.CursoController;
import model.dto.Curso;
import util.Alerta;
import view.GenericFormDialog.FormMode;

public class CursoView extends GenericCrudView<Curso, Integer, CursoController> {

	public CursoView(JFrame parent, String titulo, String[] colunas, CursoController controller) {
		super(parent, titulo, colunas, controller);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object[] extrairLinha(Curso entidade) throws Exception {

		return new Object[] {

				entidade.getId(), entidade.getNome(), entidade.getIdAreaConhecimento(),
				controller.buscarPorID(entidade.getIdAreaConhecimento()).getNome()

		};

	}

	@Override
	protected void exibirDetalhesDialog(Integer id) {

		try {

			CursoFormDialog dialog = new CursoFormDialog(

					"Gerenciar cursos", controller, this, () -> carregarTabela(), FormMode.VIEW,
					controller.buscarPorID(id)

			);

			dialog.setVisible(true);

		} catch (Exception e) {

			Alerta.erro(this, e);

		}

	}

	@Override
	protected String getLabelTextEntidadeSelecionada(Integer id) {

		try {

			Curso curso = controller.buscarPorID(id);
			return "<html>ID: " + curso.getId() + "<br>Nome: " + curso.getNome() + "</html>";

		} catch (Exception e) {

			return "Erro: " + e.getMessage();

		}

	}

	@Override
	protected void abrirTelaCadastro() {
		
		CursoFormDialog dialog = 
				new CursoFormDialog(
						
						"Gerenciar Curso",
						controller,
						this,
						()-> carregarTabela(),
						FormMode.CREATE,
						null
						
						);
		
		dialog.setVisible(true);
		
	}

}
