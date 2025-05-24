// Implementação concreta para Professores, usando a estrutura genérica EntityMainView
package view;

import javax.swing.SwingUtilities;
import br.edu.fateczl.pilha.Pilha;
import controller.ProfessorController;
import model.dto.Professor;

//Define que essa classe herda a classe genérica, e dá o tipo de entidiade Professor
// e o tipo do controlador CrudController<Professor>
public class ProfessorView extends EntityMainView<Professor, String, ProfessorController> {
    private static final long serialVersionUID = 1L;

    // Construtor passa título, colunas e controlador para a superclasse (classe mãe)
    public ProfessorView(String titulo, String[] colunas, ProfessorController controller) {
        super(titulo, colunas, controller); //constrói a classe mãe a partir do seu construtor
    }
    
    // Ponto de entrada da aplicação
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProfessorController controller = new ProfessorController(); // Cria o controlador
            String titulo = "Professores";
            String[] colunas = {"CPF", "Nome", "Pontos", "Área do conhecimento"};
            ProfessorView janela = new ProfessorView(titulo, colunas, controller); // Cria a tela
            janela.setVisible(true);    // Exibe a janela
        });
    }

    // Implementa a recuperação dos dados de Professores
    @Override
    protected Pilha<Professor> buscarEntidades() throws Exception {
        return controller.buscarTodos();               // Chama listar() do controlador
    }

    // Converte cada Professor em um array de objetos para exibição na tabela
    @Override
    protected Object[] extrairLinha(Professor entidade) throws Exception {
        return new Object[] {
            entidade.getCpf(),
            entidade.getNome(),
            entidade.getQtdPontos(),
            controller.buscarAreaPorId(entidade.getAreaID()).getNome()
        };
    }

    // Define como abrir o diálogo de cadastro específico de professor
    @Override
    protected void abrirTelaCadastro() {
        ProfessorCadastroDialog dialog = 
            new ProfessorCadastroDialog(this, () -> carregarTabela());
        dialog.setVisible(true);                   // Exibe o diálogo
    }
}
