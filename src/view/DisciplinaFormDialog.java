package view;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.edu.fateczl.Fila;
import br.edu.fateczl.Lista;
import controller.DisciplinaController;
import model.dto.Curso;
import model.dto.Disciplina;
import util.Alerta;

public class DisciplinaFormDialog extends GenericFormDialog<Disciplina, Integer, DisciplinaController>{

    private JTextField txtID;
    private JTextField txtNome;
    private JTextField txtDiaSemana;
    private JTextField txtHoraInicial;
    private JTextField txtQtdHoras;
    private JComboBox<Curso> comboCurso;
    private JLabel lblID;

    public DisciplinaFormDialog(String titulo, DisciplinaController controller, JFrame parent, Runnable onSuccess,
            FormMode mode, Disciplina entidade) {
        super(titulo, controller, parent, onSuccess, mode, entidade);
    }

    @Override
    protected JPanel construirFormulario() {
       JPanel painel = new JPanel(new GridLayout(6, 2, 10, 10));

        lblID = new JLabel("Código");
        painel.add(lblID);
        txtID = new JTextField();
        txtID.setEnabled(false);
        painel.add(txtID);

        painel.add(new JLabel("Nome"));
        txtNome = new JTextField();
        painel.add(txtNome);

        painel.add(new JLabel("Dia da semana"));
        txtDiaSemana = new JTextField();
        painel.add(txtDiaSemana);

        painel.add(new JLabel("Hora inicial:"));
        txtHoraInicial = new JTextField();
        painel.add(txtHoraInicial);

        painel.add(new JLabel("Quantidade de horas"));
        txtQtdHoras = new JTextField();
        painel.add(txtQtdHoras);

        painel.add(new JLabel("Curso:"));
        comboCurso = new JComboBox<Curso>();
        try {
            Fila<Curso> cursos = controller.buscarTodosCursos();
            while(!cursos.isEmpty()){
                comboCurso.addItem(cursos.remove());
            }
        } catch (Exception e) {
           Alerta.erro(DisciplinaFormDialog.this, e);
        }
        painel.add(comboCurso);
        
        return painel;
    }

    @Override
    protected void habilitarCamposPorModo(FormMode currentFormMode) {
        Boolean enabled = (currentFormMode == FormMode.CREATE || currentFormMode == FormMode.EDIT);
        txtID.setVisible(currentFormMode != FormMode.CREATE);
        lblID.setVisible(currentFormMode != FormMode.CREATE);
        txtNome.setEnabled(enabled);
        txtDiaSemana.setEnabled(enabled);
        txtHoraInicial.setEnabled(enabled);
        txtQtdHoras.setEnabled(enabled);
        comboCurso.setEnabled(enabled);
    }

    @Override
    protected void preencherDadosCampos(FormMode currentFormMode, Disciplina entidade) {

        if ((currentFormMode == FormMode.VIEW)) {
            try {
                txtID.setText(Integer.toString(entidade.getId()));
                txtNome.setText(entidade.getNome());
                txtDiaSemana.setText(entidade.getDiaSemana());
                txtHoraInicial.setText(entidade.getHoraInicial());
                txtQtdHoras.setText(Double.toString(entidade.getQtdHoras()));
                comboCurso.setSelectedItem(controller.buscarCursoPorId(entidade.getIdCurso()));
                } catch (Exception e) {
                    Alerta.erro(DisciplinaFormDialog.this, e);
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
            dadosInput.addLast(txtDiaSemana.getText());
            dadosInput.addLast(txtHoraInicial.getText());
            dadosInput.addLast(txtQtdHoras.getText());
            Curso cursoSelecionado = (Curso) comboCurso.getSelectedItem();
            dadosInput.addLast(Integer.toString(cursoSelecionado.getId()));
        } catch (Exception e) {
            Alerta.erro(DisciplinaFormDialog.this, e);
        }

        return dadosInput;
    }

    @Override
    protected void limparFormulario() {
        txtNome.setText("");
        txtDiaSemana.setText("");
        txtHoraInicial.setText("");
        txtQtdHoras.setText("");
        comboCurso.setSelectedItem(new Curso(-1, "Selecione", 0));
    }

}
