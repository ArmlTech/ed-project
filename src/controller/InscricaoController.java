package controller;

import java.time.LocalDate;

import br.edu.fateczl.Fila;
import br.edu.fateczl.Lista;
import model.dto.Candidatura;
import model.dto.Disciplina;
import model.dto.Processo;
import model.dto.ProcessoDisplay;
import model.dto.Professor;
import service.InscricaoService;
import service.ProcessoService;
import service.ProfessorService;


public class InscricaoController implements IGenericCrudController<Candidatura, Integer> {

    private final InscricaoService service;
    private final ProfessorService profService;
    private final ProcessoService processoService;

    public InscricaoController() {
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
        Integer idProcesso = Integer.parseInt(dadosInput.get(0));
        if (idProcesso < 0) {
            throw new Exception("Selecione um processo válido");
        }
        Integer idProfessor = Integer.parseInt(dadosInput.get(1));
        if (idProfessor < 0) {
            throw new Exception("Selecione um professor válido");
        }

        return new Candidatura(id, idProcesso, idProfessor, LocalDate.now());
    }

    public Professor buscarProfessorPorId(Integer id) throws Exception {
        return profService.buscarPorID(id);
    }

    public String buscarNomeDisciplinaPorProcessoID(Integer idProcesso) throws Exception {
        return processoService.buscarNomeDisciplinaPorProcessoID(idProcesso);
    }

    public Fila<Professor> buscarTodosProfessores() throws Exception {
       return profService.buscarTodos();
    }

    public Fila<ProcessoDisplay> buscarTodosProcessos() throws Exception {
        Fila<Processo> processos = processoService.buscarTodos();
        Lista<Disciplina> disciplinas = processoService.buscarTodasDisciplinas();
        Fila<ProcessoDisplay> processosComDisciplinas = new Fila<>();
        while (!processos.isEmpty()) {
            Processo processo = processos.remove();
            for(int i = 0; i < disciplinas.size(); i++) {
                Disciplina disciplina = disciplinas.get(i);
                if (disciplina.getId().equals(processo.getIdDisciplina())) {
                    ProcessoDisplay processoDisplay = new ProcessoDisplay(processo.getId(), disciplina.getNome());
                    processosComDisciplinas.insert(processoDisplay);
                    break;
                }
            }
        }
        return processosComDisciplinas;
    }

    public ProcessoDisplay buscarProcessoDisplayPorId(Integer idProcesso) throws Exception {
        String nomeDisciplina = processoService.buscarNomeDisciplinaPorID(idProcesso);
        return new ProcessoDisplay(idProcesso, nomeDisciplina);
    }

    public Disciplina buscarDisciplinaPorId(Integer idProcesso) throws Exception {
        return processoService.buscarDisciplinaPorId(idProcesso);
    }

}
