package view;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.edu.fateczl.Lista;
import controller.AreaConhecimentoController;
import model.dto.AreaConhecimento;
import util.Alerta;

public class AreaConhecimentoFormDialog extends GenericFormDialog<AreaConhecimento, Integer, AreaConhecimentoController> {

    private JTextField txtId;
    private JTextField txtNome;

    public AreaConhecimentoFormDialog(String titulo, AreaConhecimentoController controller, JFrame parent, Runnable onSuccess, FormMode mode, AreaConhecimento entidade) {
        super(titulo, controller, parent, onSuccess, mode, entidade);
    }

    @Override
    protected JPanel construirFormulario() {
        JPanel painel = new JPanel(new GridLayout(2, 2, 10, 10));

        painel.add(new JLabel("ID: "));
        txtId = new JTextField();
        painel.add(txtId);
        
        painel.add(new JLabel("Nome: "));
        txtNome = new JTextField();
        painel.add(txtNome);

        return painel;
    }

    @Override
    protected void habilitarCamposPorModo(FormMode currentFormMode) {
        boolean enabled = currentFormMode == FormMode.CREATE || currentFormMode == FormMode.EDIT;
        txtNome.setEnabled(enabled);
        txtId.setEnabled(false);
    }

    @Override
    protected void preencherDadosCampos(FormMode currentFormMode, AreaConhecimento entidade) {
        if ((currentFormMode == FormMode.VIEW)) {
            txtId.setText(Integer.toString(entidade.getId()));
            txtNome.setText(entidade.getNome());
        } else if (currentFormMode == FormMode.CREATE){
            limparFormulario();
        }
    }

    @Override
    protected Lista<String> getDadosInput() {
        Lista<String> dadosInput = new Lista<>();
        try {
            dadosInput.addLast(txtNome.getText());
        } catch (Exception e) {
            Alerta.erro(AreaConhecimentoFormDialog.this, e);
        }
        return dadosInput;
    }

    @Override
    protected void limparFormulario() {
        txtId.setText("");
        txtNome.setText("");
    }

}
