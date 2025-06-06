package view;

import javax.swing.JFrame;

import controller.CursoController;
import model.dto.Curso;

public class CursoView extends GenericCrudView<Curso, Integer, CursoController> {

    public CursoView(JFrame parent, String titulo, String[] colunas, CursoController controller) {
        super(parent, titulo, colunas, controller);
        //TODO Auto-generated constructor stub
    }

    @Override
    protected Object[] extrairLinha(Curso entidade) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'extrairLinha'");
    }

    @Override
    protected void exibirDetalhesDialog(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exibirDetalhesDialog'");
    }

    @Override
    protected String getLabelTextEntidadeSelecionada(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLabelTextEntidadeSelecionada'");
    }

    @Override
    protected void abrirTelaCadastro() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'abrirTelaCadastro'");
    }

}
