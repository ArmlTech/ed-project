package model.dao;

import br.edu.fateczl.Lista;
import br.edu.fateczl.pilha.Pilha;
import model.dto.Professor;
import util.CsvUtils;

import java.io.IOException;

public class ProfessorDAO {

    final private String caminhoArquivo = "C:\\TEMP\\professores.csv";
    public Pilha<Professor> buscarTodos() throws Exception {

        Lista<String[]> dados = CsvUtils.lerArquivo(caminhoArquivo);
        Pilha<Professor> professores = new Pilha<>();

        for(int i = 0, length = dados.size(); i < length; i++){
            String cpf = dados.get(i)[0];
            String nome = dados.get(i)[1];
            Float qtdPontos = Float.parseFloat(dados.get(i)[2]);
            int areaID = Integer.parseInt(dados.get(i)[3]);
            Professor professor = new Professor(cpf, nome, qtdPontos, areaID);
            professores.push(professor);
        }
        return professores;
    }

    public Professor procurarPorCPF(String cpf) throws Exception {

        Pilha<Professor> professores = buscarTodos();

        while(!professores.isEmpty()){
            Professor professor = professores.pop();
            if(professor.getCpf().equals(cpf)){
                return professor;
            }
        }

        throw new Exception("Professor não encontrado");
    }

    public void cadastrarNovo(Professor professor) throws IOException {
        CsvUtils.adicionarLinhaCSV(caminhoArquivo, professor.toCSV());
    }

    public void atualizarDados(Professor professorAtualizado) throws Exception {

        Pilha<Professor> professores = buscarTodos();
        Lista<String> professoresCSV = new Lista<>();

        while(!professores.isEmpty()){
            Professor professor = professores.pop();
            if(professor.getCpf().equals(professorAtualizado.getCpf())){
                professor = professorAtualizado;
            }
            professoresCSV.addLast(professor.toCSV());
        }

        CsvUtils.escreverCSV(caminhoArquivo, professoresCSV);
    }

    public void excluir (Professor professorExcluir) throws Exception {

        Pilha<Professor> professores = buscarTodos();
        Lista<String> professoresCSV = new Lista<>();

        while(!professores.isEmpty()){
            Professor professor = professores.pop();
            if(!professor.getCpf().equals(professorExcluir.getCpf())){
                professoresCSV.addLast(professor.toCSV());
            }
        }

        CsvUtils.escreverCSV(caminhoArquivo, professoresCSV);
    }


}
