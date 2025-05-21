package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import br.edu.fateczl.Lista;
import br.edu.fateczl.pilha.Pilha;
import controller.ProfessorController;
import model.dto.AreaConhecimento;
import model.dto.Professor;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ProfessorView extends BaseViewCRUD {
	
	ProfessorController controller;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfQtdPontos;
	private JTextField tfCPF;
	private JComboBox<AreaConhecimento> cbProfessorArea;
	private DefaultTableModel tableModel;
	private JTable tableProfessores;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfessorView frame = new ProfessorView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	@Override
	protected void carregarTabela() {
		tableModel.setRowCount(0);
		
		try {
			Pilha<Professor> professores = controller.listarProfessores();
			
			while(!professores.isEmpty()) {
				Professor professor = professores.pop();
				
				Object[] dadosLinha = {
						professor.getCpf(),
						professor.getNome(),
						professor.getQtdPontos(),
						//controller.buscarAreaPorId(professor.getAreaID()).getNome()
						"ciencia"
				};
				
				tableModel.addRow(dadosLinha);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Create the frame.
	 */
	public ProfessorView() {
		controller = new ProfessorController();
		
		setTitle("Gerenciar professores");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
		String[] colunas = {"CPF", "Nome", "Pontos", "Área do conhecimento"};
		tableModel = new DefaultTableModel(colunas, 0);
		tableProfessores = new JTable(tableModel);
		carregarTabela();
		
		JButton btnAbrirCadastro = new JButton("Cadastrar");
		btnAbrirCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfessorCadastroDialog dialogCadastro = new ProfessorCadastroDialog(ProfessorView.this, () -> carregarTabela());
				dialogCadastro.setVisible(true);
			}
		});
		btnAbrirCadastro.setBounds(20, 20, 120, 30);
		contentPane.add(btnAbrirCadastro);
		
		JScrollPane scrollPane = new JScrollPane(tableProfessores);
		scrollPane.setBounds(20, 70, 640, 350);
		contentPane.add(scrollPane);
		
		
	}


}
