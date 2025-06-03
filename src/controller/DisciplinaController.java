package controller;

import br.edu.fateczl.Lista;
import br.edu.fateczl.pilha.Pilha;
import model.dto.Curso;
import model.dto.Disciplina;
import service.CursoService;
import service.DisciplinaService;

public class DisciplinaController implements IGenericController<Disciplina, Integer> {
    private final DisciplinaService service;
    private final CursoService cursoService;

    public DisciplinaController(){
        service = new DisciplinaService();
        cursoService = new CursoService();
    }

    @Override
    public void salvar(Disciplina entidade) throws Exception {
        service.salvar(entidade);
    }

    @Override
    public Pilha<Disciplina> buscarTodos() throws Exception {
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

    public Pilha<Curso> buscarTodosCursos() throws Exception{
        return cursoService.buscarTodos();
    }

    @Override
    public Disciplina criarEntidade(Disciplina entidade, Lista<String> dadosInput) throws Exception {

        String nome = dadosInput.get(1);
        String diaSemana = dadosInput.get(2);
        String horaInicial = dadosInput.get(3);
        Double qtdHoras = Double.parseDouble(dadosInput.get(4));
        Integer idCurso = Integer.parseInt(dadosInput.get(5));

        Integer id;
		if(entidade == null){
			id = 0;
		}else{
			id = entidade.getId();
		}

        try {
            qtdHoras = Double.parseDouble(dadosInput.get(4));
        } catch (NumberFormatException e) {
            throw new Exception("Digite apenas números no campo: Quantidade de Horas");
        }

		Disciplina disciplina = new Disciplina(id, nome, diaSemana, horaInicial, qtdHoras, idCurso);
        return disciplina;
    }

}
