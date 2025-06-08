package view;

import javax.swing.*;

import util.Alerta;
import java.awt.*;

public class Main extends JFrame {
    
    private static final long serialVersionUID = 1L;
    
    JButton btnAreaConhecimento;
    JButton btnProfessor;
    JButton btnDisciplina;
    JButton btnCurso;
    JButton btnInscricao;
    JButton btnInscrito;
    JButton btnProcesso;
    JButton btnSair;
    
    public Main() {
        setTitle("Sistema de Inscrições");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        
        btnAreaConhecimento = new JButton("Gerenciar Áreas de Conhecimento");
        btnProfessor = new JButton("Gerenciar Professores");
        btnDisciplina = new JButton("Gerenciar Disciplinas");
        btnCurso = new JButton("Gerenciar Cursos");
        btnInscricao = new JButton("Gerenciar Inscrições");
        btnInscrito = new JButton("Consultar Inscritos");
        btnProcesso = new JButton("Gerenciar Processos");
        btnSair = new JButton("Sair");
        
        initListeners();
        
        panel.add(btnAreaConhecimento);
        panel.add(btnProfessor);
        panel.add(btnDisciplina);
        panel.add(btnCurso);
        panel.add(btnInscricao);
        panel.add(btnInscrito);
        panel.add(btnProcesso);
        panel.add(btnSair);
        
        add(panel);
    }
    
    private void initListeners(){
        btnProfessor.addActionListener(e -> abrirCrudGenerico(ProfessorView.class));
        btnAreaConhecimento.addActionListener(e -> abrirCrudGenerico(AreaConhecimentoView.class));
        btnDisciplina.addActionListener(e -> abrirCrudGenerico(DisciplinaView.class));
        btnCurso.addActionListener(e -> abrirCrudGenerico(CursoView.class));
        btnInscricao.addActionListener(e -> abrirCrudGenerico(InscricaoView.class));
        btnInscrito.addActionListener(e -> abrirCrudGenerico(InscritosView.class));
        btnProcesso.addActionListener(e -> abrirCrudGenerico(ProcessoView.class));
        btnSair.addActionListener(e -> dispose());
    }
    
    private void abrirCrudGenerico(Class<? extends JFrame> viewClass) {
        try {
            SwingUtilities.invokeLater(() -> {
                try {
                        JFrame janela = viewClass
                    .getConstructor(JFrame.class)
                    .newInstance(this);
                    janela.setVisible(true);
                } catch (Exception e) {
                    Alerta.erro(null, "Erro ao abrir a janela: " + e.getMessage());
                }
            });
        } catch (Exception e) {
            Alerta.erro(null, "Erro ao abrir a janela: " + e.getMessage());
        }
            

    }
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main mainFrame = new Main();
            mainFrame.setVisible(true);
        });
    }
}
