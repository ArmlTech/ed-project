package view;

import javax.swing.*;

import controller.AreaConhecimentoController;
import controller.DisciplinaController;
import controller.IGenericController;
import controller.InscricaoController;
import controller.ProfessorController;
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
        btnInscrito = new JButton("Gerenciar Inscritos");
        btnSair = new JButton("Sair");
        
        initListeners();
        
        panel.add(btnAreaConhecimento);
        panel.add(btnProfessor);
        panel.add(btnDisciplina);
        panel.add(btnCurso);
        panel.add(btnInscricao);
        panel.add(btnInscrito);
        panel.add(btnSair);
        
        add(panel);
    }
    
    private void initListeners(){
        btnProfessor.addActionListener(e -> abrirCrudGenerico(
        new ProfessorController(),
        "Professores",
        new String[]{"ID", "CPF", "Nome", "Pontos", "Área do conhecimento"},
        ProfessorView.class
        ));
        
        btnAreaConhecimento.addActionListener(e -> abrirCrudGenerico(
        new AreaConhecimentoController(),
        "Áreas de Conhecimento",
        new String[]{"ID", "Nome"},
        AreaConhecimentoView.class
        ));
        
        btnDisciplina.addActionListener(e -> abrirCrudGenerico(
        new DisciplinaController(),
        "Disciplinas",
        new String[]{"ID","Nome", "Dia da semana", "Hora inicial", "Qtd. de horas", "Curso"},
        DisciplinaView.class
        ));
        
        btnInscricao.addActionListener(e -> abrirCrudGenerico(
        new InscricaoController(),
        "Inscrições",
        new String[]{"ID","Inscrito", "Código do processo", "Disciplina"},
        InscricaoView.class
        ));
        
        btnSair.addActionListener(e -> dispose());
    }
    
    private void abrirCrudGenerico(IGenericController<?, Integer> controller, String titulo, String[] colunas, Class<? extends JFrame> viewClass) {
        try {
            JFrame janela = viewClass
            .getConstructor(JFrame.class, String.class, String[].class, controller.getClass())
            .newInstance(this, titulo, colunas, controller);
            janela.setVisible(true);
        } catch (Exception e) {
            Alerta.erro(null, "Erro ao iniciar a tela: " + e.getMessage());
        }
    }
    
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main mainFrame = new Main();
            mainFrame.setVisible(true);
        });
    }
}
