package service;

import java.io.IOException;

import model.dao.AreaConhecimentoDAO;
import model.dto.AreaConhecimento;

public class AreaConhecimentoService extends GenericService<AreaConhecimento, Integer, AreaConhecimentoDAO> {

	public AreaConhecimentoService() throws IOException {
		super(new AreaConhecimentoDAO());
	}

}
