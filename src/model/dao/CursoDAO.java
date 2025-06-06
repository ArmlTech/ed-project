package model.dao;
import java.io.IOException;

import model.dto.Curso;

<<<<<<< HEAD
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
	public void atualizar(Curso cursoAtualizado) throws Exception {

		Pilha<Curso> cursos = buscarTodos();
		Lista<String> cursosCsv = new Lista<>();

		while (!cursos.isEmpty()) {

			Curso curso = cursos.pop();

			if (curso.getId() == (cursoAtualizado.getId())) {

				curso = cursoAtualizado;

			}

			cursosCsv.addLast(toCSV(curso));

		}

		CsvUtils.escreverCSV(caminhoArquivo, cursosCsv);

	}

	@Override
	public void excluir(String id) throws Exception {

		Pilha<Curso> cursos = buscarTodos();
		Lista<String> cursosCsv = new Lista<>();

		while (!cursos.isEmpty()) {

			Curso curso = cursos.pop();

			if (curso.getId() != Integer.parseInt(id)) {

				cursosCsv.addLast(toCSV(curso));

			}

		}

		CsvUtils.escreverCSV(caminhoArquivo, cursosCsv);

	}

	@Override
	public Curso buscarPorID(String id) throws Exception {

		Pilha<Curso> cursos = buscarTodos();

		while (!cursos.isEmpty()) {

			Curso curso = cursos.pop();

			if (curso.getId() == Integer.parseInt(id)) {

				return curso;

			}

		}

		throw new Exception("Curso não encontrado");
	}

	@Override
	public String toCSV(Curso entidade) {

		return entidade.getId() + ";" + entidade.getNome() + ";" + entidade.getIdAreaConhecimento();
	}
=======
public class CursoDAO extends GenericDAO<Curso, Integer> {
>>>>>>> 18d9c03 (refactor: generalizando dao pra simplificar o codigo e criação do crud inscrição)

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