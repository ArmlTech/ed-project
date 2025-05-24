package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import br.edu.fateczl.pilha.Pilha;
import controller.IGenericController;
import util.Alerta;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

//Classe genérica abstrata que representa uma tela principal baseada em JFrame.
//<T>: Tipo da entidade que será exibida na tabela (ex: Professor, Disciplina, Curso).
//<ID>: Tipo do identificador único da entidade (ex: String, Integer).
//<C extends CrudController<T, ID>>: Restrição para garantir que o controller passado
//implementa a interface CrudController, com operações básicas de CRUD sobre o tipo T e identificador do tipo ID.
//Isso permite reaproveitar o código para diferentes entidades e controladores,
//garantindo que todos tenham as operações necessárias implementadas.
public abstract class EntityMainView<T, ID, C extends IGenericController<T, ID>> extends JFrame {
    private static final long serialVersionUID = 1L;
    protected JPanel contentPane;                  // Painel principal onde serão colocados componentes
    protected DefaultTableModel tableModel;         // Modelo da Tabela JTable
    protected JTable table;                         // Componente que exibe os dados em forma de tabela
    protected final C controller;                   // Controlador genérico para operações CRUD

    // Construtor: recebe título da janela, colunas da tabela e o controlador
    public EntityMainView(String titulo, String[] colunas, C controller) {
        this.controller = controller;               // Armazena o controlador que será usado na função BuscarEntidades()
        setLocationRelativeTo(null);                // Centraliza a janela na tela
        setTitle(titulo);                           // Define o texto da barra de título
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha apenas esta janela
        setSize(700,500);                           // Define tamanho inicial da janela
        setLayout(new BorderLayout());              // Usa BorderLayout para organizar componentes

        // Configura o painel principal
        contentPane = new JPanel();                 // Cria o painel principal de conteúdo
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Margens internas
        setContentPane(contentPane);                // Seta o painel como content pane da janela
        
        // Configura a tabela de dados
        tableModel = new DefaultTableModel(colunas, 0); // Cria modelo com colunas e 0 linhas
        table = new JTable(tableModel);             // Cria JTable usando o modelo
        add(new JScrollPane(table), BorderLayout.CENTER); // Adiciona a tabela em um JScrollPane ao centro
        carregarTabela();                           // Popula a tabela com dados iniciais

        // Botão para abrir o formulário de cadastro
        JButton btnNovo = new JButton("Cadastrar");
        btnNovo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirTelaCadastro();                // Chama método abstrato para abrir dialog de cadastro
            }
        });
        contentPane.add(btnNovo);                   // Adiciona o botão ao painel principal
    }
    
    // Método abstrato que cada tela concreta deve implementar para exibir seu formulário de cadastro
    protected abstract void abrirTelaCadastro();

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

    // Método abstrato para puxar as entidades através do controlador
    protected abstract Pilha<T> buscarEntidades() throws Exception;
    
    // Método abstrato para converter cada entidade em uma linha do tipo aceitado pela tableModel (Object[])
    protected abstract Object[] extrairLinha(T entidade) throws Exception;
}