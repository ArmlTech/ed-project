package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.edu.fateczl.Lista;
import controller.InscritosController;
import model.dto.InscritoDisplay;
import util.Alerta;
public class InscritosView extends JFrame{

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private DefaultTableModel tableModel;
    private JTable table;

    private final static String titulo = "Gerenciar Inscritos";
    private final static String[] colunas = {
        "Pontuação", "CPF", "Professor", "Área", "Disciplina", "Processo"
    };
    private final static InscritosController controller = new InscritosController();

    public InscritosView(JFrame parent) {
        setTitle(titulo);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(parent);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        tableModel = new DefaultTableModel(colunas, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
        carregarTabela();

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

    private void carregarTabela() {
        try {
            tableModel.setRowCount(0);
            Lista<InscritoDisplay> inscritos = controller.buscarTodos();
            for(int i = 0, length = inscritos.size(); i < length; i++) {
                InscritoDisplay inscrito = inscritos.get(i);
                tableModel.addRow(new Object[] {
                    inscrito.getQtdPontos(),
                    inscrito.getCpf(),
                    inscrito.getNome(),
                    inscrito.getArea(),
                    inscrito.getDisciplina(),
                    inscrito.getIdProcesso()
                });
            }

        } catch (Exception e) {
            Alerta.erro("Erro ao carregar inscritos: " + e.getMessage());
        }
    }

}
