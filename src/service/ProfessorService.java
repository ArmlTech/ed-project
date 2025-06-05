package service;
import java.io.IOException;

import model.dao.ProfessorDAO;
import model.dto.Professor;

public class ProfessorService extends GenericService<Professor, Integer, ProfessorDAO> {

    public ProfessorService() throws IOException{
        super(new ProfessorDAO());
    }
    
    @Override
    public void salvar(Professor professor) throws Exception {
        if(existeCPF(professor)) throw new Exception("CPF já cadastrado");
        dao.salvar(professor);
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
}
