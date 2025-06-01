package view;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.edu.fateczl.Lista;
import br.edu.fateczl.pilha.Pilha;
import controller.ProfessorController;
import model.dto.AreaConhecimento;
import model.dto.Professor;
import util.Alerta;

public class ProfessorFormDialog extends GenericFormDialog<Professor, String, ProfessorController> {

    private JTextField txtCPF;
    private JTextField txtNome;
    private JTextField txtQtdPontos;
    private JComboBox<AreaConhecimento> comboArea;

    public ProfessorFormDialog(String titulo, ProfessorController controller, JFrame parent, Runnable onSuccess, FormMode mode, Professor entidade){
        super(titulo, controller, parent, onSuccess, mode, entidade);
        this.controller = controller;
    }

    @Override
    protected JPanel construirFormulario() {
       JPanel painel = new JPanel(new GridLayout(4, 2, 10, 10));

       painel.add(new JLabel("CPF: "));
       txtCPF = new JTextField();
       painel.add(txtCPF);

       painel.add(new JLabel("Nome: "));
       txtNome = new JTextField();
       painel.add(txtNome);

       painel.add(new JLabel("Qtd. de Pontos: "));
       txtQtdPontos = new JTextField();
       painel.add(txtQtdPontos);

       painel.add(new JLabel("Área:"));
       comboArea = new JComboBox<>();
        comboArea.addItem(new AreaConhecimento(-1, "Selecione"));
		try {
			Pilha<AreaConhecimento> areas = controller.buscarTodosArea();
			while(!areas.isEmpty()) {
				comboArea.addItem(areas.pop());
			}
		} catch(Exception e) {
			Alerta.erro(ProfessorFormDialog.this, e);
		}
       painel.add(comboArea);

       return painel;
    }

    @Override
    protected Lista<String> getDadosInput() {
        Lista<String> dadosInput = new Lista<>();
        try {
            dadosInput.addLast(txtCPF.getText());
            dadosInput.addLast(txtNome.getText());
            dadosInput.addLast(txtQtdPontos.getText());
            AreaConhecimento areaSelecionada = (AreaConhecimento) comboArea.getSelectedItem();
            dadosInput.addLast(Integer.toString(areaSelecionada.getID()));
        } catch (Exception e) {
            Alerta.erro(ProfessorFormDialog.this, e);
        }

        return dadosInput;
    }

    @Override
    protected void limparFormulario() {
        txtCPF.setText("");
        txtNome.setText("");
        txtQtdPontos.setText("");
        comboArea.setSelectedItem(new AreaConhecimento(-1, "Selecione"));
    }

    @Override
    protected void habilitarCamposPorModo(FormMode currentFormMode) {
        Boolean enabled = (currentFormMode == FormMode.CREATE || currentFormMode == FormMode.EDIT);
        txtCPF.setEnabled(currentFormMode == FormMode.CREATE);
        txtNome.setEnabled(enabled);
        txtQtdPontos.setEnabled(enabled);
        comboArea.setEnabled(enabled);
    }

    @Override
    protected void preencherDadosCampos(FormMode currentFormMode, Professor entidade) {
        
        switch(currentFormMode){
            case CREATE:
                limparFormulario();
            case EDIT:
            
                break;
            case VIEW:
                Professor professor;
                try {
                    professor = controller.buscarPorID(entidade.getCpf());
                    txtCPF.setText(professor.getCpf());
                    txtNome.setText(professor.getNome());
                    txtQtdPontos.setText(Float.toString(professor.getQtdPontos()));
                    comboArea.setSelectedItem(controller.buscarAreaPorId(professor.getAreaID()));
                } catch (Exception e) {
                    Alerta.erro(ProfessorFormDialog.this, e);
                }
            default:
                break;
            
        }

    }

}
