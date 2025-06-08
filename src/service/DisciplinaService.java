package service;

import model.dao.DisciplinaDAO;
import model.dto.Disciplina;

public class DisciplinaService extends GenericService<Disciplina, Integer, DisciplinaDAO> {

	private final ProcessoService processoService;

	public DisciplinaService() {
		super(new DisciplinaDAO());
		this.processoService = new ProcessoService();}

	public DisciplinaService(ProcessoService processoService) {
		super(new DisciplinaDAO());
		this.processoService = processoService;
	}

	//Exclusão em cascata de Disciplina, Processos e Inscrições
	@Override
	public void excluir(Integer id) throws Exception {
		dao.excluir(id);
		processoService.excluirPorDisciplina(id);
	}

}
