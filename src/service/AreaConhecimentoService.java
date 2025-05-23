package service;
import br.edu.fateczl.pilha.Pilha;
import model.dao.AreaConhecimentoDAO;
import model.dto.AreaConhecimento;

public class AreaConhecimentoService {
	
	private final AreaConhecimentoDAO dao;

    public AreaConhecimentoService(){
        this.dao = new AreaConhecimentoDAO();
    }

	public Pilha<AreaConhecimento> listarAreas() throws Exception {
		return dao.buscarTodos();
	}
	
	public AreaConhecimento procurarPorID(int id) throws Exception {
		return dao.procurarPorID(id);
	}
	
}
