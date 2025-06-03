package model.dao;

import model.dto.Disciplina;


public class DisciplinaDAO extends GenericDAO<Disciplina, Integer> {

    public DisciplinaDAO() {
        super("disciplinas.csv");
    }
 
    @Override
    public String entityToCSV(Disciplina entidade) {
        return 
            entidade.getId() + ";" + 
            entidade.getNome() + ";" + 
            entidade.getDiaSemana() + ";" + 
            entidade.getHoraInicial() + ";" + 
            entidade.getQtdHoras() + ";" + 
            entidade.getIdCurso();
    }

    @Override
    protected Disciplina csvToEntity(String[] dados) {
        Integer id = Integer.parseInt(dados[0]);
        String nome = dados[1];
        String diaSemana = dados[2];
        String horaInicial = dados[3];
        Double qtdHoras = Double.parseDouble(dados[4]);
        Integer idCurso = Integer.parseInt(dados[5]);
        return new Disciplina(id, nome, diaSemana, horaInicial, qtdHoras, idCurso);
    }
    

}
