package view;

import javax.swing.JFrame;

import br.edu.fateczl.Lista;
import controller.ProcessoController;
import model.dto.Processo;
import util.Alerta;
import view.GenericFormDialog.FormMode;

public class ProcessoView extends GenericCrudView<Processo, Integer, ProcessoController> {

    private static final long serialVersionUID = 1L;

    private final static String titulo = "Gerenciar Processos";
    private final static String[] colunas = {
        "ID", "Disciplina"
    };
    private final static ProcessoController controller = new ProcessoController();

    public ProcessoView(JFrame parent) {
        super(parent, titulo, colunas, controller);
    }

    @Override
    protected Object[] extrairLinha(Processo entidade) throws Exception {
        return new Object[]{
            entidade.getId(),
            controller.buscarNomeDisciplina(entidade.getIdDisciplina())
        };
    }

    @Override
    protected void exibirDetalhesDialog(Integer id) {
        try {
            ProcessoFormDialog dialog = new ProcessoFormDialog(
                "Gerenciar Processo",
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
            Processo processo = controller.buscarPorID(id);
            return "<html><html><div style='width:150px;'>Processo ID: " + processo.getId() + "<br>" +
                   "ID da Disciplina: " + processo.getIdDisciplina() + "</div></html>";
        } catch (Exception e) {
            return "Erro: " + e.getMessage();
        }
    }

    @Override
    protected void abrirTelaCadastro() {
        ProcessoFormDialog dialog = new ProcessoFormDialog(
            "Gerenciar Processo",
            controller,
            this,
            () -> carregarTabela(),
            FormMode.CREATE,
            null    
        );
        dialog.setVisible(true);
    }

    @Override
    protected void ocultarColunaID(int id) {
        //Nesse caso, a coluna ID é necessária para o processo, então não a ocultamos
    }

    @Override
    protected void carregarTabela(){
        tableModel.setRowCount(0);                  // Remove todas as linhas atuais
        try {
            Lista<Processo>[] listasProcessos = controller.buscarTabelaHash();
            for (Lista<Processo> lista : listasProcessos) {
                for (int i = 0, length = lista.size(); i < length; i++) {
                    Processo processo = lista.get(i);
                    tableModel.addRow(extrairLinha(processo));
                }
            }
        } catch (Exception e) {
            Alerta.erro(e);                        // Mostra mensagem de erro em caso de exceção
        }
        ocultarColunaID(0);
    }

    
}
