package view;

import java.io.IOException;

import javax.swing.SwingUtilities;

import controller.InscricaoController;
import model.dto.Candidatura;
import util.Alerta;
import view.GenericFormDialog.FormMode;

public class InscricaoView extends GenericCrudView<Candidatura, Integer, InscricaoController> {

    public InscricaoView(String titulo, String[] colunas, InscricaoController controller) {
        super(titulo, colunas, controller);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            
            try {
                InscricaoController controller = new InscricaoController();
                String titulo = "Inscrições";
                String[] colunas = {"ID","Inscrito", "Código do processo", "Disciplina"};
                InscricaoView tela = new InscricaoView(titulo, colunas, controller);
                tela.setVisible(true);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        });
    }

    @Override
    protected Object[] extrairLinha(Candidatura entidade) throws Exception {
        return new Object[] {
            entidade.getId(),
            controller.buscarProfessorPorId(entidade.getIdProfessor()).getNome(),
            entidade.getIdProcesso(),
            controller.buscarDisciplinaProcesso(entidade.getIdProcesso())
        };
    }

    @Override
    protected void exibirDetalhesDialog(Integer id) {
        try {
            InscricaoFormDialog dialog = new InscricaoFormDialog(
                "Detalhes da Inscrição", 
                controller, 
                this, 
                () -> carregarTabela(), 
                FormMode.VIEW, 
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
            Candidatura candidatura = controller.buscarPorID(id);
            return "<html>Inscrição ID: " + candidatura.getId() + 
                "<br>Professor: " + controller.buscarProfessorPorId(candidatura.getIdProfessor()).getNome() +
                "<br>Disciplina: " + controller.buscarDisciplinaProcesso(candidatura.getIdProcesso()) +
                "<br>Processo: " + candidatura.getIdProcesso()
            + "</html>";
        } catch (Exception e) {
            return "Erro: " + e.getMessage();
        }
    }

    @Override
    protected void abrirTelaCadastro() {
        InscricaoFormDialog dialog = 
            new InscricaoFormDialog(
                "Gerenciar Inscrição", 
                controller, 
                this, 
                () -> carregarTabela(), 
                FormMode.CREATE, 
                null);
        dialog.setVisible(true);
    }

}
