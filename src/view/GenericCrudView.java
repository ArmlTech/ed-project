package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.edu.fateczl.pilha.Pilha;
import controller.IGenericController;
import util.Alerta;

//Classe genérica abstrata que representa uma tela principal baseada em JFrame.
//<T>: Tipo da entidade que será exibida na tabela (ex: Professor, Disciplina, Curso).
//<ID>: Tipo do identificador único da entidade (ex: String, Integer).
//<C extends CrudController<T, ID>>: Restrição para garantir que o controller passado
//implementa a interface CrudController, com operações básicas de CRUD sobre o tipo T e identificador do tipo ID.
//Isso permite reaproveitar o código para diferentes entidades e controladores,
//garantindo que todos tenham as operações necessárias implementadas.
public abstract class GenericCrudView<T, ID, C extends IGenericController<T, ID>> extends JFrame {
    private static final long serialVersionUID = 1L;
    protected JPanel contentPane;                  // Painel principal onde serão colocados componentes
    protected DefaultTableModel tableModel;         // Modelo da Tabela JTable
    protected JTable table;                         // Componente que exibe os dados em forma de tabela
    protected final C controller;                   // Controlador genérico para operações CRUD
    protected JPanel opcoesItemSelecionado;
    String idEntidadeSelecionada;

    JLabel lblDetalhesSelecionado = new JLabel();
    protected JButton btnNovo = new JButton("Cadastrar novo");
    protected JButton btnExibir = new JButton("Exibir detalhes");
    protected JButton btnCancelarSelecao = new JButton("Cancelar seleção");
    

    // Construtor: recebe título da janela, colunas da tabela e o controlador
    public GenericCrudView(String titulo, String[] colunas, C controller) {
        this.controller = controller;             // Armazena o controlador que será usado na função BuscarEntidades()
        setTitle(titulo);                           // Define o texto da barra de título
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha apenas esta janela
        setSize(800,500);                           // Define tamanho inicial da janela
        setLayout(new BorderLayout());              // Usa BorderLayout para organizar componentes
        setLocationRelativeTo(null);   // Centraliza a janela na tela
        setResizable(false);

        // Configura o painel principal
        contentPane = new JPanel();                 // Cria o painel principal de conteúdo
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Margens internas
        setContentPane(contentPane);                // Seta o painel como content pane da janela
        
        // Configura a tabela de dados
        tableModel = new DefaultTableModel(colunas, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                //sobscrevendo metodo da DefaultTableModel pra tornar celulas view only
                return false;
            }
        };
        table = new JTable(tableModel);             // Cria JTable usando o modelo
        add(new JScrollPane(table), BorderLayout.WEST); // Adiciona a tabela em um JScrollPane ao centro
        carregarTabela();                           // Popula a tabela com dados iniciais


        
        opcoesItemSelecionado = new JPanel();
        opcoesItemSelecionado.setLayout(new BoxLayout(opcoesItemSelecionado, BoxLayout.Y_AXIS));
        opcoesItemSelecionado.setBorder(new EmptyBorder(30, 20, 30, 20));

        opcoesItemSelecionado.add(lblDetalhesSelecionado);
        opcoesItemSelecionado.add(Box.createVerticalStrut(10)); // espaço entre componentes
        opcoesItemSelecionado.add(btnExibir);
        opcoesItemSelecionado.add(Box.createVerticalStrut(10));
        opcoesItemSelecionado.add(btnCancelarSelecao);
        opcoesItemSelecionado.add(Box.createVerticalStrut(10));
        opcoesItemSelecionado.add(btnNovo);
        cancelarSelecao(); // reseta seleções, botões e label de entidade selecionada
        table.getSelectionModel().addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting() && table.getSelectedRow() != -1){
                int linha = table.getSelectedRow();
                idEntidadeSelecionada = (String) table.getValueAt(linha, 0);
                lblDetalhesSelecionado.setText(getLabelTextEntidadeSelecionada(idEntidadeSelecionada));
                btnExibir.setEnabled(true);
                btnCancelarSelecao.setEnabled(true);
            }
        });

        btnExibir.addActionListener(e -> exibirDetalhesDialog(idEntidadeSelecionada));
        btnCancelarSelecao.addActionListener(e -> cancelarSelecao());
        btnNovo.addActionListener(e -> abrirTelaCadastro());
        
        contentPane.add(opcoesItemSelecionado, BorderLayout.EAST);                   // Adiciona o botão ao painel principal
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
 
    // Método que limpa e recarrega a tabela com dados obtidos via buscarEntidades()
    protected void carregarTabela() {
        tableModel.setRowCount(0);                  // Remove todas as linhas atuais
        try {
            Pilha<T> entidades = buscarEntidades(); // Obtém os registros via controlador
            while(!entidades.isEmpty()) {
                T entidade = entidades.pop();       // Para cada item na pilha
                tableModel.addRow(extrairLinha(entidade)); // Converte em array de objetos e adiciona como linha da tabela
            }
        } catch (Exception e) {
            Alerta.erro(e);                        // Mostra mensagem de erro em caso de exceção
        }
    }

    protected Pilha<T> buscarEntidades() throws Exception{
    	return controller.buscarTodos();
    };

    
    
    // Método abstrato para converter cada entidade em uma linha do tipo aceitado pela tableModel (Object[])
    protected abstract Object[] extrairLinha(T entidade) throws Exception;
    protected abstract void exibirDetalhesDialog(String id);
    protected abstract String getLabelTextEntidadeSelecionada(String id);
    protected abstract void abrirTelaCadastro();
}