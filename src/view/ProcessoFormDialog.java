package view;

import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.edu.fateczl.Lista;
import controller.ProcessoController;
import model.dto.Disciplina;
import model.dto.Processo;
import util.Alerta;

public class ProcessoFormDialog extends GenericFormDialog<Processo, Integer, ProcessoController> {

    private static final long serialVersionUID = 1L;

    JTextField txtID;
    JComboBox<String> comboStatus;
    JComboBox<Disciplina> comboDisciplina;

    public ProcessoFormDialog(String titulo, ProcessoController controller, JFrame parent, Runnable onSuccess, FormMode mode, Processo entidade) {
        super(titulo, controller, parent, onSuccess, mode, entidade);
        this.controller = controller;
    }

    @Override
    protected JPanel construirFormulario() {
        JPanel painel = new JPanel(new GridLayout(3, 2, 10, 10));

        painel.add(new JLabel("ID: "));
        txtID = new JTextField();
        txtID.setEnabled(false);
        painel.add(txtID);
        
        painel.add(new JLabel("Status: "));
        comboStatus = new JComboBox<>(new String[]{"Ativo", "Inativo"});
        painel.add(comboStatus);

        painel.add(new JLabel("Disciplina: "));
        comboDisciplina = new JComboBox<>();
        comboDisciplina.addItem(new Disciplina(-1, "Selecione", "", "", null, null));
        try {
            Lista<Disciplina> disciplinas = controller.buscarTodasDisciplinas();
            for (int i = 0, length = disciplinas.size(); i < length; i++) {
                comboDisciplina.addItem(disciplinas.get(i));
            }
        } catch (Exception e) {
            Alerta.erro(ProcessoFormDialog.this, e);
        }
        painel.add(comboDisciplina);

        return painel;
    }

    @Override
    protected void habilitarCamposPorModo(FormMode currentFormMode) {
        Boolean enabled = (currentFormMode == FormMode.CREATE || currentFormMode == FormMode.EDIT);
        txtID.setEnabled(false);
        comboStatus.setEnabled(enabled);
        comboDisciplina.setEnabled(enabled);
    }

    @Override
    protected void preencherDadosCampos(FormMode currentFormMode, Processo entidade) {
        
        if(currentFormMode == FormMode.VIEW) {
            try {
                txtID.setText(entidade.getId().toString());
                comboStatus.setSelectedItem(entidade.isAberto() ? "Ativo" : "Inativo");
                comboDisciplina.setSelectedItem(controller.buscarDisciplinaPorId(entidade.getIdDisciplina()));
            } catch (Exception e) {
                Alerta.erro(ProcessoFormDialog.this, e);
            }
        } else if (currentFormMode == FormMode.CREATE) {
           limparFormulario();
        }
    }

    @Override
    protected Lista<String> getDadosInput() {
        Lista<String> dadosInput = new Lista<>();
        try {
            dadosInput.addLast(comboStatus.getSelectedItem().toString());
            Disciplina disciplinaSelecionada = (Disciplina) comboDisciplina.getSelectedItem();
            dadosInput.addLast(Integer.toString(disciplinaSelecionada.getId()));
        } catch (Exception e) {
            Alerta.erro(ProcessoFormDialog.this, e);
        }
        return dadosInput;
    }

    @Override
    protected void limparFormulario() {
        txtID.setText("");
        comboStatus.setSelectedIndex(0);
        comboDisciplina.setSelectedIndex(0);
    }



}
