package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import br.edu.fateczl.Lista;
import controller.CursoController;
import model.dto.Curso;

public class CursoFormDialog extends GenericFormDialog<Curso, Integer, CursoController> {

    public CursoFormDialog(String titulo, CursoController controller, JFrame parent, Runnable onSuccess, FormMode mode,
            Curso entidade) {
        super(titulo, controller, parent, onSuccess, mode, entidade);
        //TODO Auto-generated constructor stub
    }

    @Override
    protected JPanel construirFormulario() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'construirFormulario'");
    }

    @Override
    protected void habilitarCamposPorModo(FormMode currentFormMode) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'habilitarCamposPorModo'");
    }

    @Override
    protected void preencherDadosCampos(FormMode currentFormMode, Curso entidade) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'preencherDadosCampos'");
    }

    @Override
    protected Lista<String> getDadosInput() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDadosInput'");
    }

    @Override
    protected void limparFormulario() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'limparFormulario'");
    }

}
