package view;

import javax.swing.SwingUtilities;

import br.edu.fateczl.pilha.Pilha;
import controller.ProfessorController;
import model.dto.Professor;


public class ProfessorView extends EntityMainView<Professor, ProfessorController> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProfessorView(String titulo, String[] colunas, ProfessorController controller) {
		super(titulo, colunas, controller);
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			ProfessorController controller = new ProfessorController();
			String titulo = "Professores";
			String[] colunas = {"CPF", "Nome", "Pontos", "Área do conhecimento"};
			ProfessorView janela = new ProfessorView(titulo, colunas, controller);
			janela.setVisible(true);
		});
		
	}

	@Override
	protected Pilha<Professor> buscarEntidades() throws Exception {
		return controller.listar();
	}

	@Override
	protected Object[] extrairLinha(Professor entidade) throws Exception {
		Object[] dadosLinha = {
				entidade.getCpf(),
				entidade.getNome(),
				entidade.getQtdPontos(),
				controller.buscarAreaPorId(entidade.getAreaID()).getNome()
		};
		return dadosLinha;
	}

	@Override
	protected void abrirTelaCadastro() {
		ProfessorCadastroDialog dialogCadastro = new ProfessorCadastroDialog(ProfessorView.this, () -> carregarTabela());
		dialogCadastro.setVisible(true);
	}
	

}
