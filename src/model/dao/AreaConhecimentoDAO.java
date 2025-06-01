package model.dao;

import br.edu.fateczl.Lista;
import br.edu.fateczl.pilha.Pilha;
import model.dto.AreaConhecimento;
import util.CsvUtils;

public class AreaConhecimentoDAO implements IGenericDAO<AreaConhecimento, Integer> {

	final private String caminhoArquivo = "C:\\TEMP\\areas.csv";

	@Override
	public void salvar(AreaConhecimento entidade) throws Exception {

		CsvUtils.adicionarLinhaCSV(caminhoArquivo, toCSV(entidade));

	}

	@Override
	public Pilha<AreaConhecimento> buscarTodos() throws Exception {

		Lista<String[]> dados = CsvUtils.lerArquivo(caminhoArquivo);

		Pilha<AreaConhecimento> areas = new Pilha<>();

		for (int i = 0, length = dados.size(); i < length; i++) {

			Integer id = Integer.parseInt(dados.get(i)[0]);
			String nome = dados.get(i)[1];

			AreaConhecimento area = new AreaConhecimento(id, nome);

			areas.push(area);

		}

		return areas;

	}

	@Override
	public void atualizar(AreaConhecimento entidade) throws Exception {

		Pilha<AreaConhecimento> areas = buscarTodos();

		Lista<String> dados = new Lista<>();

		while (!areas.isEmpty()) {

			AreaConhecimento area = areas.pop();

			if (area.getID() == entidade.getID()) {

				area = entidade;

			}

			dados.addLast(toCSV(area));
		}

	}

	@Override
	public void excluir(Integer id) throws Exception {

		Pilha<AreaConhecimento> areas = buscarTodos();

		Lista<String> dados = new Lista<>();

		while (!areas.isEmpty()) {

			AreaConhecimento area = areas.pop();

			if (area.getID() != id) {

				dados.addLast(toCSV(area));

			}

		}

	}

	@Override
	public AreaConhecimento buscarPorID(Integer id) throws Exception {

		Pilha<AreaConhecimento> areas = buscarTodos();

		while (!areas.isEmpty()) {

			AreaConhecimento area = areas.pop();

			if (area.getID() == id) {

				return area;

			}
			
		

		}

		throw new Exception("Área não encontrada");
		
	}

	@Override
	public String toCSV(AreaConhecimento entidade) {

		return entidade.getID() + ";" + entidade.getNome();

	}

}
