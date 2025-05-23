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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import br.edu.fateczl.pilha.Pilha;
import controller.ProfessorController;
import model.dto.AreaConhecimento;
import model.dto.Professor;
import util.Alerta;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

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
		
		super(parent, "Cadastro de Professor", true); //travar a tela de cadastro em relação ao parent até que seja fechada
		controller = new ProfessorController();
		
		setLocationRelativeTo(parent); //ficar no centro da interface mae
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
				
		JLabel lblProfessorNome = new JLabel("Nome");
		
		tfNome = new JTextField();
		tfNome.setColumns(10);
		
		JLabel label = new JLabel("");
		
		JLabel lblCPF = new JLabel("CPF");
		
		tfCPF = new JTextField();
		tfCPF.setColumns(10);
		
		JLabel label_1 = new JLabel("");
		
		JLabel lblProfessorQtdPontos = new JLabel("Quantidade de pontos");
		
		JLabel label_2 = new JLabel("");
		
		tfQtdPontos = new JTextField();
		tfQtdPontos.setColumns(10);
		
		JLabel lblProfessorArea = new JLabel("Área de conhecimentos");
		
		JLabel label_3 = new JLabel("");
		
		cbProfessorArea = new JComboBox<AreaConhecimento>();
		
		cbProfessorArea.addItem(new AreaConhecimento(-1, "Selecione"));
		try {
			Pilha<AreaConhecimento> areas = controller.listarAreas();
			while(!areas.isEmpty()) {
				cbProfessorArea.addItem(areas.pop());
			}
		} catch(Exception e) {
			Alerta.erro(ProfessorCadastroDialog.this, e);
		}
		
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparFormulario();
			}
		});
		
		JLabel label_4 = new JLabel("");
		
		JLabel label_5 = new JLabel("");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblProfessorNome, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfNome, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblCPF, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfCPF, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(27)
							.addComponent(btnLimpar, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblProfessorArea, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cbProfessorArea, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblProfessorQtdPontos, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(tfQtdPontos, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)))
							.addGap(10)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(151)
									.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
								.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(1)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(tfNome, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProfessorNome, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblCPF, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addComponent(tfCPF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblProfessorQtdPontos, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addComponent(tfQtdPontos, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblProfessorArea, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addComponent(cbProfessorArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
							.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLimpar, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))))
		);
		contentPanel.setLayout(gl_contentPanel);
				
		
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
							Alerta.aviso(ProfessorCadastroDialog.this, "Preencha todos os campos");
							return;
						}
						
						try {
							Professor professor = new Professor(cpf, nome, Float.parseFloat(pontos), area.getAreaID());
							controller.cadastrar(professor);							
							onSuccess.run();
							dispose();
						} catch (Exception e1) {
							Alerta.erro(ProfessorCadastroDialog.this, e1);
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
