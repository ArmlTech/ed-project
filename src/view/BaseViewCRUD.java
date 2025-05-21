package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public abstract class BaseViewCRUD extends JFrame{
	
	protected void exibirMensagemSucesso(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    protected void exibirMensagemErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    protected void exibirMensagemAviso(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Aviso", JOptionPane.WARNING_MESSAGE);
    }
    
    protected abstract void carregarTabela();
    protected abstract void limparFormulario();
	
}
