package service;


import br.edu.fateczl.Fila;
import br.edu.fateczl.Lista;
import model.dao.ProcessoDAO;
import model.dto.Candidatura;
import model.dto.Disciplina;
import model.dto.Processo;

public class ProcessoService extends GenericService<Processo, Integer, ProcessoDAO> {

    private final DisciplinaService disciplinaService;
    private final InscricaoService inscricaoService;
    
    public ProcessoService() {
        super(new ProcessoDAO());
        this.disciplinaService = new DisciplinaService(this);
        this.inscricaoService = new InscricaoService();
    }

    public String buscarNomeDisciplina(Integer idProcesso) throws Exception {
        return disciplinaService.buscarPorID(idProcesso).getNome();
    }

    public Disciplina buscarDisciplinaPorId(Integer id) throws Exception {
        return disciplinaService.buscarPorID(id);
    }

    public Lista<Disciplina> buscarTodasDisciplinas() throws Exception {
        Fila<Disciplina> disciplinas = disciplinaService.buscarTodos();
        Lista<Disciplina> listaDisciplinas = new Lista<>();
        while (!disciplinas.isEmpty()) {
            listaDisciplinas.addLast(disciplinas.remove());
        }
        return listaDisciplinas;
    }

    //Exclusão em cascata de Disciplina, Processos e Inscrições
    @Override
    public void excluir(Integer id) throws Exception {
        dao.excluir(id);

        Fila<Candidatura> candidaturas = inscricaoService.buscarTodos();
        while (!candidaturas.isEmpty()) {
            Candidatura candidatura = candidaturas.remove();
            if (candidatura.getIdProcesso().equals(id)) {
                inscricaoService.excluir(candidatura.getId());
            }
        }
    }

    public void excluirPorDisciplina(Integer id) throws Exception {

        Fila<Processo> processos = buscarTodos();
        while (!processos.isEmpty()) {
            Processo processo = processos.remove();
            if (processo.getIdDisciplina().equals(id)) {
                excluir(processo.getId());
            }
        }
    } 

}
