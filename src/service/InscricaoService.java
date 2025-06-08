package service;

import br.edu.fateczl.Fila;
import model.dao.InscricaoDAO;
import model.dto.Candidatura;

public class InscricaoService extends GenericService<Candidatura, Integer, InscricaoDAO> {

    public InscricaoService() {
        super(new InscricaoDAO());
    }

    @Override
    public void salvar(Candidatura candidatura) throws Exception {
        if (existeCandidatura(candidatura)) {
            throw new Exception("Professor já está inscrito nesse processo seletivo.");
        }
        dao.salvar(candidatura);
    }

    @Override
    public void atualizar(Candidatura candidatura) throws Exception {
        if (existeCandidatura(candidatura)) {
            throw new Exception("Professor já está inscrito nesse processo seletivo.");
        }
        dao.atualizar(candidatura);
    }

    public boolean existeCandidatura(Candidatura novaCandidatura) throws Exception {
        Fila<Candidatura> candidaturas = dao.buscarTodos();
        while (!candidaturas.isEmpty()) {
            Candidatura c = candidaturas.remove();
            if (c.getIdProcesso().equals(novaCandidatura.getIdProcesso()) &&
                c.getIdProfessor().equals(novaCandidatura.getIdProfessor())) {
                return true;
            }
        }
        return false;
    }

}
