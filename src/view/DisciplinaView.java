package view;

import javax.swing.SwingUtilities;

import controller.DisciplinaController;
import model.dto.Disciplina;

public class DisciplinaView extends GenericCrudView<Disciplina, Integer, DisciplinaController> {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String titulo = "Disciplinas";
            String colunas[] = {"ID","Nome", "Dia da semana", "Hora inicial", "Qtd. de horas", "Curso"};
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
            entidade.getId(),
            entidade.getNome(),
            entidade.getDiaSemana(),
            entidade.getHoraInicial(),
            entidade.getQtdHoras(),
            "estrutura"
        };
    }

    @Override
    protected void exibirDetalhesDialog(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exibirDetalhesDialog'");
    }

    @Override
    protected String getLabelTextEntidadeSelecionada(Integer id) {
        try {
            Disciplina disciplina = controller.buscarPorID(id);
            System.out.println(disciplina.toString());
            return "<html>ID: " + disciplina.getId() + " <br> Nome: " + disciplina.getNome() + "</html>";
        } catch (Exception e) {
            return "Erro: " + e.getMessage();
        }
        
    }

}
