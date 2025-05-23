package controller;

import br.edu.fateczl.pilha.Pilha;
import model.dto.AreaConhecimento;
import model.dto.Professor;
import service.AreaConhecimentoService;
import service.ProfessorService;

public class ProfessorController implements CrudController<Professor>{

    private final ProfessorService service;
    private final AreaConhecimentoService areaService;

    public ProfessorController() {
        this.service = new ProfessorService();
        this.areaService = new AreaConhecimentoService();
    }

	public Pilha<AreaConhecimento> listarAreas() throws Exception {
		return areaService.listarAreas();
	}

	public AreaConhecimento buscarAreaPorId(int id) throws Exception {
		return areaService.procurarPorID(id);
	}

	@Override
	public Pilha<Professor> listar() throws Exception {
		return service.listarProfessores();
	}

	@Override
	public void salvar(Professor entidade) throws Exception {
		service.cadastrar(entidade);
	}

	@Override
	public void excluir(Professor entidade) throws Exception {
		service.excluir(entidade);
	}




}
