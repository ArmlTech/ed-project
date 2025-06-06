package controller;

import java.io.IOException;

import br.edu.fateczl.Fila;
import br.edu.fateczl.Lista;
import model.dto.Curso;
import model.dto.Disciplina;
import service.CursoService;
import service.DisciplinaService;

public class DisciplinaController implements IGenericController<Disciplina, Integer> {
    private final DisciplinaService service;
    private final CursoService cursoService;

    public DisciplinaController() {
        service = new DisciplinaService();
        cursoService = new CursoService();
    }

    @Override
    public void salvar(Disciplina entidade) throws Exception {
        service.salvar(entidade);
    }

    @Override
    public Fila<Disciplina> buscarTodos() throws Exception {
        return service.buscarTodos();
    }

    @Override
    public void atualizar(Disciplina entidade) throws Exception {
       service.atualizar(entidade);
    }

    @Override
    public void excluir(Disciplina entidade) throws Exception {
        service.excluir(entidade.getId());
    }

    @Override
    public Disciplina buscarPorID(Integer id) throws Exception {
        return service.buscarPorID(id);
    }

    public Curso buscarCursoPorId(Integer id) throws Exception{
        return cursoService.buscarPorID(id);
    }

    public Fila<Curso> buscarTodosCursos() throws Exception{
        return cursoService.buscarTodos();
    }

    @Override
    public Disciplina criarEntidade(Disciplina entidade, Lista<String> dadosInput) throws Exception {

        for(int i = 0, length = dadosInput.size(); i < length; i++){
			if(dadosInput.get(i).isBlank()){
				throw new Exception("Preencha todos os campos");
			}
		}

        Integer id = (entidade == null) ? 0 : entidade.getId();
        String nome = dadosInput.get(0);
        String diaSemana = dadosInput.get(1);
        String horaInicial = dadosInput.get(2);
        Double qtdHoras = Double.parseDouble(dadosInput.get(3));
        Integer idCurso = Integer.parseInt(dadosInput.get(4));

        try {
            qtdHoras = Double.parseDouble(dadosInput.get(4));
        } catch (NumberFormatException e) {
            throw new Exception("Digite apenas números no campo: Quantidade de Horas");
        }

		Disciplina disciplina = new Disciplina(id, nome, diaSemana, horaInicial, qtdHoras, idCurso);
        
        return disciplina;
    }

}
