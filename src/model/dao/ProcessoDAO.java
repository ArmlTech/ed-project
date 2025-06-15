package model.dao;

import model.dto.Processo;

public class ProcessoDAO extends GenericDAO<Processo, Integer> {

    public ProcessoDAO() {
        super("processos.csv");
    }

    @Override
    protected String entityToCSV(Processo entidade) {
        return entidade.getId() + ";" + entidade.getIdDisciplina();
    }

    @Override
    protected Processo csvToEntity(String[] dados) {
        Integer id = Integer.parseInt(dados[0]);
        Integer idDisciplina = Integer.parseInt(dados[1]);
        return new Processo(id, idDisciplina);
    }
}
