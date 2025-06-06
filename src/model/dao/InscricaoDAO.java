package model.dao;
import model.dto.Candidatura;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InscricaoDAO extends GenericDAO<Candidatura, Integer> {

    public InscricaoDAO(){
        super("inscricoes.csv");
    }
    
    @Override
    protected String entityToCSV(Candidatura entidade) {
        return entidade.getId() + ";" +
               entidade.getIdProcesso() + ";" +
               entidade.getIdProfessor() + ";" +
               entidade.getData().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
    @Override
    protected Candidatura csvToEntity(String[] dados) {
        Integer id = Integer.parseInt(dados[0]);
        Integer idProcesso = Integer.parseInt(dados[1]);
        Integer idProfessor = Integer.parseInt(dados[2]);
        LocalDate data = LocalDate.parse(dados[3], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return new Candidatura(id, idProcesso, idProfessor, data);
    }
}
