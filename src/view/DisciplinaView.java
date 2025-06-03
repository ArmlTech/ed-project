package view;

import javax.swing.SwingUtilities;

import controller.DisciplinaController;
import model.dto.Disciplina;
import util.Alerta;
import view.GenericFormDialog.FormMode;

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
       DisciplinaFormDialog dialog = new DisciplinaFormDialog(
            "Gerenciar disciplinas", 
            controller, 
            this, 
            ()-> carregarTabela(), 
            FormMode.CREATE, 
            null
        );
        dialog.setVisible(true);
    }

    @Override
    protected Object[] extrairLinha(Disciplina entidade) throws Exception {
        return new Object[] {
            entidade.getId(),
            entidade.getNome(),
            entidade.getDiaSemana(),
            entidade.getHoraInicial(),
            entidade.getQtdHoras(),
            controller.buscarCursoPorId(entidade.getIdCurso()).getNome()
        };
    }

    @Override
    protected void exibirDetalhesDialog(Integer id) {
        Disciplina entidade;
        try {
            entidade = controller.buscarPorID(id);
            DisciplinaFormDialog dialog = new DisciplinaFormDialog(
                "Gerenciar disciplinas", 
                controller, 
                this, 
                ()-> carregarTabela(), 
                FormMode.VIEW, 
                entidade
            );
            dialog.setVisible(true);

        } catch (Exception e) {
            Alerta.erro(this, e);
        }
        
    }

    @Override
    protected String getLabelTextEntidadeSelecionada(Integer id) {
        try {
            Disciplina disciplina = controller.buscarPorID(id);
            return "<html>ID: " + disciplina.getId() + " <br> Nome: " + disciplina.getNome() + "</html>";
        } catch (Exception e) {
            return "Erro: " + e.getMessage();
        }
        
    }

}
