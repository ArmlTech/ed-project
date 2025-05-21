package service;

import br.edu.fateczl.Lista;
import model.dto.AreaConhecimento;

public class DisciplinaService {

	public Lista<AreaConhecimento> listarAreas() throws Exception {
		Lista<AreaConhecimento> areas = new Lista<>();
		areas.addLast(new AreaConhecimento(1, "Ciência da computação"));
		return areas;
	}

	public AreaConhecimento buscarAreaPorId() {
		// TODO Auto-generated method stub
		return null;
	}

}
