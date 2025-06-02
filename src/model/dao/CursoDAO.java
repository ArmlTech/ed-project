package model.dao;

import br.edu.fateczl.Lista;
import br.edu.fateczl.pilha.Pilha;
import model.dto.Curso;
import util.CsvUtils;

import java.io.IOException;

public class CursoDAO implements IGenericDAO<Curso, String> {

	public final String caminhoArquivo = "C:\\TEMP\\cursos.csv";

	@Override
	public void salvar(Curso curso) throws Exception {

		CsvUtils.adicionarLinhaCSV(caminhoArquivo, toCSV(curso));

	}

	@Override
	public Pilha<Curso> buscarTodos() throws Exception {

		Lista<String[]> dados = CsvUtils.lerArquivo(caminhoArquivo);
		Pilha<Curso> cursos = new Pilha<>();

		for (int i = 0, lenght = dados.size(); i < lenght; i++) {

			Integer id = Integer.parseInt(dados.get(i)[0]);
			String nome = dados.get(i)[1];
			Integer idAreaConhecimento = Integer.parseInt(dados.get(i)[1]);

			Curso curso = new Curso(id, nome, idAreaConhecimento);

			cursos.push(curso);

		}

		return cursos;
	}

	@Override
	public void atualizar(Curso entidade) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluir(String id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Curso buscarPorID(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toCSV(Curso entidade) {
		// TODO Auto-generated method stub
		return null;
	}

}
