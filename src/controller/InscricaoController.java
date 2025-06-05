package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.edu.fateczl.Fila;
import br.edu.fateczl.Lista;
import model.dto.Candidatura;
import model.dto.Disciplina;
import model.dto.Processo;
import model.dto.Professor;
import service.InscricaoService;
import service.ProcessoService;
import service.ProfessorService;


public class InscricaoController implements IGenericController<Candidatura, Integer> {

    private final InscricaoService service;
    private final ProfessorService profService;
    private final ProcessoService processoService;

    public InscricaoController() throws IOException {
        this.service = new InscricaoService();
        this.profService = new ProfessorService();
        this.processoService = new ProcessoService();
    }

    @Override
    public void salvar(Candidatura entidade) throws Exception {
        service.salvar(entidade);
    }

    @Override
    public Fila<Candidatura> buscarTodos() throws Exception {
        return service.buscarTodos();
    }

    @Override
    public void atualizar(Candidatura entidade) throws Exception {
        service.atualizar(entidade);
    }

    @Override
    public void excluir(Candidatura entidade) throws Exception {
        service.excluir(entidade.getId());
    }

    @Override
    public Candidatura buscarPorID(Integer id) throws Exception {
        return service.buscarPorID(id);
    }

    @Override
    public Candidatura criarEntidade(Candidatura entidade, Lista<String> dadosInput) throws Exception {
        for (int i = 0, length = dadosInput.size(); i < length; i++) {
            if (dadosInput.get(i).isBlank()) {
                throw new Exception("Preencha todos os campos");
            }
        }

        Integer id = (entidade == null) ? 0 : entidade.getId();
        Integer idProfessor = Integer.parseInt(dadosInput.get(0));
        Integer idProcesso = Integer.parseInt(dadosInput.get(1));
        if(dadosInput.size() > 2){
            LocalDate data = LocalDate.parse(dadosInput.get(2), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            return new Candidatura(id, idProfessor, idProcesso, data);
        }

        return new Candidatura(id, idProfessor, idProcesso, LocalDate.now());
    }

    public Professor buscarProfessorPorId(Integer id) throws Exception {
        return profService.buscarPorID(id);
    }

    public String buscarDisciplinaProcesso(Integer idProcesso) throws Exception {
        return processoService.buscarNomeDisciplina(idProcesso);
    }

    public Fila<Professor> buscarTodosProfessores() throws Exception {
       return profService.buscarTodos();
    }

    public Fila<Processo> buscarTodosProcessos() throws Exception {
        return processoService.buscarTodos();
    }

    public Disciplina buscarDisciplinaPorId(Integer idProcesso) throws Exception {
        return processoService.buscarDisciplinaPorId(idProcesso);
    }

    public Object buscarProcessoPorId(Integer idProcesso) throws Exception {
        return processoService.buscarPorID(idProcesso);
    }
}
