package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.edu.fateczl.Lista;
import controller.IGenericCrudController;
import util.Alerta;

public abstract class GenericFormDialog<T, ID, C extends IGenericCrudController<T, ID>> extends JDialog {

    protected enum FormMode{
        CREATE,
        VIEW,
        EDIT
    }

    protected C controller;
    protected T entidade;
    protected FormMode currentFormMode;

    //campos
	protected JButton btnSalvar = new JButton("Salvar");
	protected JButton btnLimpar = new JButton("Limpar");
	protected JButton btnCancelar = new JButton("Cancelar");
	protected JButton btnDeletar = new JButton("Deletar");
	protected JButton btnHabilitarEdicao = new JButton("Habilitar edição");
    
	public GenericFormDialog(String titulo, C controller, JFrame parent, Runnable onSuccess, FormMode mode, T entidade) {
        //travar a tela de cadastro em relação ao parent até que seja fechada
        super(parent, titulo, true);
        this.controller = controller;
        this.entidade = entidade;
        this.currentFormMode = mode;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formPanel = construirFormulario();
        habilitarCamposPorModo(currentFormMode);
        preencherDadosCampos(currentFormMode, entidade);
        formPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
        add(formPanel, BorderLayout.CENTER);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        botoes.add(btnSalvar);
        botoes.add(btnCancelar);
        botoes.add(btnLimpar);
        botoes.add(btnDeletar);
        botoes.add(btnHabilitarEdicao);
        
        add(botoes, BorderLayout.SOUTH);

        setFormButtonsProps(currentFormMode);
        setupListeners(onSuccess);

        pack();
        setLocationRelativeTo(parent);
        setResizable(true);
		
	}

    private void setFormButtonsProps(FormMode currentFormMode){
        btnSalvar.setVisible(currentFormMode == FormMode.CREATE || currentFormMode == FormMode.EDIT);
        btnLimpar.setVisible(currentFormMode == FormMode.CREATE);
        btnDeletar.setVisible(currentFormMode == FormMode.EDIT);
        btnCancelar.setVisible(true);
        btnHabilitarEdicao.setVisible(currentFormMode == FormMode.VIEW);

    }

    private void setupListeners(Runnable onSuccess){
        btnSalvar.addActionListener(e -> {
            Lista<String> dadosInput = getDadosInput();
            try {
                T novaEntidade = controller.criarEntidade(entidade, dadosInput);
                if(currentFormMode == FormMode.CREATE) controller.salvar(novaEntidade);
                if(currentFormMode == FormMode.EDIT) controller.atualizar(novaEntidade);
                Alerta.sucesso(GenericFormDialog.this, "Operação realizada com sucesso");
                onSuccess.run();
                dispose();
            } catch (Exception e1) {
                Alerta.erro(e1);
            }
        });

        btnLimpar.addActionListener(e -> limparFormulario());
        btnCancelar.addActionListener(e -> {
            if(currentFormMode == FormMode.EDIT){
                currentFormMode = FormMode.VIEW;
                habilitarCamposPorModo(currentFormMode);
                preencherDadosCampos(currentFormMode, entidade); 
                setFormButtonsProps(currentFormMode);
                revalidate();
                repaint();
            }else{
                dispose();
            }
        });

        btnHabilitarEdicao.addActionListener(e -> {
            currentFormMode = FormMode.EDIT;
            setFormButtonsProps(currentFormMode);
            habilitarCamposPorModo(currentFormMode);
            revalidate();
            repaint();
        });

        btnDeletar.addActionListener(e -> {
            if(Alerta.confirmar(GenericFormDialog.this, "Confirmar exclusão? Tem certeza que quer excluir?")){
                try {
                    controller.excluir(entidade);
                    Alerta.sucesso(GenericFormDialog.this, "Operação realizada com sucesso");
                    onSuccess.run();
                    dispose();
                } catch (Exception e1) {
                   Alerta.erro(GenericFormDialog.this, e1);
                }
            }
        });

    }

    protected abstract JPanel construirFormulario();
    protected abstract void habilitarCamposPorModo(FormMode currentFormMode);
    protected abstract void preencherDadosCampos(FormMode currentFormMode, T entidade);
    protected abstract Lista<String> getDadosInput();
    protected abstract void limparFormulario();

}
