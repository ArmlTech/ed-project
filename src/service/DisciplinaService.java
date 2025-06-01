package service;

  import br.edu.fateczl.pilha.Pilha;
import model.dao.DisciplinaDAO;
import model.dto.Disciplina;

public class DisciplinaService implements IGenericService<Disciplina, Integer> {

	private final DisciplinaDAO dao;

	public DisciplinaService(){
		dao = new DisciplinaDAO();
	}

	@Override
	public void salvar(Disciplina entidade) throws Exception {

		if(!verificarUnicidadePorNome(entidade.getNome())){
			dao.salvar(entidade);
		}else{
			throw new Exception("Disciplina já existe");
		}
		
		//uma disciplina pertence a apenas 1 curso (1:3 entre curso de disciplina)
		//checar unicidade a partir do nome
	}

	@Override
	public Pilha<Disciplina> buscarTodos() throws Exception {
		return dao.buscarTodos();
	}

	@Override
	public void atualizar(Disciplina entidade) throws Exception {
		dao.atualizar(entidade);
			if(!verificarUnicidadePorNome(entidade.getNome())){
				dao.salvar(entidade);
			}else{
				throw new Exception("Disciplina já existe");
			}
		//uma disciplina pertence a apenas 1 curso (1:3 entre curso de disciplina)
		//checar unicidade a partir do nome
	}

	@Override
	public void excluir(Integer id) throws Exception {
		dao.excluir(id);

		//excluir composições de disciplina (processo, candidutura aka inscrições)
	}

	@Override
	public Disciplina buscarPorID(Integer id) throws Exception {
		return dao.buscarPorID(id);
	}

	public boolean verificarUnicidadePorNome(String nome) {
		try {
			if(dao.buscarPorNome(nome) != null){
				return true;
			}
		} catch (Exception e) {
			return false;
		}

		return false;
	}

}
