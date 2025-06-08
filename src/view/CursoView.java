package view;

import javax.swing.JFrame;

import controller.CursoController;
import model.dto.Curso;
import util.Alerta;
import view.GenericFormDialog.FormMode;

public class CursoView extends GenericCrudView<Curso, Integer, CursoController> {

    private static final long serialVersionUID = 1L;

    private final static String titulo = "Gerenciar Cursos";
    private final static String[] colunas = { "ID", "Nome", "Área de Conhecimento"};
    private final static CursoController controller = new CursoController();

    public CursoView(JFrame parent){
        super(parent, titulo, colunas, controller);
    }

    @Override
    protected Object[] extrairLinha(Curso entidade) throws Exception {
        return new Object[] {
            entidade.getId(),
            entidade.getNome(),
            controller.buscarAreaPorId(entidade.getIdAreaConhecimento()).getNome()
        };
    }

    @Override
    protected void exibirDetalhesDialog(Integer id) {
        try {
            CursoFormDialog dialog = new CursoFormDialog(
                "Gerenciar curso", 
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
            Curso curso = controller.buscarPorID(id);
            return  "<html><div style='width:150px;'>"  + curso.getId() + 
                    "<br>Nome: " + curso.getNome() + 
                    "<br>Área: " + 
                    controller.buscarAreaPorId(curso.getIdAreaConhecimento()).getNome() + "</div></html>";
        } catch (Exception e) {
            return "Erro: " + e.getMessage();
        }
    }

    @Override
    protected void abrirTelaCadastro() {
        CursoFormDialog dialog = 
            new CursoFormDialog(
                "Gerenciar curso", 
                controller, 
                this, 
                () -> carregarTabela(), 
                FormMode.CREATE, 
                null);
        dialog.setVisible(true);
    }

}
