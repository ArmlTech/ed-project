package service;

import java.io.IOException;

import model.dao.DisciplinaDAO;
import model.dto.Disciplina;

public class DisciplinaService extends GenericService<Disciplina, Integer, DisciplinaDAO> {

	public DisciplinaService() throws IOException{
		super(new DisciplinaDAO());
	}

	@Override
	public void excluir(Integer id) throws Exception {
		dao.excluir(id);

		//excluir composições de disciplina (processo, candidutura aka inscrições)
	}

}
