package controller;

import br.edu.fateczl.Lista;
import br.edu.fateczl.pilha.Pilha;
import model.dto.AreaConhecimento;
import model.dto.Professor;
import service.DisciplinaService;
import service.ProfessorService;

public class ProfessorController {

    private final ProfessorService service;
    private final DisciplinaService disciplinaService;

    public ProfessorController() {
        this.service = new ProfessorService();
        this.disciplinaService = new DisciplinaService();
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

	public Lista<AreaConhecimento> listarAreas() throws Exception {
		return disciplinaService.listarAreas();
	}

	public AreaConhecimento buscarAreaPorId(int areaID) {
		return disciplinaService.buscarAreaPorId();
	}


}
