package view;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.edu.fateczl.Fila;
import br.edu.fateczl.Lista;
import controller.InscricaoController;
import model.dto.Candidatura;
import model.dto.Processo;
import model.dto.ProcessoDisplay;
import model.dto.Professor;
import util.Alerta;

public class InscricaoFormDialog extends GenericFormDialog<Candidatura, Integer, InscricaoController> {
    private JComboBox<Professor> comboProfessor;
    private JComboBox<ProcessoDisplay> comboProcesso;
    private JTextField txtID;
    private JLabel lblID;


    public InscricaoFormDialog(String titulo, InscricaoController controller, JFrame parent, Runnable onSuccess,
            FormMode mode, Candidatura entidade) {
        super(titulo, controller, parent, onSuccess, mode, entidade);
    }

    @Override
    protected JPanel construirFormulario() {
        JPanel painel = new JPanel(new GridLayout(3, 2, 10, 10));

        lblID = new JLabel("Código de inscrição:");
        painel.add(lblID);
        txtID = new JTextField();
        txtID.setEnabled(false);
        painel.add(txtID);

        painel.add(new JLabel("Processo:"));      
        comboProcesso = new JComboBox<>();
        comboProcesso.addItem(new ProcessoDisplay(-1, "Selecione"));
        try {
            Fila<ProcessoDisplay> processos = controller.buscarTodosProcessos();
            while(!processos.isEmpty()) {
                comboProcesso.addItem(processos.remove());
            }
        } catch(Exception e) {
            Alerta.erro(this, e);
        }
        painel.add(comboProcesso);

        
        painel.add(new JLabel("Professor:"));
        comboProfessor = new JComboBox<>();
        comboProfessor.addItem(new Professor(-1, "000.000.000-00", "Selecione", (float) 0, 0)); 
		try {
			Fila<Professor> professores = controller.buscarTodosProfessores();
			while(!professores.isEmpty()) {
				comboProfessor.addItem(professores.remove());
			}
		} catch(Exception e) {
			Alerta.erro(this, e);
		}
       painel.add(comboProfessor);

        return painel;
    }

    @Override
    protected void habilitarCamposPorModo(FormMode currentFormMode) {
        Boolean enabled = currentFormMode == FormMode.EDIT || currentFormMode == FormMode.CREATE;
        lblID.setVisible(currentFormMode == FormMode.VIEW || currentFormMode == FormMode.EDIT);
        txtID.setVisible(currentFormMode == FormMode.VIEW || currentFormMode == FormMode.EDIT);
        comboProfessor.setEnabled(currentFormMode == FormMode.CREATE);
        comboProcesso.setEnabled(enabled);
    }

    @Override
    protected void preencherDadosCampos(FormMode currentFormMode, Candidatura entidade) {

        if ((currentFormMode == FormMode.VIEW)) {
            try {
                txtID.setText(Integer.toString(entidade.getId()));
                comboProcesso.setSelectedItem(controller.buscarProcessoDisplayPorId(entidade.getIdProcesso()));
                comboProfessor.setSelectedItem(controller.buscarProfessorPorId(entidade.getIdProfessor()));
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
            dadosInput.addLast(Integer.toString(((ProcessoDisplay) comboProcesso.getSelectedItem()).getId()));
            dadosInput.addLast(Integer.toString(((Professor) comboProfessor.getSelectedItem()).getId()));
        } catch (Exception e) {
            Alerta.erro(this, e);
        }
        return dadosInput;
    }

    @Override
    protected void limparFormulario() {
        txtID.setText("");
        comboProfessor.setSelectedItem(new Professor(-1, "000.000.000-00", "Selecione", (float) 0, 0));
        comboProcesso.setSelectedItem(new Processo(-1, 0));
    }

}
