package model.dao;
import java.io.IOException;

import model.dto.Processo;

public class ProcessoDAO extends GenericDAO<Processo, Integer> {

    public ProcessoDAO() throws IOException {
        super("processos.csv");
    }

    @Override
    protected String entityToCSV(Processo entidade) {
        return entidade.getId() + ";" +
                (entidade.isStatus() ? "1" : "0") + ";" +
                entidade.getIdDisciplina();
    }

    @Override
    protected Processo csvToEntity(String[] dados) {
        Integer id = Integer.parseInt(dados[0]);
        boolean status = dados[1].equals("1"); // 1 = true, 0 = false
        Integer idDisciplina = Integer.parseInt(dados[2]);
        return new Processo(id, status, idDisciplina);
    }
}
