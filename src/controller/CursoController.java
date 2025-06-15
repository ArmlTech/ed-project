package controller;

import br.edu.fateczl.Fila;
import br.edu.fateczl.Lista;
import model.dto.AreaConhecimento;
import model.dto.Curso;
import service.AreaConhecimentoService;
import service.CursoService;

public class CursoController implements IGenericCrudController<Curso, Integer> {

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
    public Fila<Curso> buscarTodos() throws Exception {
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
        for (int i = 0, length = dadosInput.size(); i < length; i++) {
            if (dadosInput.get(i).isBlank()) {
                throw new Exception("Preencha todos os campos");
            }
        }
        Integer id = (entidade == null) ? -1 : entidade.getId();
        String nome = dadosInput.get(0);
        Integer idAreaConhecimento = Integer.parseInt(dadosInput.get(1));
        if (idAreaConhecimento < 0) {
            throw new Exception("Selecione uma área de conhecimento válida");
        }
        
        Curso curso = new Curso(id, nome, idAreaConhecimento);
        return curso;
    }

    public AreaConhecimento buscarAreaPorId(Integer id) throws Exception {
        return areaService.buscarPorID(id);
    }

    public Fila<AreaConhecimento> buscarTodosArea() throws Exception {
       return areaService.buscarTodos();
    }

}
