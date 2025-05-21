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
	

	@Override
	protected void limparFormulario() {
		tfCPF.setText("");
		tfNome.setText("");
		tfQtdPontos.setText("");
		cbProfessorArea.setSelectedIndex(0);
		
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

		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("684px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				RowSpec.decode("461px"),}));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, "1, 1, fill, fill");
		
		JPanel panelCadastrarProfessor = new JPanel();
		tabbedPane.addTab("Cadastrar", null, panelCadastrarProfessor, null);
		panelCadastrarProfessor.setLayout(null);				
		
		JLabel lblProfessorNome = new JLabel("Nome");
		lblProfessorNome.setBounds(54, 49, 27, 14);
		panelCadastrarProfessor.add(lblProfessorNome);
		
		tfNome = new JTextField();
		tfNome.setBounds(173, 46, 250, 20);
		panelCadastrarProfessor.add(tfNome);
		tfNome.setColumns(10);
		
		JLabel lblProfessorArea = new JLabel("Área de conhecimentos");
		lblProfessorArea.setBounds(54, 99, 130, 14);
		panelCadastrarProfessor.add(lblProfessorArea);
		
		JLabel lblProfessorQtdPontos = new JLabel("Quantidade de pontos");
		lblProfessorQtdPontos.setBounds(54, 74, 130, 14);
		panelCadastrarProfessor.add(lblProfessorQtdPontos);
		
		JLabel lblCPF = new JLabel("CPF");
		lblCPF.setBounds(54, 24, 46, 14);
		panelCadastrarProfessor.add(lblCPF);
		
		tfQtdPontos = new JTextField();
		tfQtdPontos.setBounds(173, 71, 57, 20);
		panelCadastrarProfessor.add(tfQtdPontos);
		tfQtdPontos.setColumns(10);
		
		tfCPF = new JTextField();
		tfCPF.setBounds(173, 21, 130, 20);
		panelCadastrarProfessor.add(tfCPF);
		tfCPF.setColumns(10);
		
		cbProfessorArea = new JComboBox<AreaConhecimento>();
		cbProfessorArea.setBounds(173, 95, 130, 22);
		panelCadastrarProfessor.add(cbProfessorArea);
		
		cbProfessorArea.addItem(new AreaConhecimento(-1, "Selecione"));
		try {
			Lista<AreaConhecimento> areas = controller.listarAreas();
			for(int i = 0, length = areas.size(); i < length; i++) {
				try {
					cbProfessorArea.addItem(areas.get(i));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparFormulario();
			}
		});
		btnLimpar.setBounds(115, 168, 89, 23);
		panelCadastrarProfessor.add(btnLimpar);
		
		JButton btnProfessorCadastrar = new JButton("Cadastrar");
		btnProfessorCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = tfNome.getText();
				String cpf = tfCPF.getText();
				String pontos = tfQtdPontos.getText();
				AreaConhecimento area = (AreaConhecimento) cbProfessorArea.getSelectedItem();
				
				if (nome.isBlank() || cpf.isBlank() || pontos.isBlank() || area.getAreaID() == -1) {
					JOptionPane.showMessageDialog(ProfessorView.this, 
							"Preencha todos os campos",
							"Atenção",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				Professor professor = new Professor(cpf, nome, Float.parseFloat(pontos), area.getAreaID());
				try {
					controller.cadastrar(professor);
					carregarTabela();
					Pilha<Professor> professores = controller.listarProfessores();
					System.out.println(professores.pop().toString());
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(ProfessorView.this,
                            "Erro ao cadastrar professor: " + e1.getMessage(),
                            "Erro de Cadastro",
                            JOptionPane.ERROR_MESSAGE);
				}
				
				
				
			}
		});
		btnProfessorCadastrar.setBounds(214, 168, 89, 23);
		panelCadastrarProfessor.add(btnProfessorCadastrar);
		
		JPanel panelListarProfessores = new JPanel();
		tabbedPane.addTab("Consultar", null, panelListarProfessores, "Lista de professores");
		
		String[] colunas = {"CPF", "Nome", "Pontos", "Área do conhecimento"};
		tableModel = new DefaultTableModel(colunas, 0);
		tableProfessores = new JTable(tableModel);
		carregarTabela();
		
		JButton btnNewButton = new JButton("New button");
		panelListarProfessores.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane(tableProfessores);
		panelListarProfessores.add(scrollPane);
		
		
	}


}
