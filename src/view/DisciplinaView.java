package view;

import javax.swing.SwingUtilities;

import controller.DisciplinaController;
import model.dto.Disciplina;

public class DisciplinaView extends GenericCrudView<Disciplina, Integer, DisciplinaController> {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String titulo = "Disciplinas";
            String colunas[] = {"Nome", "Dia da semana", "Hora inicial", "Qtd. de horas", "Curso"};
            DisciplinaController controller = new DisciplinaController();
            DisciplinaView tela = new DisciplinaView(titulo, colunas, controller);
            tela.setVisible(true);
        });
    }

    public DisciplinaView(String titulo, String[] colunas, DisciplinaController controller) {
        super(titulo, colunas, controller);
    }

    @Override
    protected void abrirTelaCadastro() {
        // TODO Auto-generated method stub
    }

    @Override
    protected Object[] extrairLinha(Disciplina entidade) throws Exception {
        return new Object[] {
            entidade.getNome(),
            entidade.getDiaSemana(),
            entidade.getHoraInicial(),
            entidade.getQtdHoras(),
            "estrutura"
        };
    }

    @Override
    protected void exibirDetalhesDialog(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exibirDetalhesDialog'");
    }

    @Override
    protected String getLabelTextEntidadeSelecionada(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLabelTextEntidadeSelecionada'");
    }

}
