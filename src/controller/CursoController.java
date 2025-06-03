package controller;

<<<<<<< HEAD
import br.edu.fateczl.Lista;
import br.edu.fateczl.pilha.Pilha;
import model.dto.Curso;
import service.AreaConhecimentoService;
import service.CursoService;

public class CursoController implements IGenericController<Curso, Integer> {

	private final CursoService service;
	private final AreaConhecimentoService areaService;

	public CursoController() {

		this.service = new CursoService();
		this.areaService = new AreaConhecimentoService();

	}

	@Override
	public void salvar(Curso entidade) throws Exception {

		service.salvar(entidade);

	}

	@Override
	public Pilha<Curso> buscarTodos() throws Exception {
		return service.buscarTodos();
	}

	@Override
	public void atualizar(Curso entidade) throws Exception {

		service.atualizar(entidade);

	}

	@Override
	public void excluir(Curso entidade) throws Exception {

		service.excluir(entidade.getId());

	}

	@Override
	public Curso buscarPorID(Integer id) throws Exception {

		return service.buscarPorID(id);

	}

	@Override
	public Curso criarEntidade(Curso entidade, Lista<String> dadosInput) throws Exception {

		for (int i = 0, lenght = dadosInput.size(); i < lenght; i++) {

			if (dadosInput.get(i).isBlank()) {

				throw new Exception("Preencha todos os campos");

			}

		}

		Integer id;

		if (entidade == null) {

			id = 0;

		} else {

			id = entidade.getId();

		}

		Integer idCurso = Integer.parseInt(dadosInput.get(0));
		String nome = dadosInput.get(1);
		Integer idAreaConhecimento = Integer.parseInt(dadosInput.get(2));

		Curso curso = new Curso(idCurso, nome, idAreaConhecimento);
		return curso;

	}
=======
import br.edu.fateczl.Fila;
import br.edu.fateczl.Lista;
import model.dto.Curso;

public class CursoController implements IGenericController<Curso, Integer> {

    @Override
    public void salvar(Curso entidade) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'salvar'");
    }

    @Override
    public Fila<Curso> buscarTodos() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarTodos'");
    }

    @Override
    public void atualizar(Curso entidade) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public void excluir(Curso entidade) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'excluir'");
    }

    @Override
    public Curso buscarPorID(Integer id) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorID'");
    }

    @Override
    public Curso criarEntidade(Curso entidade, Lista<String> dadosInput) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'criarEntidade'");
    }
>>>>>>> 18d9c03 (refactor: generalizando dao pra simplificar o codigo e criação do crud inscrição)

}
