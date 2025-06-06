package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import br.edu.fateczl.Lista;
import controller.CursoController;
import model.dto.Curso;
import util.Alerta;
import view.GenericFormDialog.FormMode;

public class CursoFormDialog extends GenericFormDialog<Curso, Integer, CursoController> {

	// private JTextField txtId;
	private JTextField txtNome;

	public CursoFormDialog(String titulo, CursoController controller, JFrame parent, Runnable onSuccess, FormMode mode,
			Curso entidade) {
		super(titulo, controller, parent, onSuccess, mode, entidade);
		this.controller = controller;
	}

	@Override
	protected JPanel construirFormulario() {

		JPanel painel = new JPanel(new GridLayout(4, 2, 10, 10));

		painel.add(new JLabel("Nome: "));

		return painel;
	}

	@Override
	protected void habilitarCamposPorModo(FormMode currentFormMode) {

		Boolean enabled = (currentFormMode == FormMode.CREATE || currentFormMode == FormMode.EDIT);
		txtNome.setEnabled(currentFormMode == FormMode.CREATE);

	}

	@Override
	protected void preencherDadosCampos(FormMode currentFormMode, Curso entidade) {

		switch (currentFormMode) {

		case CREATE:
			limparFormulario();
		case EDIT:
			break;
		case VIEW:
			Curso curso;
			try {

				curso = controller.buscarPorID(entidade.getId());
				txtNome.setText(curso.getNome());

			} catch (Exception e) {

				Alerta.erro(CursoFormDialog.this, e);

			}

		default:
			break;

		}

	}

	@Override
	protected Lista<String> getDadosInput() {

		Lista<String> dadosInput = new Lista<>();
		try {
			dadosInput.addLast(txtNome.getText());
		} catch (Exception e) {

			Alerta.erro(CursoFormDialog.this, e);

		}

		return dadosInput;

	}

	@Override
	protected void limparFormulario() {

		txtNome.setText("");

	}

}
