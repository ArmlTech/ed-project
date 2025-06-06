package model.dao;
import java.io.IOException;

import model.dto.AreaConhecimento;

public class AreaConhecimentoDAO extends GenericDAO<AreaConhecimento, Integer> {

	public AreaConhecimentoDAO() {
		super("areas.csv");
	}

	@Override
	protected String entityToCSV(AreaConhecimento entidade) {
		return entidade.getId() + ";" + entidade.getNome();
	}

	@Override
	protected AreaConhecimento csvToEntity(String[] dados) {
		Integer id = Integer.parseInt(dados[0]);
		String nome = dados[1];
		return new AreaConhecimento(id, nome);
	}

}
