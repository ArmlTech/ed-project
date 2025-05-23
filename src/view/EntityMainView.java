package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.edu.fateczl.pilha.Pilha;
import controller.CrudController;
import controller.ProfessorController;
import util.Alerta;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public abstract class EntityMainView<T, C extends CrudController<T>> extends JFrame {
	
	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected DefaultTableModel tableModel;
	protected JTable table;
	
	public EntityMainView(String titulo, String[] colunas, C controller) {
		setLocationRelativeTo(null);
		setTitle(titulo);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(700,500);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		tableModel = new DefaultTableModel(colunas, 0); // criação do esqueleto da tabela e definir que as colunas aparecerão na primeira linha
		table = new JTable(tableModel); //carregar esqueleto da tabela na tabela
		add(new JScrollPane(table), BorderLayout.CENTER);
		carregarTabela(); // //chamar o método que carrega os dados da tabela vindos do CSV (banco de dados) (MÉTODO DEVE SER IMPLEMENTADO DE FORMA ESPECIALIZADA)
		
		JButton btnNovo = new JButton("Cadastrar");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroDialog dialog = new CadastroDialog(EntityMainView.this, () -> carregarTabela());
				dialog.setVisible(true);
			}
		});
		contentPane.add(btnNovo);
	}
	
	protected void carregarTabela() {
		tableModel.setRowCount(0);
		
		try {
			Pilha<T> entidades = buscarEntidades();
			
			while(!entidades.isEmpty()) {
				T entidade = entidades.pop();				
				tableModel.addRow(extrairLinha(entidade));
			}
		} catch (Exception e) {
			Alerta.erro(e);
		}
	}

	protected abstract Pilha<T> buscarEntidades();
	protected abstract Object[] extrairLinha(T entidade);


}
