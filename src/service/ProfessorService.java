package service;

import br.edu.fateczl.pilha.Pilha;
import model.dao.ProfessorDAO;
import model.dto.Professor;

public class ProfessorService implements IGenericService<Professor, String> {

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
    public void excluir(String cpf) throws Exception {
        dao.excluir(cpf);
    }

    public boolean existeCPF(Professor professor){
        try {
            dao.buscarPorID(professor.getCpf());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

	@Override
	public Professor buscarPorID(String id) throws Exception {
		return dao.buscarPorID(id);
	}

}
