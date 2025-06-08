package view;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.edu.fateczl.Fila;
import br.edu.fateczl.Lista;
import controller.CursoController;
import model.dto.AreaConhecimento;
import model.dto.Curso;
import util.Alerta;

public class CursoFormDialog extends GenericFormDialog<Curso, Integer, CursoController> {

    private JTextField txtId;
    private JTextField txtNome;
    private JComboBox<AreaConhecimento> comboArea;

    public CursoFormDialog(String titulo, CursoController controller, JFrame parent, Runnable onSuccess, FormMode mode,
            Curso entidade) {
        super(titulo, controller, parent, onSuccess, mode, entidade);
    }

    @Override
    protected JPanel construirFormulario() {
        JPanel painel = new JPanel(new GridLayout(3, 2, 10, 10));

        painel.add(new JLabel("ID: "));
        txtId = new JTextField();
        painel.add(txtId);

        painel.add(new JLabel("Nome: "));
        txtNome = new JTextField();
        painel.add(txtNome);

        painel.add(new JLabel("Área:"));
        comboArea = new JComboBox<>();
        comboArea.addItem(new AreaConhecimento(-1, "Selecione"));
        try {
            Fila<AreaConhecimento> areas = controller.buscarTodosArea();
            while (!areas.isEmpty()) {
                comboArea.addItem(areas.remove());
                
            }
        } catch (Exception e) {
            Alerta.erro(CursoFormDialog.this, e);
        }
        painel.add(comboArea);

        return painel;
    }

    @Override
    protected void habilitarCamposPorModo(FormMode currentFormMode) {
        boolean enabled = (currentFormMode == FormMode.CREATE || currentFormMode == FormMode.EDIT);
        txtId.setEnabled(false);
        txtNome.setEnabled(enabled);
        comboArea.setEnabled(enabled);
    }

    @Override
    protected void preencherDadosCampos(FormMode currentFormMode, Curso entidade) {
        if (currentFormMode == FormMode.VIEW) {
            try {
                txtId.setText(entidade.getId().toString());
                txtNome.setText(entidade.getNome());
                comboArea.setSelectedItem(controller.buscarAreaPorId(entidade.getIdAreaConhecimento()));
            } catch (Exception e) {
                Alerta.erro(this, e);
            }
        } else if (currentFormMode == FormMode.CREATE){
            limparFormulario();
        }
    }

    @Override
    protected Lista<String> getDadosInput() {
        Lista<String> dadosInput = new Lista<>();
        try {
            dadosInput.addLast(txtNome.getText());
            AreaConhecimento areaSelecionada = (AreaConhecimento) comboArea.getSelectedItem();
            dadosInput.addLast(Integer.toString(areaSelecionada.getId()));
        } catch (Exception e) {
            Alerta.erro(CursoFormDialog.this, e);
        }

        return dadosInput;
    }

    @Override
    protected void limparFormulario() {
        txtId.setText("");
        txtNome.setText("");
        comboArea.setSelectedItem(new AreaConhecimento(-1, "Selecione"));
    }

}
