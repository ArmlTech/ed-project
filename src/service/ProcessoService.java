package service;

import java.io.IOException;

import model.dao.ProcessoDAO;
import model.dto.Disciplina;
import model.dto.Processo;

public class ProcessoService extends GenericService<Processo, Integer, ProcessoDAO> {

    private final DisciplinaService disciplinaService;
    public ProcessoService() {
        super(new ProcessoDAO());
        this.disciplinaService = new DisciplinaService();
    }

    public String buscarNomeDisciplina(Integer idProcesso) throws Exception {
        return disciplinaService.buscarPorID(idProcesso).getNome();
    }

    public Disciplina buscarDisciplinaPorId(Integer id) throws Exception {
        return disciplinaService.buscarPorID(id);
    }

}
