package view;

import javax.swing.JFrame;

import controller.InscricaoController;
import model.dto.Candidatura;
import util.Alerta;
import view.GenericFormDialog.FormMode;

public class InscricaoView extends GenericCrudView<Candidatura, Integer, InscricaoController> {

    private static final long serialVersionUID = 1L;

    private final static String titulo = "Gerenciar Inscrições";
    private final static String[] colunas = {
        "ID", "Professor", "Processo", "Disciplina"
    };
    private final static InscricaoController controller = new InscricaoController();

    public InscricaoView(JFrame parent) {
        super(parent, titulo, colunas, controller);
    }

    @Override
    protected Object[] extrairLinha(Candidatura entidade) throws Exception {
        return new Object[] {
            entidade.getId(),
            controller.buscarProfessorPorId(entidade.getIdProfessor()).getNome(),
            entidade.getIdProcesso(),
            controller.buscarNomeDisciplinaPorProcessoID(entidade.getIdProcesso())
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
            return "<html><html><div style='width:150px;'>Inscrição ID: " + candidatura.getId() + 
                "<br>Professor: " + controller.buscarProfessorPorId(candidatura.getIdProfessor()).getNome() +
                "<br>Disciplina: " + controller.buscarNomeDisciplinaPorProcessoID(candidatura.getIdProcesso()) +
                "<br>Processo: " + candidatura.getIdProcesso()
            + "</div></html>";
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
