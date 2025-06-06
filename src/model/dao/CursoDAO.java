package model.dao;
import java.io.IOException;

import model.dto.Curso;

public class CursoDAO extends GenericDAO<Curso, Integer> {

	public CursoDAO() {
		super("cursos.csv");
	}

	@Override
	public String entityToCSV(Curso entidade) {
		return entidade.getId() + ";" + entidade.getNome() + ";" + entidade.getIdAreaConhecimento();
	}

	@Override
	protected Curso csvToEntity(String[] dados) {
		Integer id = Integer.parseInt(dados[0]);
		String nome = dados[1];
		Integer idAreaConhecimento = Integer.parseInt(dados[2]);
		return new Curso(id, nome, idAreaConhecimento);
	}

}