package service;

import model.dao.AreaConhecimentoDAO;
import model.dto.AreaConhecimento;

public class AreaConhecimentoService extends GenericService<AreaConhecimento, Integer, AreaConhecimentoDAO> {

	public AreaConhecimentoService() {
		super(new AreaConhecimentoDAO());
	}

}
