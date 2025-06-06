// Implementação concreta para Professores, usando a estrutura genérica
package view;


import javax.swing.JFrame;
import controller.ProfessorController;
import model.dto.Professor;
import util.Alerta;
import view.GenericFormDialog.FormMode;

//Define que essa classe herda a classe genérica, e dá o tipo de entidiade Professor
// e o tipo do controlador CrudController<Professor>
public class ProfessorView extends GenericCrudView<Professor, Integer, ProfessorController> {
    private static final long serialVersionUID = 1L;

    // Construtor passa título, colunas e controlador para a superclasse (classe mãe)
    public ProfessorView(JFrame parent, String titulo, String[] colunas, ProfessorController controller) {
        super(parent, titulo, colunas, controller); //constrói a classe mãe a partir do seu construtor
    }
    
    // Converte cada Professor em um array de objetos para exibição na tabela
    @Override
    protected Object[] extrairLinha(Professor entidade) throws Exception {
        return new Object[] {
            entidade.getId(),
            entidade.getCpf(),
            entidade.getNome(),
            entidade.getQtdPontos(),
            controller.buscarAreaPorId(entidade.getAreaID()).getNome()
        };
    }
    
    // Define como abrir o diálogo de cadastro específico de professor
    @Override
    protected void abrirTelaCadastro() {
        ProfessorFormDialog dialog = 
            new ProfessorFormDialog(
                "Gerenciar professor", 
                controller, 
                this, 
                () -> carregarTabela(), 
                FormMode.CREATE, 
                null);
        dialog.setVisible(true);                   // Exibe o diálogo
    }

    @Override
    protected void exibirDetalhesDialog(Integer id) {
        try {
            ProfessorFormDialog dialog = new ProfessorFormDialog(
                "Gerenciar professor", 
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
            Professor professor = controller.buscarPorID(id);
            return "<html>CPF: " + professor.getCpf() + "<br>Nome: " + professor.getNome() + "</html>";
        } catch (Exception e) {
            return "Erro: " + e.getMessage();
        }
    }
}
