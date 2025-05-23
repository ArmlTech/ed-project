package controller;

import br.edu.fateczl.pilha.Pilha;
import model.dto.AreaConhecimento;
import model.dto.Professor;
import service.AreaConhecimentoService;
import service.ProfessorService;

public class ProfessorController{

    private final ProfessorService service;
    private final AreaConhecimentoService areaService;

    public ProfessorController() {
        this.service = new ProfessorService();
        this.areaService = new AreaConhecimentoService();
    }

    public Pilha<Professor> listarProfessores() throws Exception {
        return service.listarProfessores();
    }

    public void cadastrar(Professor professor) throws Exception {
        service.cadastrar(professor);
    }

    public void atualizar(Professor professor) throws Exception {
        service.atualizar(professor);
    }

    public void excluir(String cpf) throws Exception {
        service.excluir(cpf);
    }

	public Pilha<AreaConhecimento> listarAreas() throws Exception {
		return areaService.listarAreas();
	}

	public AreaConhecimento buscarAreaPorId(int id) throws Exception {
		return areaService.procurarPorID(id);
	}




}
