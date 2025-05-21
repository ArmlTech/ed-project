package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.edu.fateczl.Lista;
import br.edu.fateczl.pilha.Pilha;
import controller.ProfessorController;
import model.dto.AreaConhecimento;
import model.dto.Professor;

public class ProfessorCadastroDialog extends JDialog {

	ProfessorController controller;
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfNome;
	private JTextField tfQtdPontos;
	private JTextField tfCPF;
	private JComboBox<AreaConhecimento> cbProfessorArea;
	
	private void limparFormulario() {
		tfCPF.setText("");
		tfNome.setText("");
		tfQtdPontos.setText("");
		cbProfessorArea.setSelectedIndex(0);
		
	}

	public ProfessorCadastroDialog(JFrame parent, Runnable onSuccess) {
		
		controller = new ProfessorController();
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
				
		JLabel lblProfessorNome = new JLabel("Nome");
		lblProfessorNome.setBounds(10, 14, 27, 14);
		contentPanel.add(lblProfessorNome);
		
		tfNome = new JTextField();
		tfNome.setBounds(42, 11, 86, 20);
		contentPanel.add(tfNome);
		tfNome.setColumns(10);
		
		JLabel lblProfessorArea = new JLabel("Área de conhecimentos");
		lblProfessorArea.setBounds(10, 92, 112, 14);
		contentPanel.add(lblProfessorArea);
		
		JLabel lblProfessorQtdPontos = new JLabel("Quantidade de pontos");
		lblProfessorQtdPontos.setBounds(10, 67, 107, 14);
		contentPanel.add(lblProfessorQtdPontos);
		
		JLabel lblCPF = new JLabel("CPF");
		lblCPF.setBounds(10, 42, 19, 14);
		contentPanel.add(lblCPF);
		
		tfQtdPontos = new JTextField();
		tfQtdPontos.setBounds(137, 64, 86, 20);
		contentPanel.add(tfQtdPontos);
		tfQtdPontos.setColumns(10);
		
		tfCPF = new JTextField();
		tfCPF.setBounds(42, 39, 86, 20);
		contentPanel.add(tfCPF);
		tfCPF.setColumns(10);
		
		cbProfessorArea = new JComboBox<AreaConhecimento>();
		cbProfessorArea.setBounds(132, 88, 30, 22);
		contentPanel.add(cbProfessorArea);
		
		cbProfessorArea.addItem(new AreaConhecimento(-1, "Selecione"));
		try {
			Lista<AreaConhecimento> areas = controller.listarAreas();
			for(int i = 0, length = areas.size(); i < length; i++) {
				try {
					cbProfessorArea.addItem(areas.get(i));
				} catch (Exception e) {
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
		btnLimpar.setBounds(42, 166, 63, 23);
		contentPanel.add(btnLimpar);
				
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnProfessorCadastrar = new JButton("Cadastrar");
				btnProfessorCadastrar.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						String nome = tfNome.getText();
						String cpf = tfCPF.getText();
						String pontos = tfQtdPontos.getText();
						AreaConhecimento area = (AreaConhecimento) cbProfessorArea.getSelectedItem();
						
						if (nome.isBlank() || cpf.isBlank() || pontos.isBlank() || area.getAreaID() == -1) {
							JOptionPane.showMessageDialog(ProfessorCadastroDialog.this, 
									"Preencha todos os campos",
									"Atenção",
									JOptionPane.WARNING_MESSAGE);
							return;
						}
						
						try {
							Professor professor = new Professor(cpf, nome, Float.parseFloat(pontos), area.getAreaID());
							controller.cadastrar(professor);
							Pilha<Professor> professores = controller.listarProfessores();
							System.out.println(professores.pop().toString());
							
							onSuccess.run();
							dispose();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(ProfessorCadastroDialog.this,
		                            "Erro ao cadastrar professor: " + e1.getMessage(),
		                            "Erro de Cadastro",
		                            JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				btnProfessorCadastrar.setActionCommand("Cadastrar");
				buttonPane.add(btnProfessorCadastrar);
				getRootPane().setDefaultButton(btnProfessorCadastrar);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancelar");
				buttonPane.add(cancelButton);
			}
		}
	}

}
