package controller;

import br.edu.fateczl.pilha.Pilha;
import model.dto.AreaConhecimento;
import model.dto.Professor;
import service.AreaConhecimentoService;
import service.ProfessorService;

public class ProfessorController implements IGenericController<Professor, String>{

    private final ProfessorService service;
    private final AreaConhecimentoService areaService;

    public ProfessorController() {
        this.service = new ProfessorService();
        this.areaService = new AreaConhecimentoService();
    }

	@Override
	public void salvar(Professor entidade) throws Exception {
		service.salvar(entidade);
	}
	
	@Override
	public Pilha<Professor> buscarTodos() throws Exception {
		return service.buscarTodos();
	}
	
	@Override
	public void atualizar(Professor entidade) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(String cpf) throws Exception {
		service.excluir(cpf);
	}

	public Pilha<AreaConhecimento> listarAreas() throws Exception {
		return areaService.buscarTodos();
	}

	public AreaConhecimento buscarAreaPorId(Integer id) throws Exception {
		return areaService.buscarPorID(id);
	}

	@Override
	public Professor buscarPorID(String id) throws Exception {
		return service.buscarPorID(id);
	}



}
