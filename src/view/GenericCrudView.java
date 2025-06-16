package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import br.edu.fateczl.Fila;
import controller.IGenericCrudController;
import util.Alerta;

/* 
Classe genérica abstrata que representa uma tela principal baseada em JFrame.
<T>: tipo da entidade que será exibida na tabela (ex: Professor, Disciplina, Curso).
<ID>: tipo do identificador único da entidade (ex: String, Integer).
<C extends CrudController<T, ID>>: restrição para garantir que o controller passado
implementa a interface CrudController, com operações básicas de CRUD sobre o tipo T e identificador do tipo ID.

permite reaproveitar o código para diferentes entidades e controladores,garantindo que todos tenham as operações 
necessárias implementadas.
*/


public abstract class GenericCrudView<T, ID, C extends IGenericCrudController<T, ID>> extends JFrame {
    private static final long serialVersionUID = 1L;
    protected JPanel contentPane;                  // painel principal onde serão colocados componentes
    protected DefaultTableModel tableModel;         // modelo da Tabela JTable
    protected JTable table;                         // componente que exibe os dados em forma de tabela
    protected final C controller;                   // controlador genérico para operações CRUD
    protected JPanel opcoesItemSelecionado;
    ID idEntidadeSelecionada;

    JLabel lblDetalhesSelecionado = new JLabel();
    protected JButton btnNovo = new JButton("Cadastrar novo");
    protected JButton btnExibir = new JButton("Exibir detalhes");
    protected JButton btnCancelarSelecao = new JButton("Cancelar seleção");

    // construtor: recebe título da janela, colunas da tabela e o controlador
    public GenericCrudView(JFrame parent, String titulo, String[] colunas, C controller) {
        this.controller = controller;             
        setTitle(titulo);                           
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setSize(900,600);                           
        setLayout(new BorderLayout());              
        setLocationRelativeTo(null);   
        setResizable(false);

        //painel principal
        contentPane = new JPanel();                 
        contentPane.setBorder(new EmptyBorder(30, 5, 30, 5)); 
        setContentPane(contentPane);                
        
        tableModel = new DefaultTableModel(colunas, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                
                return false;
            }
        };
        
        table = new JTable(tableModel);             
        add(new JScrollPane(table), BorderLayout.WEST);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(650, 450));  
        styleTable();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        carregarTabela();                           
        

        opcoesItemSelecionado = new JPanel();
        opcoesItemSelecionado.setLayout(new BoxLayout(opcoesItemSelecionado, BoxLayout.Y_AXIS));
        opcoesItemSelecionado.setBorder(new EmptyBorder(30, 5, 30, 5));

        opcoesItemSelecionado.add(lblDetalhesSelecionado);
        opcoesItemSelecionado.add(Box.createVerticalStrut(10)); 
        opcoesItemSelecionado.add(btnExibir);
        opcoesItemSelecionado.add(Box.createVerticalStrut(10));
        opcoesItemSelecionado.add(btnCancelarSelecao);
        opcoesItemSelecionado.add(Box.createVerticalStrut(10));
        opcoesItemSelecionado.add(btnNovo);
        cancelarSelecao(); 
        table.getSelectionModel().addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting() && table.getSelectedRow() != -1){
                int linha = table.getSelectedRow();

                idEntidadeSelecionada = (ID) table.getValueAt(linha, 0);
                lblDetalhesSelecionado.setText(getLabelTextEntidadeSelecionada(idEntidadeSelecionada));
                btnExibir.setEnabled(true);
                btnCancelarSelecao.setEnabled(true);
            }
        });

        btnExibir.addActionListener(e -> exibirDetalhesDialog(idEntidadeSelecionada));
        btnCancelarSelecao.addActionListener(e -> cancelarSelecao());
        btnNovo.addActionListener(e -> abrirTelaCadastro());

        contentPane.add(opcoesItemSelecionado, BorderLayout.EAST);                   

        if (parent != null) parent.setEnabled(false);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                if (parent != null) {
                    parent.setEnabled(true); 
                    parent.toFront(); 
                }
            }
        });

    }

    private void styleTable() {
        table.setRowHeight(20);  
        table.setShowGrid(true);       
        table.setIntercellSpacing(new Dimension(0, 1));      
        table.setSelectionForeground(Color.BLACK);               
        table.setRowHeight(25);  
        table.setIntercellSpacing(new Dimension(10, 10));  
    
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15)); 
        table.setDefaultRenderer(Object.class, renderer);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);           
    }

    
    
    protected void ocultarColunaID(int id) {
        var colunaID = table.getColumnModel().getColumn(id);
        colunaID.setMinWidth(0);
        colunaID.setMaxWidth(0);
        colunaID.setWidth(0);
        colunaID.setPreferredWidth(0);
    }

    private void cancelarSelecao() {
        lblDetalhesSelecionado.setText("Nenhum selecionado");
        btnExibir.setEnabled(false);
        btnCancelarSelecao.setEnabled(false);
        table.clearSelection();
        table.getSelectionModel().clearSelection();
        table.requestFocusInWindow();
        opcoesItemSelecionado.revalidate();
        opcoesItemSelecionado.repaint();
    }
    
    protected void carregarTabela() {
        tableModel.setRowCount(0);                  
        try {
            Fila<T> entidades = controller.buscarTodos();
            while(!entidades.isEmpty()) {
                T entidade = entidades.remove();       
                tableModel.addRow(extrairLinha(entidade)); 
            }
        } catch (Exception e) {
            Alerta.erro(e);                        
        }
        ocultarColunaID(0);
    }

    protected abstract Object[] extrairLinha(T entidade) throws Exception;
    protected abstract void exibirDetalhesDialog(ID id);
    protected abstract String getLabelTextEntidadeSelecionada(ID id);
    protected abstract void abrirTelaCadastro();
}

