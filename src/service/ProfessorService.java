package service;

import br.edu.fateczl.pilha.Pilha;
import model.dao.ProfessorDAO;
import model.dto.Professor;

public class ProfessorService {

    private final ProfessorDAO dao;

    public ProfessorService(){
        this.dao = new ProfessorDAO();
    }
    
    public Pilha<Professor> listarProfessores() throws Exception {
        return dao.buscarTodos();
    }

    public void cadastrar(Professor professor) throws Exception {
        if(existeCPF(professor)){
            throw new Exception("CPF já cadastrado");
        }
        dao.cadastrarNovo(professor);
    }

    public void atualizar(Professor professor) throws Exception {
        dao.atualizarDados(professor);
    }

    public void excluir(String cpf) throws Exception {
        dao.excluir(cpf);
    }

    public boolean existeCPF(Professor professor){
        try {
            dao.procurarPorCPF(professor.getCpf());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
