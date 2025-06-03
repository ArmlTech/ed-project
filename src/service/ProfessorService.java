package service;

import br.edu.fateczl.pilha.Pilha;
import model.dao.ProfessorDAO;
import model.dto.Professor;

public class ProfessorService implements IGenericService<Professor, Integer> {

    private final ProfessorDAO dao;

    public ProfessorService(){
        this.dao = new ProfessorDAO();
    }
    
    @Override
    public void salvar(Professor professor) throws Exception {
        if(existeCPF(professor)) throw new Exception("CPF já cadastrado");
        dao.salvar(professor);
    }
    
    @Override
    public Pilha<Professor> buscarTodos() throws Exception {
        return dao.buscarTodos();
    }

    @Override
    public void atualizar(Professor professor) throws Exception {
        dao.atualizar(professor);
    }

    @Override
    public void excluir(Integer id) throws Exception {
        dao.excluir(id);
    }

    public boolean existeCPF(Professor professor){
        try {
            dao.buscarPorCPF(professor.getCpf());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

	public Professor buscarPorCPF(String id) throws Exception {
		return dao.buscarPorCPF(id);
	}

    @Override
    public Professor buscarPorID(Integer id) throws Exception {
        return dao.buscarPorID(id);
    }

}
